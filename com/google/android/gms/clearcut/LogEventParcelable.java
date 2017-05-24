package com.google.android.gms.clearcut;

import android.os.Parcel;
import com.google.android.gms.clearcut.zzb.zzc;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzarp.zzd;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.Arrays;

public class LogEventParcelable extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();
    public PlayLoggerContext uc;
    public byte[] ud;
    public int[] ue;
    public String[] uf;
    public int[] ug;
    public byte[][] uh;
    public boolean ui;
    public final zzd uj;
    public final zzc uk;
    public final zzc ul;
    public final int versionCode;

    LogEventParcelable(int i, PlayLoggerContext playLoggerContext, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z) {
        this.versionCode = i;
        this.uc = playLoggerContext;
        this.ud = bArr;
        this.ue = iArr;
        this.uf = strArr;
        this.uj = null;
        this.uk = null;
        this.ul = null;
        this.ug = iArr2;
        this.uh = bArr2;
        this.ui = z;
    }

    public LogEventParcelable(PlayLoggerContext playLoggerContext, zzd com_google_android_gms_internal_zzarp_zzd, zzc com_google_android_gms_clearcut_zzb_zzc, zzc com_google_android_gms_clearcut_zzb_zzc2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, boolean z) {
        this.versionCode = 1;
        this.uc = playLoggerContext;
        this.uj = com_google_android_gms_internal_zzarp_zzd;
        this.uk = com_google_android_gms_clearcut_zzb_zzc;
        this.ul = com_google_android_gms_clearcut_zzb_zzc2;
        this.ue = iArr;
        this.uf = strArr;
        this.ug = iArr2;
        this.uh = bArr;
        this.ui = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogEventParcelable)) {
            return false;
        }
        LogEventParcelable logEventParcelable = (LogEventParcelable) obj;
        return this.versionCode == logEventParcelable.versionCode && zzab.equal(this.uc, logEventParcelable.uc) && Arrays.equals(this.ud, logEventParcelable.ud) && Arrays.equals(this.ue, logEventParcelable.ue) && Arrays.equals(this.uf, logEventParcelable.uf) && zzab.equal(this.uj, logEventParcelable.uj) && zzab.equal(this.uk, logEventParcelable.uk) && zzab.equal(this.ul, logEventParcelable.ul) && Arrays.equals(this.ug, logEventParcelable.ug) && Arrays.deepEquals(this.uh, logEventParcelable.uh) && this.ui == logEventParcelable.ui;
    }

    public int hashCode() {
        return zzab.hashCode(Integer.valueOf(this.versionCode), this.uc, this.ud, this.ue, this.uf, this.uj, this.uk, this.ul, this.ug, this.uh, Boolean.valueOf(this.ui));
    }

    public String toString() {
        return "LogEventParcelable[" + this.versionCode + ", " + this.uc + ", " + "LogEventBytes: " + (this.ud == null ? null : new String(this.ud)) + ", " + "TestCodes: " + Arrays.toString(this.ue) + ", " + "MendelPackages: " + Arrays.toString(this.uf) + ", " + "LogEvent: " + this.uj + ", " + "ExtensionProducer: " + this.uk + ", " + "VeProducer: " + this.ul + ", " + "ExperimentIDs: " + Arrays.toString(this.ug) + ", " + "ExperimentTokens: " + Arrays.toString(this.uh) + ", " + "AddPhenotypeExperimentTokens: " + this.ui + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
