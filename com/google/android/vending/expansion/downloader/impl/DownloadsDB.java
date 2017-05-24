package com.google.android.vending.expansion.downloader.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DownloadsDB {
    private static final int CONTROL_IDX = 7;
    private static final int CURRENTBYTES_IDX = 4;
    private static final String DATABASE_NAME = "DownloadsDB";
    private static final int DATABASE_VERSION = 7;
    private static final String[] DC_PROJECTION = new String[]{DownloadsDB$DownloadColumns.FILENAME, DownloadsDB$DownloadColumns.URI, DownloadsDB$DownloadColumns.ETAG, DownloadsDB$DownloadColumns.TOTALBYTES, DownloadsDB$DownloadColumns.CURRENTBYTES, DownloadsDB$DownloadColumns.LASTMOD, DownloadsDB$DownloadColumns.STATUS, DownloadsDB$DownloadColumns.CONTROL, DownloadsDB$DownloadColumns.NUM_FAILED, DownloadsDB$DownloadColumns.RETRY_AFTER, DownloadsDB$DownloadColumns.REDIRECT_COUNT, DownloadsDB$DownloadColumns.INDEX};
    private static final int ETAG_IDX = 2;
    private static final int FILENAME_IDX = 0;
    private static final int INDEX_IDX = 11;
    private static final int LASTMOD_IDX = 5;
    public static final String LOG_TAG = DownloadsDB.class.getName();
    private static final int NUM_FAILED_IDX = 8;
    private static final int REDIRECT_COUNT_IDX = 10;
    private static final int RETRY_AFTER_IDX = 9;
    private static final int STATUS_IDX = 6;
    private static final int TOTALBYTES_IDX = 3;
    private static final int URI_IDX = 1;
    private static DownloadsDB mDownloadsDB;
    int mFlags;
    SQLiteStatement mGetDownloadByIndex;
    final SQLiteOpenHelper mHelper;
    long mMetadataRowID = -1;
    int mStatus = -1;
    SQLiteStatement mUpdateCurrentBytes;
    int mVersionCode = -1;

    public static synchronized DownloadsDB getDB(Context paramContext) {
        DownloadsDB downloadsDB;
        synchronized (DownloadsDB.class) {
            if (mDownloadsDB == null) {
                downloadsDB = new DownloadsDB(paramContext);
            } else {
                downloadsDB = mDownloadsDB;
            }
        }
        return downloadsDB;
    }

    private SQLiteStatement getDownloadByIndexStatement() {
        if (this.mGetDownloadByIndex == null) {
            this.mGetDownloadByIndex = this.mHelper.getReadableDatabase().compileStatement("SELECT _id FROM DownloadColumns WHERE FILEIDX = ?");
        }
        return this.mGetDownloadByIndex;
    }

    private SQLiteStatement getUpdateCurrentBytesStatement() {
        if (this.mUpdateCurrentBytes == null) {
            this.mUpdateCurrentBytes = this.mHelper.getReadableDatabase().compileStatement("UPDATE DownloadColumns SET CURRENTBYTES = ? WHERE FILEIDX = ?");
        }
        return this.mUpdateCurrentBytes;
    }

    private DownloadsDB(Context paramContext) {
        this.mHelper = new DownloadsContentDBHelper(paramContext);
        Cursor cur = this.mHelper.getReadableDatabase().rawQuery("SELECT APKVERSION,_id,DOWNLOADSTATUS,DOWNLOADFLAGS FROM MetadataColumns LIMIT 1", null);
        if (cur != null && cur.moveToFirst()) {
            this.mVersionCode = cur.getInt(0);
            this.mMetadataRowID = cur.getLong(1);
            this.mStatus = cur.getInt(2);
            this.mFlags = cur.getInt(3);
            cur.close();
        }
        mDownloadsDB = this;
    }

    protected DownloadInfo getDownloadInfoByFileName(String fileName) {
        Cursor itemcur = null;
        try {
            itemcur = this.mHelper.getReadableDatabase().query(DownloadsDB$DownloadColumns.TABLE_NAME, DC_PROJECTION, "FN = ?", new String[]{fileName}, null, null, null);
            if (itemcur == null || !itemcur.moveToFirst()) {
                if (itemcur != null) {
                    itemcur.close();
                }
                return null;
            }
            DownloadInfo downloadInfoFromCursor = getDownloadInfoFromCursor(itemcur);
            return downloadInfoFromCursor;
        } finally {
            if (itemcur != null) {
                itemcur.close();
            }
        }
    }

    public long getIDForDownloadInfo(DownloadInfo di) {
        return getIDByIndex(di.mIndex);
    }

    public long getIDByIndex(int index) {
        SQLiteStatement downloadByIndex = getDownloadByIndexStatement();
        downloadByIndex.clearBindings();
        downloadByIndex.bindLong(1, (long) index);
        try {
            return downloadByIndex.simpleQueryForLong();
        } catch (SQLiteDoneException e) {
            return -1;
        }
    }

    public void updateDownloadCurrentBytes(DownloadInfo di) {
        SQLiteStatement downloadCurrentBytes = getUpdateCurrentBytesStatement();
        downloadCurrentBytes.clearBindings();
        downloadCurrentBytes.bindLong(1, di.mCurrentBytes);
        downloadCurrentBytes.bindLong(2, (long) di.mIndex);
        downloadCurrentBytes.execute();
    }

    public void close() {
        this.mHelper.close();
    }

    public boolean updateDownload(DownloadInfo di) {
        ContentValues cv = new ContentValues();
        cv.put(DownloadsDB$DownloadColumns.INDEX, Integer.valueOf(di.mIndex));
        cv.put(DownloadsDB$DownloadColumns.FILENAME, di.mFileName);
        cv.put(DownloadsDB$DownloadColumns.URI, di.mUri);
        cv.put(DownloadsDB$DownloadColumns.ETAG, di.mETag);
        cv.put(DownloadsDB$DownloadColumns.TOTALBYTES, Long.valueOf(di.mTotalBytes));
        cv.put(DownloadsDB$DownloadColumns.CURRENTBYTES, Long.valueOf(di.mCurrentBytes));
        cv.put(DownloadsDB$DownloadColumns.LASTMOD, Long.valueOf(di.mLastMod));
        cv.put(DownloadsDB$DownloadColumns.STATUS, Integer.valueOf(di.mStatus));
        cv.put(DownloadsDB$DownloadColumns.CONTROL, Integer.valueOf(di.mControl));
        cv.put(DownloadsDB$DownloadColumns.NUM_FAILED, Integer.valueOf(di.mNumFailed));
        cv.put(DownloadsDB$DownloadColumns.RETRY_AFTER, Integer.valueOf(di.mRetryAfter));
        cv.put(DownloadsDB$DownloadColumns.REDIRECT_COUNT, Integer.valueOf(di.mRedirectCount));
        return updateDownload(di, cv);
    }

    public boolean updateDownload(DownloadInfo di, ContentValues cv) {
        long id = di == null ? -1 : getIDForDownloadInfo(di);
        try {
            SQLiteDatabase sqldb = this.mHelper.getWritableDatabase();
            if (id != -1) {
                return 1 != sqldb.update(DownloadsDB$DownloadColumns.TABLE_NAME, cv, new StringBuilder().append("DownloadColumns._id = ").append(id).toString(), null) ? false : false;
            } else {
                return -1 != sqldb.insert(DownloadsDB$DownloadColumns.TABLE_NAME, DownloadsDB$DownloadColumns.URI, cv);
            }
        } catch (SQLiteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getLastCheckedVersionCode() {
        return this.mVersionCode;
    }

    public boolean isDownloadRequired() {
        boolean z = true;
        Cursor cur = this.mHelper.getReadableDatabase().rawQuery("SELECT Count(*) FROM DownloadColumns WHERE STATUS <> 0", null);
        if (cur != null) {
            try {
                if (cur.moveToFirst()) {
                    if (cur.getInt(0) != 0) {
                        z = false;
                    }
                    if (cur != null) {
                        cur.close();
                    }
                    return z;
                }
            } catch (Throwable th) {
                if (cur != null) {
                    cur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return z;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean updateFlags(int flags) {
        if (this.mFlags == flags) {
            return true;
        }
        ContentValues cv = new ContentValues();
        cv.put(DownloadsDB$MetadataColumns.FLAGS, Integer.valueOf(flags));
        if (!updateMetadata(cv)) {
            return false;
        }
        this.mFlags = flags;
        return true;
    }

    public boolean updateStatus(int status) {
        if (this.mStatus == status) {
            return true;
        }
        ContentValues cv = new ContentValues();
        cv.put(DownloadsDB$MetadataColumns.DOWNLOAD_STATUS, Integer.valueOf(status));
        if (!updateMetadata(cv)) {
            return false;
        }
        this.mStatus = status;
        return true;
    }

    public boolean updateMetadata(ContentValues cv) {
        SQLiteDatabase sqldb = this.mHelper.getWritableDatabase();
        if (-1 == this.mMetadataRowID) {
            long newID = sqldb.insert(DownloadsDB$MetadataColumns.TABLE_NAME, DownloadsDB$MetadataColumns.APKVERSION, cv);
            if (-1 == newID) {
                return false;
            }
            this.mMetadataRowID = newID;
        } else if (sqldb.update(DownloadsDB$MetadataColumns.TABLE_NAME, cv, "_id = " + this.mMetadataRowID, null) == 0) {
            return false;
        }
        return true;
    }

    public boolean updateMetadata(int apkVersion, int downloadStatus) {
        ContentValues cv = new ContentValues();
        cv.put(DownloadsDB$MetadataColumns.APKVERSION, Integer.valueOf(apkVersion));
        cv.put(DownloadsDB$MetadataColumns.DOWNLOAD_STATUS, Integer.valueOf(downloadStatus));
        if (!updateMetadata(cv)) {
            return false;
        }
        this.mVersionCode = apkVersion;
        this.mStatus = downloadStatus;
        return true;
    }

    public boolean updateFromDb(DownloadInfo di) {
        Cursor cur = null;
        try {
            cur = this.mHelper.getReadableDatabase().query(DownloadsDB$DownloadColumns.TABLE_NAME, DC_PROJECTION, "FN= ?", new String[]{di.mFileName}, null, null, null);
            if (cur == null || !cur.moveToFirst()) {
                if (cur != null) {
                    cur.close();
                }
                return false;
            }
            setDownloadInfoFromCursor(di, cur);
            return true;
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
    }

    public void setDownloadInfoFromCursor(DownloadInfo di, Cursor cur) {
        di.mUri = cur.getString(1);
        di.mETag = cur.getString(2);
        di.mTotalBytes = cur.getLong(3);
        di.mCurrentBytes = cur.getLong(4);
        di.mLastMod = cur.getLong(5);
        di.mStatus = cur.getInt(6);
        di.mControl = cur.getInt(7);
        di.mNumFailed = cur.getInt(8);
        di.mRetryAfter = cur.getInt(9);
        di.mRedirectCount = cur.getInt(10);
    }

    public DownloadInfo getDownloadInfoFromCursor(Cursor cur) {
        DownloadInfo di = new DownloadInfo(cur.getInt(11), cur.getString(0), getClass().getPackage().getName());
        setDownloadInfoFromCursor(di, cur);
        return di;
    }

    public DownloadInfo[] getDownloads() {
        DownloadInfo[] retInfos = null;
        Cursor cur = null;
        try {
            cur = this.mHelper.getReadableDatabase().query(DownloadsDB$DownloadColumns.TABLE_NAME, DC_PROJECTION, null, null, null, null, null);
            if (cur == null || !cur.moveToFirst()) {
                if (cur != null) {
                    cur.close();
                }
                return retInfos;
            }
            retInfos = new DownloadInfo[cur.getCount()];
            int idx = 0;
            while (true) {
                int idx2 = idx + 1;
                retInfos[idx] = getDownloadInfoFromCursor(cur);
                if (!cur.moveToNext()) {
                    break;
                }
                idx = idx2;
            }
            return retInfos;
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
    }
}
