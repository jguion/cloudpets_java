package com.android;

import java.nio.ByteOrder;

public class Convert {
    public boolean testCPU() {
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            return true;
        }
        return false;
    }

    public short getShort(byte[] buf) {
        return getShort(buf, testCPU());
    }

    public byte[] getBytes(short s) {
        return getBytes((long) s, testCPU());
    }

    public short getShort(byte[] buf, boolean bBigEnding) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        } else if (buf.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        } else {
            short r = (short) 0;
            int i;
            if (bBigEnding) {
                for (byte b : buf) {
                    r = (short) ((b & 255) | ((short) (r << 8)));
                }
            } else {
                for (i = buf.length - 1; i >= 0; i--) {
                    r = (short) ((buf[i] & 255) | ((short) (r << 8)));
                }
            }
            return r;
        }
    }

    public byte[] getBytes(long s, boolean bBigEnding) {
        byte[] buf = new byte[8];
        int i;
        if (bBigEnding) {
            for (i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) ((int) (s & 255));
                s >>= 8;
            }
        } else {
            for (i = 0; i < buf.length; i++) {
                buf[i] = (byte) ((int) (s & 255));
                s >>= 8;
            }
        }
        return buf;
    }

    public short[] Bytes2Shorts(byte[] buf) {
        short[] s = new short[(buf.length / 2)];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = new byte[2];
            for (int jLoop = 0; jLoop < 2; jLoop++) {
                temp[jLoop] = buf[(iLoop * 2) + jLoop];
            }
            s[iLoop] = getShort(temp);
        }
        return s;
    }

    public byte[] Shorts2Bytes(short[] s) {
        byte[] buf = new byte[(s.length * 2)];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = getBytes(s[iLoop]);
            for (int jLoop = 0; jLoop < 2; jLoop++) {
                buf[(iLoop * 2) + jLoop] = temp[jLoop];
            }
        }
        return buf;
    }
}
