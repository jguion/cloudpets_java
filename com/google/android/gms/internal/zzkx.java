package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzab;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzkx {
    private final String[] zzcrz;
    private final double[] zzcsa;
    private final double[] zzcsb;
    private final int[] zzcsc;
    private int zzcsd;

    public static class zza {
        public final int count;
        public final String name;
        public final double zzcse;
        public final double zzcsf;
        public final double zzcsg;

        public zza(String str, double d, double d2, double d3, int i) {
            this.name = str;
            this.zzcsf = d;
            this.zzcse = d2;
            this.zzcsg = d3;
            this.count = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzkx_zza = (zza) obj;
            return zzab.equal(this.name, com_google_android_gms_internal_zzkx_zza.name) && this.zzcse == com_google_android_gms_internal_zzkx_zza.zzcse && this.zzcsf == com_google_android_gms_internal_zzkx_zza.zzcsf && this.count == com_google_android_gms_internal_zzkx_zza.count && Double.compare(this.zzcsg, com_google_android_gms_internal_zzkx_zza.zzcsg) == 0;
        }

        public int hashCode() {
            return zzab.hashCode(this.name, Double.valueOf(this.zzcse), Double.valueOf(this.zzcsf), Double.valueOf(this.zzcsg), Integer.valueOf(this.count));
        }

        public String toString() {
            return zzab.zzx(this).zzg(FriendRecord.NAME, this.name).zzg("minBound", Double.valueOf(this.zzcsf)).zzg("maxBound", Double.valueOf(this.zzcse)).zzg("percent", Double.valueOf(this.zzcsg)).zzg("count", Integer.valueOf(this.count)).toString();
        }
    }

    public static class zzb {
        private final List<String> zzcsh = new ArrayList();
        private final List<Double> zzcsi = new ArrayList();
        private final List<Double> zzcsj = new ArrayList();

        public zzb zza(String str, double d, double d2) {
            int i = 0;
            while (i < this.zzcsh.size()) {
                double doubleValue = ((Double) this.zzcsj.get(i)).doubleValue();
                double doubleValue2 = ((Double) this.zzcsi.get(i)).doubleValue();
                if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                    break;
                }
                i++;
            }
            this.zzcsh.add(i, str);
            this.zzcsj.add(i, Double.valueOf(d));
            this.zzcsi.add(i, Double.valueOf(d2));
            return this;
        }

        public zzkx zzuw() {
            return new zzkx();
        }
    }

    private zzkx(zzb com_google_android_gms_internal_zzkx_zzb) {
        int size = com_google_android_gms_internal_zzkx_zzb.zzcsi.size();
        this.zzcrz = (String[]) com_google_android_gms_internal_zzkx_zzb.zzcsh.toArray(new String[size]);
        this.zzcsa = zzn(com_google_android_gms_internal_zzkx_zzb.zzcsi);
        this.zzcsb = zzn(com_google_android_gms_internal_zzkx_zzb.zzcsj);
        this.zzcsc = new int[size];
        this.zzcsd = 0;
    }

    private double[] zzn(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public List<zza> getBuckets() {
        List<zza> arrayList = new ArrayList(this.zzcrz.length);
        for (int i = 0; i < this.zzcrz.length; i++) {
            arrayList.add(new zza(this.zzcrz[i], this.zzcsb[i], this.zzcsa[i], ((double) this.zzcsc[i]) / ((double) this.zzcsd), this.zzcsc[i]));
        }
        return arrayList;
    }

    public void zza(double d) {
        this.zzcsd++;
        int i = 0;
        while (i < this.zzcsb.length) {
            if (this.zzcsb[i] <= d && d < this.zzcsa[i]) {
                int[] iArr = this.zzcsc;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzcsb[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
