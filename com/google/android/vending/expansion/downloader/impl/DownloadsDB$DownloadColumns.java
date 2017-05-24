package com.google.android.vending.expansion.downloader.impl;

import android.provider.BaseColumns;
import com.android.vending.expansion.zipfile.APEZProvider;

public class DownloadsDB$DownloadColumns implements BaseColumns {
    public static final String CONTROL = "CONTROL";
    public static final String CURRENTBYTES = "CURRENTBYTES";
    public static final String ETAG = "ETAG";
    public static final String FILENAME = "FN";
    public static final String INDEX = "FILEIDX";
    public static final String LASTMOD = "LASTMOD";
    public static final String NUM_FAILED = "FAILCOUNT";
    public static final String REDIRECT_COUNT = "REDIRECTCOUNT";
    public static final String RETRY_AFTER = "RETRYAFTER";
    public static final String[][] SCHEMA;
    public static final String STATUS = "STATUS";
    public static final String TABLE_NAME = "DownloadColumns";
    public static final String TOTALBYTES = "TOTALBYTES";
    public static final String URI = "URI";
    public static final String _ID = "DownloadColumns._id";

    static {
        String[][] strArr = new String[13][];
        strArr[0] = new String[]{APEZProvider.FILEID, "INTEGER PRIMARY KEY"};
        strArr[1] = new String[]{INDEX, "INTEGER UNIQUE"};
        strArr[2] = new String[]{URI, "TEXT"};
        strArr[3] = new String[]{FILENAME, "TEXT UNIQUE"};
        strArr[4] = new String[]{ETAG, "TEXT"};
        strArr[5] = new String[]{TOTALBYTES, "INTEGER"};
        strArr[6] = new String[]{CURRENTBYTES, "INTEGER"};
        strArr[7] = new String[]{LASTMOD, "INTEGER"};
        strArr[8] = new String[]{STATUS, "INTEGER"};
        strArr[9] = new String[]{CONTROL, "INTEGER"};
        strArr[10] = new String[]{NUM_FAILED, "INTEGER"};
        strArr[11] = new String[]{RETRY_AFTER, "INTEGER"};
        strArr[12] = new String[]{REDIRECT_COUNT, "INTEGER"};
        SCHEMA = strArr;
    }
}
