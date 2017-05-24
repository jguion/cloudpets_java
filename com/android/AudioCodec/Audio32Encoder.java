package com.android.AudioCodec;

public class Audio32Encoder {
    public static native short[] encode(byte[] bArr);

    public static native void getHeader(short[] sArr);

    public static native void init(int i);

    public static native String stringFromJNI();

    static {
        try {
            System.loadLibrary("Audio32Encoder");
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("loadLibrary(Audio32Encoder)," + ule.getMessage());
        }
    }
}
