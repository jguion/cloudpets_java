package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zziy;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zziy
public final class zzad {
    public static final String DEVICE_ID_EMULATOR = zzm.zzjr().zzdc("emulator");
    private final boolean zzami;
    private final int zzawu;
    private final int zzawx;
    private final String zzawy;
    private final String zzaxa;
    private final Bundle zzaxc;
    private final String zzaxe;
    private final boolean zzaxg;
    private final Bundle zzayj;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzayk;
    private final SearchAdRequest zzayl;
    private final Set<String> zzaym;
    private final Set<String> zzayn;
    private final Date zzgn;
    private final Set<String> zzgp;
    private final Location zzgr;

    public static final class zza {
        private boolean zzami = false;
        private int zzawu = -1;
        private int zzawx = -1;
        private String zzawy;
        private String zzaxa;
        private final Bundle zzaxc = new Bundle();
        private String zzaxe;
        private boolean zzaxg;
        private final Bundle zzayj = new Bundle();
        private final HashSet<String> zzayo = new HashSet();
        private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzayp = new HashMap();
        private final HashSet<String> zzayq = new HashSet();
        private final HashSet<String> zzayr = new HashSet();
        private Date zzgn;
        private Location zzgr;

        public void setManualImpressionsEnabled(boolean z) {
            this.zzami = z;
        }

        @Deprecated
        public void zza(NetworkExtras networkExtras) {
            if (networkExtras instanceof AdMobExtras) {
                zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
            } else {
                this.zzayp.put(networkExtras.getClass(), networkExtras);
            }
        }

        public void zza(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzayj.putBundle(cls.getName(), bundle);
        }

        public void zza(Date date) {
            this.zzgn = date;
        }

        public void zzai(String str) {
            this.zzayo.add(str);
        }

        public void zzaj(String str) {
            this.zzayq.add(str);
        }

        public void zzak(String str) {
            this.zzayq.remove(str);
        }

        public void zzal(String str) {
            this.zzaxa = str;
        }

        public void zzam(String str) {
            this.zzawy = str;
        }

        public void zzan(String str) {
            this.zzaxe = str;
        }

        public void zzao(String str) {
            this.zzayr.add(str);
        }

        public void zzb(Location location) {
            this.zzgr = location;
        }

        public void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
            if (this.zzayj.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
                this.zzayj.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
            }
            this.zzayj.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
        }

        public void zzf(String str, String str2) {
            this.zzaxc.putString(str, str2);
        }

        public void zzo(boolean z) {
            this.zzawx = z ? 1 : 0;
        }

        public void zzp(boolean z) {
            this.zzaxg = z;
        }

        public void zzv(int i) {
            this.zzawu = i;
        }
    }

    public zzad(zza com_google_android_gms_ads_internal_client_zzad_zza) {
        this(com_google_android_gms_ads_internal_client_zzad_zza, null);
    }

    public zzad(zza com_google_android_gms_ads_internal_client_zzad_zza, SearchAdRequest searchAdRequest) {
        this.zzgn = com_google_android_gms_ads_internal_client_zzad_zza.zzgn;
        this.zzaxa = com_google_android_gms_ads_internal_client_zzad_zza.zzaxa;
        this.zzawu = com_google_android_gms_ads_internal_client_zzad_zza.zzawu;
        this.zzgp = Collections.unmodifiableSet(com_google_android_gms_ads_internal_client_zzad_zza.zzayo);
        this.zzgr = com_google_android_gms_ads_internal_client_zzad_zza.zzgr;
        this.zzami = com_google_android_gms_ads_internal_client_zzad_zza.zzami;
        this.zzayj = com_google_android_gms_ads_internal_client_zzad_zza.zzayj;
        this.zzayk = Collections.unmodifiableMap(com_google_android_gms_ads_internal_client_zzad_zza.zzayp);
        this.zzawy = com_google_android_gms_ads_internal_client_zzad_zza.zzawy;
        this.zzaxe = com_google_android_gms_ads_internal_client_zzad_zza.zzaxe;
        this.zzayl = searchAdRequest;
        this.zzawx = com_google_android_gms_ads_internal_client_zzad_zza.zzawx;
        this.zzaym = Collections.unmodifiableSet(com_google_android_gms_ads_internal_client_zzad_zza.zzayq);
        this.zzaxc = com_google_android_gms_ads_internal_client_zzad_zza.zzaxc;
        this.zzayn = Collections.unmodifiableSet(com_google_android_gms_ads_internal_client_zzad_zza.zzayr);
        this.zzaxg = com_google_android_gms_ads_internal_client_zzad_zza.zzaxg;
    }

    public Date getBirthday() {
        return this.zzgn;
    }

    public String getContentUrl() {
        return this.zzaxa;
    }

    public Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzayj.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        return bundle != null ? bundle.getBundle(cls.getName()) : null;
    }

    public Bundle getCustomTargeting() {
        return this.zzaxc;
    }

    public int getGender() {
        return this.zzawu;
    }

    public Set<String> getKeywords() {
        return this.zzgp;
    }

    public Location getLocation() {
        return this.zzgr;
    }

    public boolean getManualImpressionsEnabled() {
        return this.zzami;
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (NetworkExtras) this.zzayk.get(cls);
    }

    public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> cls) {
        return this.zzayj.getBundle(cls.getName());
    }

    public String getPublisherProvidedId() {
        return this.zzawy;
    }

    public boolean isDesignedForFamilies() {
        return this.zzaxg;
    }

    public boolean isTestDevice(Context context) {
        return this.zzaym.contains(zzm.zzjr().zzar(context));
    }

    public String zzjz() {
        return this.zzaxe;
    }

    public SearchAdRequest zzka() {
        return this.zzayl;
    }

    public Map<Class<? extends NetworkExtras>, NetworkExtras> zzkb() {
        return this.zzayk;
    }

    public Bundle zzkc() {
        return this.zzayj;
    }

    public int zzkd() {
        return this.zzawx;
    }

    public Set<String> zzke() {
        return this.zzayn;
    }
}
