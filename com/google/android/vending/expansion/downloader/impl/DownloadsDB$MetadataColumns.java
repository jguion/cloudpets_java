package com.google.android.vending.expansion.downloader.impl;

import android.provider.BaseColumns;
import com.android.vending.expansion.zipfile.APEZProvider;

public class DownloadsDB$MetadataColumns implements BaseColumns {
    public static final String APKVERSION = "APKVERSION";
    public static final String DOWNLOAD_STATUS = "DOWNLOADSTATUS";
    public static final String FLAGS = "DOWNLOADFLAGS";
    public static final String[][] SCHEMA;
    public static final String TABLE_NAME = "MetadataColumns";
    public static final String _ID = "MetadataColumns._id";

    static {
        r0 = new String[4][];
        r0[0] = new String[]{APEZProvider.FILEID, "INTEGER PRIMARY KEY"};
        r0[1] = new String[]{APKVERSION, "INTEGER"};
        r0[2] = new String[]{DOWNLOAD_STATUS, "INTEGER"};
        r0[3] = new String[]{FLAGS, "INTEGER"};
        SCHEMA = r0;
    }
}
