package com.spiraltoys.cloudpets2.toy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ToyUtil {

    public enum WriteSpeed {
        SLOW,
        MEDIUM,
        FAST
    }

    public static int getNumber(byte[] value, int start, int length) {
        int result = 0;
        int shift = 0;
        int i = 0;
        while (i + start < value.length && i < length) {
            result += value[i + start] << shift;
            shift += 8;
            i++;
        }
        return result;
    }

    public static byte[] getBytes(short value) {
        return new byte[]{(byte) (value & 255), (byte) ((value >> 8) & 255)};
    }

    public static final String parseName(byte[] scanRecord) {
        ByteBuffer scanRecordBuffer = ByteBuffer.wrap(scanRecord).order(ByteOrder.LITTLE_ENDIAN);
        while (scanRecordBuffer.hasRemaining()) {
            byte length = scanRecordBuffer.get();
            if (length != (byte) 0) {
                byte type = scanRecordBuffer.get();
                byte[] data = new byte[(length - 1)];
                scanRecordBuffer.get(data);
                switch (type) {
                    case (byte) 8:
                    case (byte) 9:
                        return new String(data);
                    default:
                        break;
                }
            }
        }
        return null;
    }

    public static String getByteString(byte[] value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte toHexString : value) {
            String hexString = Integer.toHexString(toHexString);
            if (hexString.length() == 1) {
                stringBuilder.append('0');
            } else {
                hexString = hexString.substring(hexString.length() - 2);
            }
            stringBuilder.append(hexString);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
