package com.android.AudioCodec;

public class MsAdpcm {
    public static native void decode(short[] sArr, short[] sArr2);

    public static native short getInputSize();

    public static native short getOutputSize();

    public static native boolean init(short[] sArr);

    static {
        try {
            System.loadLibrary("MsAdpcm");
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("loadLibrary(MsAdpcm)," + ule.getMessage());
        }
    }
}
