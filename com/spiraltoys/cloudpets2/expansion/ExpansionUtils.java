package com.spiraltoys.cloudpets2.expansion;

import android.content.Context;
import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.google.android.vending.expansion.downloader.Helpers;
import com.spiraltoys.cloudpets2.BuildConfig;
import java.io.IOException;

public class ExpansionUtils {
    public static final ExpansionFileDescriptor[] EXPANSION_FILE_DESCRIPTORS = new ExpansionFileDescriptor[]{new ExpansionFileDescriptor(true, 89, BuildConfig.OBB_MAIN_SIZE)};

    public static class ExpansionFileDescriptor {
        private final long mFileSize;
        private final int mFileVersion;
        private final boolean mIsMain;
        private ZipResourceFile mZipResourceFile;

        ExpansionFileDescriptor(boolean isMain, int fileVersion, long fileSize) {
            this.mIsMain = isMain;
            this.mFileVersion = fileVersion;
            this.mFileSize = fileSize;
        }

        public boolean isMain() {
            return this.mIsMain;
        }

        public int getFileVersion() {
            return this.mFileVersion;
        }

        public long getFileSize() {
            return this.mFileSize;
        }

        public int getMainVersion() {
            return isMain() ? getFileVersion() : 0;
        }

        public int getPatchVersion() {
            return isMain() ? 0 : getFileVersion();
        }

        public ZipResourceFile getZipResourceFile(Context context) throws IOException {
            if (this.mZipResourceFile == null) {
                this.mZipResourceFile = APKExpansionSupport.getAPKExpansionZipFile(context, getMainVersion(), getPatchVersion());
            }
            return this.mZipResourceFile;
        }
    }

    public static boolean expansionFilesDelivered(Context context) {
        for (ExpansionFileDescriptor expansionFileDescriptor : EXPANSION_FILE_DESCRIPTORS) {
            if (!Helpers.doesFileExist(context, Helpers.getExpansionAPKFileName(context, expansionFileDescriptor.mIsMain, expansionFileDescriptor.mFileVersion), expansionFileDescriptor.mFileSize, false)) {
                return false;
            }
        }
        return true;
    }

    public static ExpansionFileDescriptor getMainExpansionDescriptor() {
        return EXPANSION_FILE_DESCRIPTORS[0];
    }
}
