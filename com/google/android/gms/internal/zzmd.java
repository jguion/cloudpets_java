package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zziy
@TargetApi(11)
public class zzmd extends zzlu {
    public zzmd(zzlt com_google_android_gms_internal_zzlt, boolean z) {
        super(com_google_android_gms_internal_zzlt, z);
    }

    protected WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        Exception e;
        String valueOf;
        if (webView instanceof zzlt) {
            zzlt com_google_android_gms_internal_zzlt = (zzlt) webView;
            if (this.zzcvg != null) {
                this.zzcvg.zzb(str, map);
            }
            if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
                return super.shouldInterceptRequest(webView, str);
            }
            if (com_google_android_gms_internal_zzlt.zzvr() != null) {
                com_google_android_gms_internal_zzlt.zzvr().zzov();
            }
            String str2 = com_google_android_gms_internal_zzlt.zzdt().zzaxj ? (String) zzdi.zzbbz.get() : com_google_android_gms_internal_zzlt.zzvv() ? (String) zzdi.zzbby.get() : (String) zzdi.zzbbx.get();
            try {
                return zzf(com_google_android_gms_internal_zzlt.getContext(), com_google_android_gms_internal_zzlt.zzvu().zzcs, str2);
            } catch (IOException e2) {
                e = e2;
                str2 = "Could not fetch MRAID JS. ";
                valueOf = String.valueOf(e.getMessage());
                zzb.zzdf(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            } catch (ExecutionException e3) {
                e = e3;
                str2 = "Could not fetch MRAID JS. ";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                zzb.zzdf(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            } catch (InterruptedException e4) {
                e = e4;
                str2 = "Could not fetch MRAID JS. ";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                zzb.zzdf(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            } catch (TimeoutException e5) {
                e = e5;
                str2 = "Could not fetch MRAID JS. ";
                valueOf = String.valueOf(e.getMessage());
                if (valueOf.length() == 0) {
                }
                zzb.zzdf(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
                return null;
            }
        }
        zzb.zzdf("Tried to intercept request from a WebView that wasn't an AdWebView.");
        return null;
    }

    protected WebResourceResponse zzf(Context context, String str, String str2) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Map hashMap = new HashMap();
        hashMap.put(HttpHeaders.USER_AGENT, zzu.zzfz().zzg(context, str));
        hashMap.put(HttpHeaders.CACHE_CONTROL, "max-stale=3600");
        String str3 = (String) new zzky(context).zzd(str2, hashMap).get(60, TimeUnit.SECONDS);
        return str3 == null ? null : new WebResourceResponse("application/javascript", Key.STRING_CHARSET_NAME, new ByteArrayInputStream(str3.getBytes(Key.STRING_CHARSET_NAME)));
    }
}
