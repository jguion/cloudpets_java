package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.List;

@zziy
public class zzdw implements zzars {
    @Nullable
    private CustomTabsSession zzbiy;
    @Nullable
    private CustomTabsClient zzbiz;
    @Nullable
    private CustomTabsServiceConnection zzbja;
    @Nullable
    private zza zzbjb;

    public interface zza {
        void zzlh();

        void zzli();
    }

    public static boolean zzo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null || resolveActivity == null) {
            return false;
        }
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (resolveActivity.activityInfo.name.equals(((ResolveInfo) queryIntentActivities.get(i)).activityInfo.name)) {
                return resolveActivity.activityInfo.packageName.equals(zzarq.zzfc(context));
            }
        }
        return false;
    }

    public boolean mayLaunchUrl(Uri uri, Bundle bundle, List<Bundle> list) {
        if (this.zzbiz == null) {
            return false;
        }
        CustomTabsSession zzlf = zzlf();
        return zzlf != null ? zzlf.mayLaunchUrl(uri, bundle, list) : false;
    }

    public void zza(CustomTabsClient customTabsClient) {
        this.zzbiz = customTabsClient;
        this.zzbiz.warmup(0);
        if (this.zzbjb != null) {
            this.zzbjb.zzlh();
        }
    }

    public void zza(zza com_google_android_gms_internal_zzdw_zza) {
        this.zzbjb = com_google_android_gms_internal_zzdw_zza;
    }

    public void zzd(Activity activity) {
        if (this.zzbja != null) {
            activity.unbindService(this.zzbja);
            this.zzbiz = null;
            this.zzbiy = null;
            this.zzbja = null;
        }
    }

    public void zze(Activity activity) {
        if (this.zzbiz == null) {
            String zzfc = zzarq.zzfc(activity);
            if (zzfc != null) {
                this.zzbja = new zzarr(this);
                CustomTabsClient.bindCustomTabsService(activity, zzfc, this.zzbja);
            }
        }
    }

    @Nullable
    public CustomTabsSession zzlf() {
        if (this.zzbiz == null) {
            this.zzbiy = null;
        } else if (this.zzbiy == null) {
            this.zzbiy = this.zzbiz.newSession(null);
        }
        return this.zzbiy;
    }

    public void zzlg() {
        this.zzbiz = null;
        this.zzbiy = null;
        if (this.zzbjb != null) {
            this.zzbjb.zzli();
        }
    }
}
