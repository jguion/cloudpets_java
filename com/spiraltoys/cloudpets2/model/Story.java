package com.spiraltoys.cloudpets2.model;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Story implements Serializable {
    private String mAuthor;
    @SerializedName("authorStringResource")
    private String mAuthorStringResourceName;
    @SerializedName("pageStringsResource")
    private String mPageStringsResourceName;
    private ArrayList<Page> mPages = new ArrayList();
    private String mPreviewImagePath;
    @SerializedName("sortPriority")
    private int mSortPriority;
    private String mTitle;
    @SerializedName("titleStringResource")
    private String mTitleStringResourceName;
    @SerializedName("unitySceneName")
    private String mUnitySceneName;

    public static class Page implements Serializable {
        private String mAudioPath;
        private String mImagePath;
        private String mText;

        protected Page() {
        }

        public String getText() {
            return this.mText;
        }

        protected void setText(String text) {
            this.mText = text;
        }

        public String getImagePath() {
            return this.mImagePath;
        }

        public InputStream getImageInputStream(Context context) {
            try {
                return ExpansionUtils.getMainExpansionDescriptor().getZipResourceFile(context).getInputStream(this.mImagePath);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void setImagePath(String imagePath) {
            this.mImagePath = imagePath;
        }

        public String getAudioPath() {
            return this.mAudioPath;
        }

        protected void setAudioPath(String audioPath) {
            this.mAudioPath = audioPath;
        }
    }

    protected void setPages(ArrayList<Page> pages) {
        this.mPages = pages;
    }

    protected void loadResources(Context context) {
        this.mTitle = context.getString(getTitleStringResourceId(context));
        this.mAuthor = context.getString(getAuthorStringResourceId(context));
    }

    protected int getTitleStringResourceId(Context context) {
        return context.getResources().getIdentifier(this.mTitleStringResourceName, "string", context.getPackageName());
    }

    protected int getAuthorStringResourceId(Context context) {
        return context.getResources().getIdentifier(this.mAuthorStringResourceName, "string", context.getPackageName());
    }

    protected int getPageStringsArrayResourceId(Context context) {
        return context.getResources().getIdentifier(this.mPageStringsResourceName, "array", context.getPackageName());
    }

    public String getUnitySceneName() {
        return this.mUnitySceneName;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Page getPage(int pageNumber) {
        return (Page) this.mPages.get(pageNumber);
    }

    public List<Page> getPages() {
        return new ArrayList(this.mPages);
    }

    public int getPageCount() {
        return this.mPages.size();
    }

    public String getPreviewImagePath() {
        return this.mPreviewImagePath;
    }

    public int getSortPriority() {
        return this.mSortPriority;
    }

    protected void setPreviewImagePath(String previewImagePath) {
        this.mPreviewImagePath = previewImagePath;
    }
}
