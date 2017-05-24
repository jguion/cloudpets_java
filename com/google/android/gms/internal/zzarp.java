package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.parse.ParseException;
import java.io.IOException;
import java.util.Arrays;

public interface zzarp {

    public static final class zza extends zzare<zza> implements Cloneable {
        public String[] bqP;
        public String[] bqQ;
        public int[] bqR;
        public long[] bqS;
        public long[] bqT;

        public zza() {
            dd();
        }

        public /* synthetic */ zzare cP() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public /* synthetic */ zzark cQ() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return de();
        }

        public zza dd() {
            this.bqP = zzarn.bqK;
            this.bqQ = zzarn.bqK;
            this.bqR = zzarn.bqF;
            this.bqS = zzarn.bqG;
            this.bqT = zzarn.bqG;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }

        public zza de() {
            try {
                zza com_google_android_gms_internal_zzarp_zza = (zza) super.cP();
                if (this.bqP != null && this.bqP.length > 0) {
                    com_google_android_gms_internal_zzarp_zza.bqP = (String[]) this.bqP.clone();
                }
                if (this.bqQ != null && this.bqQ.length > 0) {
                    com_google_android_gms_internal_zzarp_zza.bqQ = (String[]) this.bqQ.clone();
                }
                if (this.bqR != null && this.bqR.length > 0) {
                    com_google_android_gms_internal_zzarp_zza.bqR = (int[]) this.bqR.clone();
                }
                if (this.bqS != null && this.bqS.length > 0) {
                    com_google_android_gms_internal_zzarp_zza.bqS = (long[]) this.bqS.clone();
                }
                if (this.bqT != null && this.bqT.length > 0) {
                    com_google_android_gms_internal_zzarp_zza.bqT = (long[]) this.bqT.clone();
                }
                return com_google_android_gms_internal_zzarp_zza;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzarp_zza = (zza) obj;
            return (zzari.equals(this.bqP, com_google_android_gms_internal_zzarp_zza.bqP) && zzari.equals(this.bqQ, com_google_android_gms_internal_zzarp_zza.bqQ) && zzari.equals(this.bqR, com_google_android_gms_internal_zzarp_zza.bqR) && zzari.equals(this.bqS, com_google_android_gms_internal_zzarp_zza.bqS) && zzari.equals(this.bqT, com_google_android_gms_internal_zzarp_zza.bqT)) ? (this.bqv == null || this.bqv.isEmpty()) ? com_google_android_gms_internal_zzarp_zza.bqv == null || com_google_android_gms_internal_zzarp_zza.bqv.isEmpty() : this.bqv.equals(com_google_android_gms_internal_zzarp_zza.bqv) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + zzari.hashCode(this.bqP)) * 31) + zzari.hashCode(this.bqQ)) * 31) + zzari.hashCode(this.bqR)) * 31) + zzari.hashCode(this.bqS)) * 31) + zzari.hashCode(this.bqT)) * 31;
            int hashCode2 = (this.bqv == null || this.bqv.isEmpty()) ? 0 : this.bqv.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzard com_google_android_gms_internal_zzard) throws IOException {
            int i = 0;
            if (this.bqP != null && this.bqP.length > 0) {
                for (String str : this.bqP) {
                    if (str != null) {
                        com_google_android_gms_internal_zzard.zzr(1, str);
                    }
                }
            }
            if (this.bqQ != null && this.bqQ.length > 0) {
                for (String str2 : this.bqQ) {
                    if (str2 != null) {
                        com_google_android_gms_internal_zzard.zzr(2, str2);
                    }
                }
            }
            if (this.bqR != null && this.bqR.length > 0) {
                for (int zzae : this.bqR) {
                    com_google_android_gms_internal_zzard.zzae(3, zzae);
                }
            }
            if (this.bqS != null && this.bqS.length > 0) {
                for (long zzb : this.bqS) {
                    com_google_android_gms_internal_zzard.zzb(4, zzb);
                }
            }
            if (this.bqT != null && this.bqT.length > 0) {
                while (i < this.bqT.length) {
                    com_google_android_gms_internal_zzard.zzb(5, this.bqT[i]);
                    i++;
                }
            }
            super.zza(com_google_android_gms_internal_zzard);
        }

        public /* synthetic */ zzark zzb(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            return zzcm(com_google_android_gms_internal_zzarc);
        }

        public zza zzcm(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            while (true) {
                int cw = com_google_android_gms_internal_zzarc.cw();
                int zzc;
                Object obj;
                int zzahc;
                Object obj2;
                switch (cw) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 10);
                        cw = this.bqP == null ? 0 : this.bqP.length;
                        obj = new String[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.bqP, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.readString();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.readString();
                        this.bqP = obj;
                        continue;
                    case 18:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 18);
                        cw = this.bqQ == null ? 0 : this.bqQ.length;
                        obj = new String[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.bqQ, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.readString();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.readString();
                        this.bqQ = obj;
                        continue;
                    case 24:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 24);
                        cw = this.bqR == null ? 0 : this.bqR.length;
                        obj = new int[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.bqR, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.cA();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.cA();
                        this.bqR = obj;
                        continue;
                    case 26:
                        zzahc = com_google_android_gms_internal_zzarc.zzahc(com_google_android_gms_internal_zzarc.cF());
                        zzc = com_google_android_gms_internal_zzarc.getPosition();
                        cw = 0;
                        while (com_google_android_gms_internal_zzarc.cK() > 0) {
                            com_google_android_gms_internal_zzarc.cA();
                            cw++;
                        }
                        com_google_android_gms_internal_zzarc.zzahe(zzc);
                        zzc = this.bqR == null ? 0 : this.bqR.length;
                        obj2 = new int[(cw + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bqR, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzarc.cA();
                            zzc++;
                        }
                        this.bqR = obj2;
                        com_google_android_gms_internal_zzarc.zzahd(zzahc);
                        continue;
                    case 32:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 32);
                        cw = this.bqS == null ? 0 : this.bqS.length;
                        obj = new long[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.bqS, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.cz();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.cz();
                        this.bqS = obj;
                        continue;
                    case 34:
                        zzahc = com_google_android_gms_internal_zzarc.zzahc(com_google_android_gms_internal_zzarc.cF());
                        zzc = com_google_android_gms_internal_zzarc.getPosition();
                        cw = 0;
                        while (com_google_android_gms_internal_zzarc.cK() > 0) {
                            com_google_android_gms_internal_zzarc.cz();
                            cw++;
                        }
                        com_google_android_gms_internal_zzarc.zzahe(zzc);
                        zzc = this.bqS == null ? 0 : this.bqS.length;
                        obj2 = new long[(cw + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bqS, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzarc.cz();
                            zzc++;
                        }
                        this.bqS = obj2;
                        com_google_android_gms_internal_zzarc.zzahd(zzahc);
                        continue;
                    case 40:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 40);
                        cw = this.bqT == null ? 0 : this.bqT.length;
                        obj = new long[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.bqT, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.cz();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.cz();
                        this.bqT = obj;
                        continue;
                    case 42:
                        zzahc = com_google_android_gms_internal_zzarc.zzahc(com_google_android_gms_internal_zzarc.cF());
                        zzc = com_google_android_gms_internal_zzarc.getPosition();
                        cw = 0;
                        while (com_google_android_gms_internal_zzarc.cK() > 0) {
                            com_google_android_gms_internal_zzarc.cz();
                            cw++;
                        }
                        com_google_android_gms_internal_zzarc.zzahe(zzc);
                        zzc = this.bqT == null ? 0 : this.bqT.length;
                        obj2 = new long[(cw + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bqT, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzarc.cz();
                            zzc++;
                        }
                        this.bqT = obj2;
                        com_google_android_gms_internal_zzarc.zzahd(zzahc);
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzarc, cw)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int i;
            int i2;
            int i3;
            int i4 = 0;
            int zzx = super.zzx();
            if (this.bqP == null || this.bqP.length <= 0) {
                i = zzx;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.bqP) {
                    if (str != null) {
                        i3++;
                        i2 += zzard.zzuy(str);
                    }
                }
                i = (zzx + i2) + (i3 * 1);
            }
            if (this.bqQ != null && this.bqQ.length > 0) {
                i3 = 0;
                zzx = 0;
                for (String str2 : this.bqQ) {
                    if (str2 != null) {
                        zzx++;
                        i3 += zzard.zzuy(str2);
                    }
                }
                i = (i + i3) + (zzx * 1);
            }
            if (this.bqR != null && this.bqR.length > 0) {
                i3 = 0;
                for (int zzx2 : this.bqR) {
                    i3 += zzard.zzahi(zzx2);
                }
                i = (i + i3) + (this.bqR.length * 1);
            }
            if (this.bqS != null && this.bqS.length > 0) {
                i3 = 0;
                for (long zzdb : this.bqS) {
                    i3 += zzard.zzdb(zzdb);
                }
                i = (i + i3) + (this.bqS.length * 1);
            }
            if (this.bqT == null || this.bqT.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i4 < this.bqT.length) {
                i2 += zzard.zzdb(this.bqT[i4]);
                i4++;
            }
            return (i + i2) + (this.bqT.length * 1);
        }
    }

    public static final class zzb extends zzare<zzb> implements Cloneable {
        public int bqU;
        public String bqV;
        public String version;

        public zzb() {
            df();
        }

        public /* synthetic */ zzare cP() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public /* synthetic */ zzark cQ() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return dg();
        }

        public zzb df() {
            this.bqU = 0;
            this.bqV = "";
            this.version = "";
            this.bqv = null;
            this.bqE = -1;
            return this;
        }

        public zzb dg() {
            try {
                return (zzb) super.cP();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb com_google_android_gms_internal_zzarp_zzb = (zzb) obj;
            if (this.bqU != com_google_android_gms_internal_zzarp_zzb.bqU) {
                return false;
            }
            if (this.bqV == null) {
                if (com_google_android_gms_internal_zzarp_zzb.bqV != null) {
                    return false;
                }
            } else if (!this.bqV.equals(com_google_android_gms_internal_zzarp_zzb.bqV)) {
                return false;
            }
            if (this.version == null) {
                if (com_google_android_gms_internal_zzarp_zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(com_google_android_gms_internal_zzarp_zzb.version)) {
                return false;
            }
            return (this.bqv == null || this.bqv.isEmpty()) ? com_google_android_gms_internal_zzarp_zzb.bqv == null || com_google_android_gms_internal_zzarp_zzb.bqv.isEmpty() : this.bqv.equals(com_google_android_gms_internal_zzarp_zzb.bqv);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.bqV == null ? 0 : this.bqV.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.bqU) * 31)) * 31)) * 31;
            if (!(this.bqv == null || this.bqv.isEmpty())) {
                i = this.bqv.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzard com_google_android_gms_internal_zzard) throws IOException {
            if (this.bqU != 0) {
                com_google_android_gms_internal_zzard.zzae(1, this.bqU);
            }
            if (!this.bqV.equals("")) {
                com_google_android_gms_internal_zzard.zzr(2, this.bqV);
            }
            if (!this.version.equals("")) {
                com_google_android_gms_internal_zzard.zzr(3, this.version);
            }
            super.zza(com_google_android_gms_internal_zzard);
        }

        public /* synthetic */ zzark zzb(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            return zzcn(com_google_android_gms_internal_zzarc);
        }

        public zzb zzcn(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            while (true) {
                int cw = com_google_android_gms_internal_zzarc.cw();
                switch (cw) {
                    case 0:
                        break;
                    case 8:
                        this.bqU = com_google_android_gms_internal_zzarc.cA();
                        continue;
                    case 18:
                        this.bqV = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    case 26:
                        this.version = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzarc, cw)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            if (this.bqU != 0) {
                zzx += zzard.zzag(1, this.bqU);
            }
            if (!this.bqV.equals("")) {
                zzx += zzard.zzs(2, this.bqV);
            }
            return !this.version.equals("") ? zzx + zzard.zzs(3, this.version) : zzx;
        }
    }

    public static final class zzc extends zzare<zzc> implements Cloneable {
        public byte[] bqW;
        public String bqX;
        public byte[][] bqY;
        public boolean bqZ;

        public zzc() {
            dh();
        }

        public /* synthetic */ zzare cP() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public /* synthetic */ zzark cQ() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return di();
        }

        public zzc dh() {
            this.bqW = zzarn.bqM;
            this.bqX = "";
            this.bqY = zzarn.bqL;
            this.bqZ = false;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }

        public zzc di() {
            try {
                zzc com_google_android_gms_internal_zzarp_zzc = (zzc) super.cP();
                if (this.bqY != null && this.bqY.length > 0) {
                    com_google_android_gms_internal_zzarp_zzc.bqY = (byte[][]) this.bqY.clone();
                }
                return com_google_android_gms_internal_zzarp_zzc;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc com_google_android_gms_internal_zzarp_zzc = (zzc) obj;
            if (!Arrays.equals(this.bqW, com_google_android_gms_internal_zzarp_zzc.bqW)) {
                return false;
            }
            if (this.bqX == null) {
                if (com_google_android_gms_internal_zzarp_zzc.bqX != null) {
                    return false;
                }
            } else if (!this.bqX.equals(com_google_android_gms_internal_zzarp_zzc.bqX)) {
                return false;
            }
            return (zzari.zza(this.bqY, com_google_android_gms_internal_zzarp_zzc.bqY) && this.bqZ == com_google_android_gms_internal_zzarp_zzc.bqZ) ? (this.bqv == null || this.bqv.isEmpty()) ? com_google_android_gms_internal_zzarp_zzc.bqv == null || com_google_android_gms_internal_zzarp_zzc.bqv.isEmpty() : this.bqv.equals(com_google_android_gms_internal_zzarp_zzc.bqv) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.bqZ ? 1231 : 1237) + (((((this.bqX == null ? 0 : this.bqX.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.bqW)) * 31)) * 31) + zzari.zzb(this.bqY)) * 31)) * 31;
            if (!(this.bqv == null || this.bqv.isEmpty())) {
                i = this.bqv.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzard com_google_android_gms_internal_zzard) throws IOException {
            if (!Arrays.equals(this.bqW, zzarn.bqM)) {
                com_google_android_gms_internal_zzard.zza(1, this.bqW);
            }
            if (this.bqY != null && this.bqY.length > 0) {
                for (byte[] bArr : this.bqY) {
                    if (bArr != null) {
                        com_google_android_gms_internal_zzard.zza(2, bArr);
                    }
                }
            }
            if (this.bqZ) {
                com_google_android_gms_internal_zzard.zzj(3, this.bqZ);
            }
            if (!this.bqX.equals("")) {
                com_google_android_gms_internal_zzard.zzr(4, this.bqX);
            }
            super.zza(com_google_android_gms_internal_zzard);
        }

        public /* synthetic */ zzark zzb(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            return zzco(com_google_android_gms_internal_zzarc);
        }

        public zzc zzco(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            while (true) {
                int cw = com_google_android_gms_internal_zzarc.cw();
                switch (cw) {
                    case 0:
                        break;
                    case 10:
                        this.bqW = com_google_android_gms_internal_zzarc.readBytes();
                        continue;
                    case 18:
                        int zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 18);
                        cw = this.bqY == null ? 0 : this.bqY.length;
                        Object obj = new byte[(zzc + cw)][];
                        if (cw != 0) {
                            System.arraycopy(this.bqY, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.readBytes();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.readBytes();
                        this.bqY = obj;
                        continue;
                    case 24:
                        this.bqZ = com_google_android_gms_internal_zzarc.cC();
                        continue;
                    case 34:
                        this.bqX = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzarc, cw)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int i = 0;
            int zzx = super.zzx();
            if (!Arrays.equals(this.bqW, zzarn.bqM)) {
                zzx += zzard.zzb(1, this.bqW);
            }
            if (this.bqY != null && this.bqY.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (i < this.bqY.length) {
                    byte[] bArr = this.bqY[i];
                    if (bArr != null) {
                        i3++;
                        i2 += zzard.zzbg(bArr);
                    }
                    i++;
                }
                zzx = (zzx + i2) + (i3 * 1);
            }
            if (this.bqZ) {
                zzx += zzard.zzk(3, this.bqZ);
            }
            return !this.bqX.equals("") ? zzx + zzard.zzs(4, this.bqX) : zzx;
        }
    }

    public static final class zzd extends zzare<zzd> implements Cloneable {
        public boolean bak;
        public long bra;
        public long brb;
        public long brc;
        public int brd;
        public zze[] bre;
        public byte[] brf;
        public zzb brg;
        public byte[] brh;
        public String bri;
        public String brj;
        public zza brk;
        public String brl;
        public long brm;
        public zzc brn;
        public byte[] bro;
        public String brp;
        public int brq;
        public int[] brr;
        public long brs;
        public zzf brt;
        public String tag;
        public int zzajd;

        public zzd() {
            dj();
        }

        public /* synthetic */ zzare cP() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public /* synthetic */ zzark cQ() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return dk();
        }

        public zzd dj() {
            this.bra = 0;
            this.brb = 0;
            this.brc = 0;
            this.tag = "";
            this.brd = 0;
            this.zzajd = 0;
            this.bak = false;
            this.bre = zze.dl();
            this.brf = zzarn.bqM;
            this.brg = null;
            this.brh = zzarn.bqM;
            this.bri = "";
            this.brj = "";
            this.brk = null;
            this.brl = "";
            this.brm = 180000;
            this.brn = null;
            this.bro = zzarn.bqM;
            this.brp = "";
            this.brq = 0;
            this.brr = zzarn.bqF;
            this.brs = 0;
            this.brt = null;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }

        public zzd dk() {
            try {
                zzd com_google_android_gms_internal_zzarp_zzd = (zzd) super.cP();
                if (this.bre != null && this.bre.length > 0) {
                    com_google_android_gms_internal_zzarp_zzd.bre = new zze[this.bre.length];
                    for (int i = 0; i < this.bre.length; i++) {
                        if (this.bre[i] != null) {
                            com_google_android_gms_internal_zzarp_zzd.bre[i] = (zze) this.bre[i].clone();
                        }
                    }
                }
                if (this.brg != null) {
                    com_google_android_gms_internal_zzarp_zzd.brg = (zzb) this.brg.clone();
                }
                if (this.brk != null) {
                    com_google_android_gms_internal_zzarp_zzd.brk = (zza) this.brk.clone();
                }
                if (this.brn != null) {
                    com_google_android_gms_internal_zzarp_zzd.brn = (zzc) this.brn.clone();
                }
                if (this.brr != null && this.brr.length > 0) {
                    com_google_android_gms_internal_zzarp_zzd.brr = (int[]) this.brr.clone();
                }
                if (this.brt != null) {
                    com_google_android_gms_internal_zzarp_zzd.brt = (zzf) this.brt.clone();
                }
                return com_google_android_gms_internal_zzarp_zzd;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd com_google_android_gms_internal_zzarp_zzd = (zzd) obj;
            if (this.bra != com_google_android_gms_internal_zzarp_zzd.bra || this.brb != com_google_android_gms_internal_zzarp_zzd.brb || this.brc != com_google_android_gms_internal_zzarp_zzd.brc) {
                return false;
            }
            if (this.tag == null) {
                if (com_google_android_gms_internal_zzarp_zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(com_google_android_gms_internal_zzarp_zzd.tag)) {
                return false;
            }
            if (this.brd != com_google_android_gms_internal_zzarp_zzd.brd || this.zzajd != com_google_android_gms_internal_zzarp_zzd.zzajd || this.bak != com_google_android_gms_internal_zzarp_zzd.bak || !zzari.equals(this.bre, com_google_android_gms_internal_zzarp_zzd.bre) || !Arrays.equals(this.brf, com_google_android_gms_internal_zzarp_zzd.brf)) {
                return false;
            }
            if (this.brg == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brg != null) {
                    return false;
                }
            } else if (!this.brg.equals(com_google_android_gms_internal_zzarp_zzd.brg)) {
                return false;
            }
            if (!Arrays.equals(this.brh, com_google_android_gms_internal_zzarp_zzd.brh)) {
                return false;
            }
            if (this.bri == null) {
                if (com_google_android_gms_internal_zzarp_zzd.bri != null) {
                    return false;
                }
            } else if (!this.bri.equals(com_google_android_gms_internal_zzarp_zzd.bri)) {
                return false;
            }
            if (this.brj == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brj != null) {
                    return false;
                }
            } else if (!this.brj.equals(com_google_android_gms_internal_zzarp_zzd.brj)) {
                return false;
            }
            if (this.brk == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brk != null) {
                    return false;
                }
            } else if (!this.brk.equals(com_google_android_gms_internal_zzarp_zzd.brk)) {
                return false;
            }
            if (this.brl == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brl != null) {
                    return false;
                }
            } else if (!this.brl.equals(com_google_android_gms_internal_zzarp_zzd.brl)) {
                return false;
            }
            if (this.brm != com_google_android_gms_internal_zzarp_zzd.brm) {
                return false;
            }
            if (this.brn == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brn != null) {
                    return false;
                }
            } else if (!this.brn.equals(com_google_android_gms_internal_zzarp_zzd.brn)) {
                return false;
            }
            if (!Arrays.equals(this.bro, com_google_android_gms_internal_zzarp_zzd.bro)) {
                return false;
            }
            if (this.brp == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brp != null) {
                    return false;
                }
            } else if (!this.brp.equals(com_google_android_gms_internal_zzarp_zzd.brp)) {
                return false;
            }
            if (this.brq != com_google_android_gms_internal_zzarp_zzd.brq || !zzari.equals(this.brr, com_google_android_gms_internal_zzarp_zzd.brr) || this.brs != com_google_android_gms_internal_zzarp_zzd.brs) {
                return false;
            }
            if (this.brt == null) {
                if (com_google_android_gms_internal_zzarp_zzd.brt != null) {
                    return false;
                }
            } else if (!this.brt.equals(com_google_android_gms_internal_zzarp_zzd.brt)) {
                return false;
            }
            return (this.bqv == null || this.bqv.isEmpty()) ? com_google_android_gms_internal_zzarp_zzd.bqv == null || com_google_android_gms_internal_zzarp_zzd.bqv.isEmpty() : this.bqv.equals(com_google_android_gms_internal_zzarp_zzd.bqv);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.brt == null ? 0 : this.brt.hashCode()) + (((((((((this.brp == null ? 0 : this.brp.hashCode()) + (((((this.brn == null ? 0 : this.brn.hashCode()) + (((((this.brl == null ? 0 : this.brl.hashCode()) + (((this.brk == null ? 0 : this.brk.hashCode()) + (((this.brj == null ? 0 : this.brj.hashCode()) + (((this.bri == null ? 0 : this.bri.hashCode()) + (((((this.brg == null ? 0 : this.brg.hashCode()) + (((((((this.bak ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.bra ^ (this.bra >>> 32)))) * 31) + ((int) (this.brb ^ (this.brb >>> 32)))) * 31) + ((int) (this.brc ^ (this.brc >>> 32)))) * 31)) * 31) + this.brd) * 31) + this.zzajd) * 31)) * 31) + zzari.hashCode(this.bre)) * 31) + Arrays.hashCode(this.brf)) * 31)) * 31) + Arrays.hashCode(this.brh)) * 31)) * 31)) * 31)) * 31)) * 31) + ((int) (this.brm ^ (this.brm >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.bro)) * 31)) * 31) + this.brq) * 31) + zzari.hashCode(this.brr)) * 31) + ((int) (this.brs ^ (this.brs >>> 32)))) * 31)) * 31;
            if (!(this.bqv == null || this.bqv.isEmpty())) {
                i = this.bqv.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzard com_google_android_gms_internal_zzard) throws IOException {
            int i = 0;
            if (this.bra != 0) {
                com_google_android_gms_internal_zzard.zzb(1, this.bra);
            }
            if (!this.tag.equals("")) {
                com_google_android_gms_internal_zzard.zzr(2, this.tag);
            }
            if (this.bre != null && this.bre.length > 0) {
                for (zzark com_google_android_gms_internal_zzark : this.bre) {
                    if (com_google_android_gms_internal_zzark != null) {
                        com_google_android_gms_internal_zzard.zza(3, com_google_android_gms_internal_zzark);
                    }
                }
            }
            if (!Arrays.equals(this.brf, zzarn.bqM)) {
                com_google_android_gms_internal_zzard.zza(4, this.brf);
            }
            if (!Arrays.equals(this.brh, zzarn.bqM)) {
                com_google_android_gms_internal_zzard.zza(6, this.brh);
            }
            if (this.brk != null) {
                com_google_android_gms_internal_zzard.zza(7, this.brk);
            }
            if (!this.bri.equals("")) {
                com_google_android_gms_internal_zzard.zzr(8, this.bri);
            }
            if (this.brg != null) {
                com_google_android_gms_internal_zzard.zza(9, this.brg);
            }
            if (this.bak) {
                com_google_android_gms_internal_zzard.zzj(10, this.bak);
            }
            if (this.brd != 0) {
                com_google_android_gms_internal_zzard.zzae(11, this.brd);
            }
            if (this.zzajd != 0) {
                com_google_android_gms_internal_zzard.zzae(12, this.zzajd);
            }
            if (!this.brj.equals("")) {
                com_google_android_gms_internal_zzard.zzr(13, this.brj);
            }
            if (!this.brl.equals("")) {
                com_google_android_gms_internal_zzard.zzr(14, this.brl);
            }
            if (this.brm != 180000) {
                com_google_android_gms_internal_zzard.zzd(15, this.brm);
            }
            if (this.brn != null) {
                com_google_android_gms_internal_zzard.zza(16, this.brn);
            }
            if (this.brb != 0) {
                com_google_android_gms_internal_zzard.zzb(17, this.brb);
            }
            if (!Arrays.equals(this.bro, zzarn.bqM)) {
                com_google_android_gms_internal_zzard.zza(18, this.bro);
            }
            if (this.brq != 0) {
                com_google_android_gms_internal_zzard.zzae(19, this.brq);
            }
            if (this.brr != null && this.brr.length > 0) {
                while (i < this.brr.length) {
                    com_google_android_gms_internal_zzard.zzae(20, this.brr[i]);
                    i++;
                }
            }
            if (this.brc != 0) {
                com_google_android_gms_internal_zzard.zzb(21, this.brc);
            }
            if (this.brs != 0) {
                com_google_android_gms_internal_zzard.zzb(22, this.brs);
            }
            if (this.brt != null) {
                com_google_android_gms_internal_zzard.zza(23, this.brt);
            }
            if (!this.brp.equals("")) {
                com_google_android_gms_internal_zzard.zzr(24, this.brp);
            }
            super.zza(com_google_android_gms_internal_zzard);
        }

        public /* synthetic */ zzark zzb(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            return zzcp(com_google_android_gms_internal_zzarc);
        }

        public zzd zzcp(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            while (true) {
                int cw = com_google_android_gms_internal_zzarc.cw();
                int zzc;
                Object obj;
                switch (cw) {
                    case 0:
                        break;
                    case 8:
                        this.bra = com_google_android_gms_internal_zzarc.cz();
                        continue;
                    case 18:
                        this.tag = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    case 26:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, 26);
                        cw = this.bre == null ? 0 : this.bre.length;
                        obj = new zze[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.bre, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = new zze();
                            com_google_android_gms_internal_zzarc.zza(obj[cw]);
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = new zze();
                        com_google_android_gms_internal_zzarc.zza(obj[cw]);
                        this.bre = obj;
                        continue;
                    case 34:
                        this.brf = com_google_android_gms_internal_zzarc.readBytes();
                        continue;
                    case 50:
                        this.brh = com_google_android_gms_internal_zzarc.readBytes();
                        continue;
                    case 58:
                        if (this.brk == null) {
                            this.brk = new zza();
                        }
                        com_google_android_gms_internal_zzarc.zza(this.brk);
                        continue;
                    case 66:
                        this.bri = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    case 74:
                        if (this.brg == null) {
                            this.brg = new zzb();
                        }
                        com_google_android_gms_internal_zzarc.zza(this.brg);
                        continue;
                    case 80:
                        this.bak = com_google_android_gms_internal_zzarc.cC();
                        continue;
                    case 88:
                        this.brd = com_google_android_gms_internal_zzarc.cA();
                        continue;
                    case 96:
                        this.zzajd = com_google_android_gms_internal_zzarc.cA();
                        continue;
                    case 106:
                        this.brj = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    case 114:
                        this.brl = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    case ParseException.CACHE_MISS /*120*/:
                        this.brm = com_google_android_gms_internal_zzarc.cE();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        if (this.brn == null) {
                            this.brn = new zzc();
                        }
                        com_google_android_gms_internal_zzarc.zza(this.brn);
                        continue;
                    case 136:
                        this.brb = com_google_android_gms_internal_zzarc.cz();
                        continue;
                    case 146:
                        this.bro = com_google_android_gms_internal_zzarc.readBytes();
                        continue;
                    case 152:
                        cw = com_google_android_gms_internal_zzarc.cA();
                        switch (cw) {
                            case 0:
                            case 1:
                            case 2:
                                this.brq = cw;
                                break;
                            default:
                                continue;
                        }
                    case ParseException.INVALID_EVENT_NAME /*160*/:
                        zzc = zzarn.zzc(com_google_android_gms_internal_zzarc, ParseException.INVALID_EVENT_NAME);
                        cw = this.brr == null ? 0 : this.brr.length;
                        obj = new int[(zzc + cw)];
                        if (cw != 0) {
                            System.arraycopy(this.brr, 0, obj, 0, cw);
                        }
                        while (cw < obj.length - 1) {
                            obj[cw] = com_google_android_gms_internal_zzarc.cA();
                            com_google_android_gms_internal_zzarc.cw();
                            cw++;
                        }
                        obj[cw] = com_google_android_gms_internal_zzarc.cA();
                        this.brr = obj;
                        continue;
                    case 162:
                        int zzahc = com_google_android_gms_internal_zzarc.zzahc(com_google_android_gms_internal_zzarc.cF());
                        zzc = com_google_android_gms_internal_zzarc.getPosition();
                        cw = 0;
                        while (com_google_android_gms_internal_zzarc.cK() > 0) {
                            com_google_android_gms_internal_zzarc.cA();
                            cw++;
                        }
                        com_google_android_gms_internal_zzarc.zzahe(zzc);
                        zzc = this.brr == null ? 0 : this.brr.length;
                        Object obj2 = new int[(cw + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.brr, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzarc.cA();
                            zzc++;
                        }
                        this.brr = obj2;
                        com_google_android_gms_internal_zzarc.zzahd(zzahc);
                        continue;
                    case 168:
                        this.brc = com_google_android_gms_internal_zzarc.cz();
                        continue;
                    case 176:
                        this.brs = com_google_android_gms_internal_zzarc.cz();
                        continue;
                    case 186:
                        if (this.brt == null) {
                            this.brt = new zzf();
                        }
                        com_google_android_gms_internal_zzarc.zza(this.brt);
                        continue;
                    case DownloaderService.STATUS_WAITING_TO_RETRY /*194*/:
                        this.brp = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzarc, cw)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int i;
            int i2 = 0;
            int zzx = super.zzx();
            if (this.bra != 0) {
                zzx += zzard.zzf(1, this.bra);
            }
            if (!this.tag.equals("")) {
                zzx += zzard.zzs(2, this.tag);
            }
            if (this.bre != null && this.bre.length > 0) {
                i = zzx;
                for (zzark com_google_android_gms_internal_zzark : this.bre) {
                    if (com_google_android_gms_internal_zzark != null) {
                        i += zzard.zzc(3, com_google_android_gms_internal_zzark);
                    }
                }
                zzx = i;
            }
            if (!Arrays.equals(this.brf, zzarn.bqM)) {
                zzx += zzard.zzb(4, this.brf);
            }
            if (!Arrays.equals(this.brh, zzarn.bqM)) {
                zzx += zzard.zzb(6, this.brh);
            }
            if (this.brk != null) {
                zzx += zzard.zzc(7, this.brk);
            }
            if (!this.bri.equals("")) {
                zzx += zzard.zzs(8, this.bri);
            }
            if (this.brg != null) {
                zzx += zzard.zzc(9, this.brg);
            }
            if (this.bak) {
                zzx += zzard.zzk(10, this.bak);
            }
            if (this.brd != 0) {
                zzx += zzard.zzag(11, this.brd);
            }
            if (this.zzajd != 0) {
                zzx += zzard.zzag(12, this.zzajd);
            }
            if (!this.brj.equals("")) {
                zzx += zzard.zzs(13, this.brj);
            }
            if (!this.brl.equals("")) {
                zzx += zzard.zzs(14, this.brl);
            }
            if (this.brm != 180000) {
                zzx += zzard.zzh(15, this.brm);
            }
            if (this.brn != null) {
                zzx += zzard.zzc(16, this.brn);
            }
            if (this.brb != 0) {
                zzx += zzard.zzf(17, this.brb);
            }
            if (!Arrays.equals(this.bro, zzarn.bqM)) {
                zzx += zzard.zzb(18, this.bro);
            }
            if (this.brq != 0) {
                zzx += zzard.zzag(19, this.brq);
            }
            if (this.brr != null && this.brr.length > 0) {
                i = 0;
                while (i2 < this.brr.length) {
                    i += zzard.zzahi(this.brr[i2]);
                    i2++;
                }
                zzx = (zzx + i) + (this.brr.length * 2);
            }
            if (this.brc != 0) {
                zzx += zzard.zzf(21, this.brc);
            }
            if (this.brs != 0) {
                zzx += zzard.zzf(22, this.brs);
            }
            if (this.brt != null) {
                zzx += zzard.zzc(23, this.brt);
            }
            return !this.brp.equals("") ? zzx + zzard.zzs(24, this.brp) : zzx;
        }
    }

    public static final class zze extends zzare<zze> implements Cloneable {
        private static volatile zze[] bru;
        public String value;
        public String zzcb;

        public zze() {
            dm();
        }

        public static zze[] dl() {
            if (bru == null) {
                synchronized (zzari.bqD) {
                    if (bru == null) {
                        bru = new zze[0];
                    }
                }
            }
            return bru;
        }

        public /* synthetic */ zzare cP() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public /* synthetic */ zzark cQ() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return dn();
        }

        public zze dm() {
            this.zzcb = "";
            this.value = "";
            this.bqv = null;
            this.bqE = -1;
            return this;
        }

        public zze dn() {
            try {
                return (zze) super.cP();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze com_google_android_gms_internal_zzarp_zze = (zze) obj;
            if (this.zzcb == null) {
                if (com_google_android_gms_internal_zzarp_zze.zzcb != null) {
                    return false;
                }
            } else if (!this.zzcb.equals(com_google_android_gms_internal_zzarp_zze.zzcb)) {
                return false;
            }
            if (this.value == null) {
                if (com_google_android_gms_internal_zzarp_zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(com_google_android_gms_internal_zzarp_zze.value)) {
                return false;
            }
            return (this.bqv == null || this.bqv.isEmpty()) ? com_google_android_gms_internal_zzarp_zze.bqv == null || com_google_android_gms_internal_zzarp_zze.bqv.isEmpty() : this.bqv.equals(com_google_android_gms_internal_zzarp_zze.bqv);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzcb == null ? 0 : this.zzcb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (!(this.bqv == null || this.bqv.isEmpty())) {
                i = this.bqv.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzard com_google_android_gms_internal_zzard) throws IOException {
            if (!this.zzcb.equals("")) {
                com_google_android_gms_internal_zzard.zzr(1, this.zzcb);
            }
            if (!this.value.equals("")) {
                com_google_android_gms_internal_zzard.zzr(2, this.value);
            }
            super.zza(com_google_android_gms_internal_zzard);
        }

        public /* synthetic */ zzark zzb(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            return zzcq(com_google_android_gms_internal_zzarc);
        }

        public zze zzcq(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            while (true) {
                int cw = com_google_android_gms_internal_zzarc.cw();
                switch (cw) {
                    case 0:
                        break;
                    case 10:
                        this.zzcb = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    case 18:
                        this.value = com_google_android_gms_internal_zzarc.readString();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzarc, cw)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            if (!this.zzcb.equals("")) {
                zzx += zzard.zzs(1, this.zzcb);
            }
            return !this.value.equals("") ? zzx + zzard.zzs(2, this.value) : zzx;
        }
    }

    public static final class zzf extends zzare<zzf> implements Cloneable {
        public int brv;

        public zzf() {
            do();
        }

        public /* synthetic */ zzare cP() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public /* synthetic */ zzark cQ() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return dp();
        }

        public zzf do() {
            this.brv = -1;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }

        public zzf dp() {
            try {
                return (zzf) super.cP();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf com_google_android_gms_internal_zzarp_zzf = (zzf) obj;
            return this.brv == com_google_android_gms_internal_zzarp_zzf.brv ? (this.bqv == null || this.bqv.isEmpty()) ? com_google_android_gms_internal_zzarp_zzf.bqv == null || com_google_android_gms_internal_zzarp_zzf.bqv.isEmpty() : this.bqv.equals(com_google_android_gms_internal_zzarp_zzf.bqv) : false;
        }

        public int hashCode() {
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.brv) * 31;
            int hashCode2 = (this.bqv == null || this.bqv.isEmpty()) ? 0 : this.bqv.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzard com_google_android_gms_internal_zzard) throws IOException {
            if (this.brv != -1) {
                com_google_android_gms_internal_zzard.zzae(1, this.brv);
            }
            super.zza(com_google_android_gms_internal_zzard);
        }

        public /* synthetic */ zzark zzb(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            return zzcr(com_google_android_gms_internal_zzarc);
        }

        public zzf zzcr(zzarc com_google_android_gms_internal_zzarc) throws IOException {
            while (true) {
                int cw = com_google_android_gms_internal_zzarc.cw();
                switch (cw) {
                    case 0:
                        break;
                    case 8:
                        cw = com_google_android_gms_internal_zzarc.cA();
                        switch (cw) {
                            case -1:
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                this.brv = cw;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzarc, cw)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            return this.brv != -1 ? zzx + zzard.zzag(1, this.brv) : zzx;
        }
    }
}
