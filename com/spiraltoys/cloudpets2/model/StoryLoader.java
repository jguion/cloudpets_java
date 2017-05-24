package com.spiraltoys.cloudpets2.model;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.android.vending.expansion.zipfile.ZipResourceFile.ZipEntryRO;
import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;
import com.spiraltoys.cloudpets2.model.Story.Page;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoryLoader {
    private static final String EXTENSION_JPEG = "jpeg";
    private static final String EXTENSION_JPG = "jpg";
    private static final String EXTENSION_PNG = "png";
    private static final String EXTENSION_WAV = "wav";
    private static final String STORIES_DIRECTORY_PATH = "expansion_media_files/stories/";
    private static final String STORY_FILE_NAME = "story.json";
    private static final Pattern STORY_PAGE_FILE_NAME_MATCHER = Pattern.compile("^.*page.*?([0-9]+)$");
    private static final Pattern STORY_PREVIEW_FILE_NAME_MATCHER = Pattern.compile("^.*preview$");
    private static StoryLoader mInstance;
    private ArrayList<Story> mStories;

    public static synchronized StoryLoader getInstance() {
        StoryLoader storyLoader;
        synchronized (StoryLoader.class) {
            if (mInstance == null) {
                mInstance = new StoryLoader();
            }
            storyLoader = mInstance;
        }
        return storyLoader;
    }

    private StoryLoader() {
    }

    public ArrayList<Story> loadStories(Context context) {
        if (isLoaded()) {
            return this.mStories;
        }
        this.mStories = new ArrayList();
        try {
            ZipResourceFile expansion = ExpansionUtils.getMainExpansionDescriptor().getZipResourceFile(context);
            for (ZipEntryRO storyFolderEntry : expansion.getEntriesAt(STORIES_DIRECTORY_PATH)) {
                Story story = tryToLoadStory(context, expansion, storyFolderEntry.mFileName);
                if (story != null) {
                    this.mStories.add(story);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.mStories = null;
        }
        Collections.sort(this.mStories, new Comparator<Story>() {
            public int compare(Story lhs, Story rhs) {
                int sortPriorityCompare = -Integer.compare(lhs.getSortPriority(), rhs.getSortPriority());
                return sortPriorityCompare == 0 ? lhs.getTitle().compareTo(rhs.getTitle()) : sortPriorityCompare;
            }
        });
        return this.mStories;
    }

    public ArrayList<Story> getStories() {
        return this.mStories == null ? new ArrayList() : this.mStories;
    }

    @Nullable
    private Story tryToLoadStory(Context context, ZipResourceFile expansion, String folderPath) {
        InputStream in = null;
        try {
            in = expansion.getAssetFileDescriptor(folderPath + STORY_FILE_NAME).createInputStream();
            Story story = (Story) new GsonBuilder().setPrettyPrinting().create().fromJson(new JsonReader(new InputStreamReader(in)), Story.class);
            if (story != null) {
                int i;
                story.loadResources(context);
                String[] pageStrings = context.getResources().getStringArray(story.getPageStringsArrayResourceId(context));
                SparseArray<Page> pages = new SparseArray(pageStrings.length);
                for (i = 0; i < pageStrings.length; i++) {
                    Page page = new Page();
                    page.setText(pageStrings[i]);
                    pages.put(i, page);
                }
                for (ZipEntryRO storyFileEntry : expansion.getEntriesAt(folderPath)) {
                    if (!tryToAddOrUpdatePageEntry(pages, storyFileEntry)) {
                        tryToAddOrUpdatePreviewEntry(story, storyFileEntry);
                    }
                }
                ArrayList<Page> pagesList = new ArrayList(pages.size());
                for (i = 0; i < pages.size(); i++) {
                    pagesList.add(pages.valueAt(i));
                }
                story.setPages(pagesList);
            }
            if (in == null) {
                return story;
            }
            try {
                in.close();
                return story;
            } catch (IOException e) {
                e.printStackTrace();
                return story;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
            }
        }
    }

    private boolean tryToAddOrUpdatePageEntry(SparseArray<Page> pages, ZipEntryRO storyFileEntry) {
        Matcher pageMatcher = STORY_PAGE_FILE_NAME_MATCHER.matcher(Files.getNameWithoutExtension(storyFileEntry.mFileName));
        if (!pageMatcher.matches()) {
            return false;
        }
        int pageNumber = Integer.parseInt(pageMatcher.toMatchResult().group(1));
        Page page = (Page) pages.get(pageNumber);
        if (page == null) {
            page = (Page) pages.get(pageNumber, new Page());
        }
        String fileExtension = Files.getFileExtension(storyFileEntry.mFileName);
        boolean z = true;
        switch (fileExtension.hashCode()) {
            case 105441:
                if (fileExtension.equals(EXTENSION_JPG)) {
                    z = true;
                    break;
                }
                break;
            case 111145:
                if (fileExtension.equals(EXTENSION_PNG)) {
                    z = true;
                    break;
                }
                break;
            case 117484:
                if (fileExtension.equals(EXTENSION_WAV)) {
                    z = false;
                    break;
                }
                break;
            case 3268712:
                if (fileExtension.equals(EXTENSION_JPEG)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                page.setAudioPath(storyFileEntry.mFileName);
                return true;
            case true:
            case true:
            case true:
                page.setImagePath(storyFileEntry.mFileName);
                return true;
            default:
                return false;
        }
    }

    private void tryToAddOrUpdatePreviewEntry(Story story, ZipEntryRO storyFileEntry) {
        String fileName = Files.getNameWithoutExtension(storyFileEntry.mFileName);
        String fileExtension = Files.getFileExtension(storyFileEntry.mFileName);
        Matcher previewMatcher = STORY_PREVIEW_FILE_NAME_MATCHER.matcher(fileName);
        HashSet<String> validPreviewExtensions = new HashSet();
        validPreviewExtensions.add(EXTENSION_JPEG);
        validPreviewExtensions.add(EXTENSION_JPG);
        validPreviewExtensions.add(EXTENSION_PNG);
        if (previewMatcher.matches() && validPreviewExtensions.contains(fileExtension)) {
            story.setPreviewImagePath(storyFileEntry.mFileName);
        }
    }

    public boolean isLoaded() {
        return this.mStories != null;
    }
}
