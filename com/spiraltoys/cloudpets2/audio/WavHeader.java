package com.spiraltoys.cloudpets2.audio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class WavHeader {
    private static final String CHUNKID = "RIFF";
    private static final String FORMAT = "WAVE";
    public int size;

    public static WavHeader tryDeserialize(ByteBuffer input) {
        if (input.remaining() < 12) {
            return null;
        }
        ByteOrder byteOrderPrev = input.order();
        int chunkStartPos = input.position();
        try {
            input.order(ByteOrder.LITTLE_ENDIAN);
            byte[] stringId = new byte[4];
            input.get(stringId, 0, 4);
            String chunkId = new String(stringId, Charset.forName("US-ASCII"));
            int size = input.getInt();
            input.get(stringId, 0, 4);
            String format = new String(stringId, Charset.forName("US-ASCII"));
            if (chunkId.equals(CHUNKID) && format.equals(FORMAT) && size >= 12) {
                WavHeader wavHeader = new WavHeader(size);
                input.order(byteOrderPrev);
                return wavHeader;
            }
            input.position(chunkStartPos);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            input.position(chunkStartPos);
            return null;
        } finally {
            input.order(byteOrderPrev);
        }
    }

    public WavHeader() {
        this(0);
    }

    public WavHeader(int inSize) {
        this.size = inSize;
    }

    public void serialize(OutputStream os) throws IOException {
        ByteBuffer endianBuffer = ByteBuffer.allocate(4);
        endianBuffer.order(ByteOrder.LITTLE_ENDIAN);
        os.write(CHUNKID.getBytes(Charset.forName("US-ASCII")));
        os.write(endianBuffer.putInt(this.size).array());
        os.write(FORMAT.getBytes(Charset.forName("US-ASCII")));
    }

    public int getChunkSize() {
        return 12;
    }
}
