package com.spiraltoys.cloudpets2.audio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class WavChunkFiller {
    private static final String CHUNKID = "FLLR";
    private int padding;

    public static WavChunkFiller tryDeserialize(ByteBuffer input) {
        if (input.remaining() < 8) {
            return null;
        }
        ByteOrder byteOrderPrev = input.order();
        int chunkStartPos = input.position();
        try {
            input.order(ByteOrder.LITTLE_ENDIAN);
            byte[] tag = new byte[4];
            input.get(tag, 0, 4);
            input.position(chunkStartPos);
            if (!new String(tag, Charset.forName("US-ASCII")).equals(CHUNKID)) {
                return null;
            }
            WavChunkFiller wavChunkFiller = new WavChunkFiller(input);
            input.order(byteOrderPrev);
            return wavChunkFiller;
        } catch (Exception e) {
            input.position(chunkStartPos);
            return null;
        } finally {
            input.order(byteOrderPrev);
        }
    }

    public void setPadding(int inPadding) {
        this.padding = Math.max(0, inPadding);
    }

    public void serialize(OutputStream os) throws IOException {
        ByteBuffer endianBuffer = ByteBuffer.allocate(4);
        endianBuffer.order(ByteOrder.LITTLE_ENDIAN);
        os.write(CHUNKID.getBytes(Charset.forName("US-ASCII")));
        os.write(endianBuffer.putInt(getChunkSize() - 8).array());
        for (int paddingCount = 0; paddingCount < this.padding; paddingCount++) {
            os.write(0);
        }
    }

    public int getChunkSize() {
        return this.padding + 8;
    }

    protected WavChunkFiller(ByteBuffer input) throws BufferUnderflowException, RuntimeException {
        input.position(input.position() + 4);
        this.padding = input.getInt() - 8;
        if (this.padding < 0) {
            throw new RuntimeException("Bad WAV filler chunk");
        }
        input.position(input.position() + this.padding);
    }
}
