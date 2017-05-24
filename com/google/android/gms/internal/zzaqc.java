package com.google.android.gms.internal;

public class zzaqc {
    private int boA;
    private int boB;
    private final byte[] boz = new byte[256];

    public zzaqc(byte[] bArr) {
        int i;
        for (i = 0; i < 256; i++) {
            this.boz[i] = (byte) i;
        }
        i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            i = ((i + this.boz[i2]) + bArr[i2 % bArr.length]) & 255;
            byte b = this.boz[i2];
            this.boz[i2] = this.boz[i];
            this.boz[i] = b;
        }
        this.boA = 0;
        this.boB = 0;
    }

    public void zzax(byte[] bArr) {
        int i = this.boA;
        int i2 = this.boB;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.boz[i]) & 255;
            byte b = this.boz[i];
            this.boz[i] = this.boz[i2];
            this.boz[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.boz[(this.boz[i] + this.boz[i2]) & 255]);
        }
        this.boA = i;
        this.boB = i2;
    }
}
