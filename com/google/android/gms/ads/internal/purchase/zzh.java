package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zziy;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@zziy
public class zzh {
    private static final Object zzakd = new Object();
    private static final String zzccd = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[]{"InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time"});
    private static zzh zzccf;
    private final zza zzcce;

    public class zza extends SQLiteOpenHelper {
        final /* synthetic */ zzh zzccg;

        public zza(zzh com_google_android_gms_ads_internal_purchase_zzh, Context context, String str) {
            this.zzccg = com_google_android_gms_ads_internal_purchase_zzh;
            super(context, str, null, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(zzh.zzccd);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            zzb.zzde("Database updated from version " + i + " to version " + i2);
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            onCreate(sQLiteDatabase);
        }
    }

    zzh(Context context) {
        this.zzcce = new zza(this, context, "google_inapp_purchase.db");
    }

    public static zzh zzs(Context context) {
        zzh com_google_android_gms_ads_internal_purchase_zzh;
        synchronized (zzakd) {
            if (zzccf == null) {
                zzccf = new zzh(context);
            }
            com_google_android_gms_ads_internal_purchase_zzh = zzccf;
        }
        return com_google_android_gms_ads_internal_purchase_zzh;
    }

    public int getRecordCount() {
        Cursor cursor = null;
        int i = 0;
        synchronized (zzakd) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
            } else {
                try {
                    cursor = writableDatabase.rawQuery("select count(*) from InAppPurchase", null);
                    if (cursor.moveToFirst()) {
                        i = cursor.getInt(0);
                        if (cursor != null) {
                            cursor.close();
                        }
                    } else {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } catch (SQLiteException e) {
                    String str = "Error getting record count";
                    String valueOf = String.valueOf(e.getMessage());
                    zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }
        return i;
    }

    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzcce.getWritableDatabase();
        } catch (SQLiteException e) {
            zzb.zzdf("Error opening writable conversion tracking database");
            return null;
        }
    }

    public zzf zza(Cursor cursor) {
        return cursor == null ? null : new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    public void zza(zzf com_google_android_gms_ads_internal_purchase_zzf) {
        if (com_google_android_gms_ads_internal_purchase_zzf != null) {
            synchronized (zzakd) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase == null) {
                    return;
                }
                writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", new Object[]{"purchase_id", Long.valueOf(com_google_android_gms_ads_internal_purchase_zzf.zzcby)}), null);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(com.google.android.gms.ads.internal.purchase.zzf r7) {
        /*
        r6 = this;
        if (r7 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = zzakd;
        monitor-enter(r1);
        r0 = r6.getWritableDatabase();	 Catch:{ all -> 0x000e }
        if (r0 != 0) goto L_0x0011;
    L_0x000c:
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0002;
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        throw r0;
    L_0x0011:
        r2 = new android.content.ContentValues;	 Catch:{ all -> 0x000e }
        r2.<init>();	 Catch:{ all -> 0x000e }
        r3 = "product_id";
        r4 = r7.zzcca;	 Catch:{ all -> 0x000e }
        r2.put(r3, r4);	 Catch:{ all -> 0x000e }
        r3 = "developer_payload";
        r4 = r7.zzcbz;	 Catch:{ all -> 0x000e }
        r2.put(r3, r4);	 Catch:{ all -> 0x000e }
        r3 = "record_time";
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x000e }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x000e }
        r2.put(r3, r4);	 Catch:{ all -> 0x000e }
        r3 = "InAppPurchase";
        r4 = 0;
        r2 = r0.insert(r3, r4, r2);	 Catch:{ all -> 0x000e }
        r7.zzcby = r2;	 Catch:{ all -> 0x000e }
        r0 = r6.getRecordCount();	 Catch:{ all -> 0x000e }
        r2 = (long) r0;	 Catch:{ all -> 0x000e }
        r4 = 20000; // 0x4e20 float:2.8026E-41 double:9.8813E-320;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x0048;
    L_0x0045:
        r6.zzqr();	 Catch:{ all -> 0x000e }
    L_0x0048:
        monitor-exit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzh.zzb(com.google.android.gms.ads.internal.purchase.zzf):void");
    }

    public List<zzf> zzg(long j) {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        synchronized (zzakd) {
            List<zzf> linkedList = new LinkedList();
            if (j <= 0) {
                return linkedList;
            }
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return linkedList;
            }
            try {
                query = writableDatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", String.valueOf(j));
                try {
                    if (query.moveToFirst()) {
                        do {
                            linkedList.add(zza(query));
                        } while (query.moveToNext());
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    try {
                        str = "Error extracing purchase info: ";
                        valueOf = String.valueOf(e.getMessage());
                        zzb.zzdf(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                        if (query != null) {
                            query.close();
                        }
                        return linkedList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            } catch (SQLiteException e3) {
                e = e3;
                query = null;
                str = "Error extracing purchase info: ";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                zzb.zzdf(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                if (query != null) {
                    query.close();
                }
                return linkedList;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }

    public void zzqr() {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        synchronized (zzakd) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return;
            }
            try {
                query = writableDatabase.query("InAppPurchase", null, null, null, null, null, "record_time ASC", "1");
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            zza(zza(query));
                        }
                    } catch (SQLiteException e2) {
                        e = e2;
                        try {
                            str = "Error remove oldest record";
                            valueOf = String.valueOf(e.getMessage());
                            zzb.zzdf(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                            if (query != null) {
                                query.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e3) {
                e = e3;
                query = null;
                str = "Error remove oldest record";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                zzb.zzdf(valueOf.length() == 0 ? new String(str) : str.concat(valueOf));
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
    }
}
