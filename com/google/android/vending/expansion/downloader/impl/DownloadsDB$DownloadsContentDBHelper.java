package com.google.android.vending.expansion.downloader.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

protected class DownloadsDB$DownloadsContentDBHelper extends SQLiteOpenHelper {
    private static final String[][][] sSchemas = new String[][][]{DownloadsDB$DownloadColumns.SCHEMA, DownloadsDB$MetadataColumns.SCHEMA};
    private static final String[] sTables = new String[]{DownloadsDB$DownloadColumns.TABLE_NAME, DownloadsDB$MetadataColumns.TABLE_NAME};

    DownloadsDB$DownloadsContentDBHelper(Context paramContext) {
        super(paramContext, "DownloadsDB", null, 7);
    }

    private String createTableQueryFromArray(String paramString, String[][] paramArrayOfString) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("CREATE TABLE ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" (");
        for (String[] arrayOfString : paramArrayOfString) {
            localStringBuilder.append(' ');
            localStringBuilder.append(arrayOfString[0]);
            localStringBuilder.append(' ');
            localStringBuilder.append(arrayOfString[1]);
            localStringBuilder.append(',');
        }
        localStringBuilder.setLength(localStringBuilder.length() - 1);
        localStringBuilder.append(");");
        return localStringBuilder.toString();
    }

    private void dropTables(SQLiteDatabase paramSQLiteDatabase) {
        for (String table : sTables) {
            try {
                paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table);
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        int numSchemas = sSchemas.length;
        int i = 0;
        while (i < numSchemas) {
            try {
                paramSQLiteDatabase.execSQL(createTableQueryFromArray(sTables[i], sSchemas[i]));
                i++;
            } catch (Exception localException) {
                while (true) {
                    localException.printStackTrace();
                }
            }
        }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
        Log.w(DownloadsDB$DownloadsContentDBHelper.class.getName(), "Upgrading database from version " + paramInt1 + " to " + paramInt2 + ", which will destroy all old data");
        dropTables(paramSQLiteDatabase);
        onCreate(paramSQLiteDatabase);
    }
}
