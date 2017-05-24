package com.spiraltoys.cloudpets2.audio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class WavChunkData {
    private static final String CHUNKID = "data";
    private byte[] data;
    private short[] shortData;

    public static WavChunkData tryDeserialize(ByteBuffer input) {
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
            WavChunkData wavChunkData = new WavChunkData(input);
            input.order(byteOrderPrev);
            return wavChunkData;
        } catch (Exception e) {
            input.position(chunkStartPos);
            return null;
        } finally {
            input.order(byteOrderPrev);
        }
    }

    public WavChunkData() {
        setBytes(null);
    }

    public WavChunkData(byte[] inData) {
        setBytes(inData);
    }

    public WavChunkData(short[] inData) {
        setShorts(inData);
    }

    public void setBytes(byte[] inData) {
        this.data = inData;
        this.shortData = null;
    }

    public void setShorts(short[] inData) {
        this.shortData = inData;
        if (inData == null) {
            this.shortData = null;
            return;
        }
        this.data = new byte[(this.shortData.length << 1)];
        for (int byteIdx = 0; byteIdx < this.data.length; byteIdx++) {
            this.data[byteIdx] = (byte) (((byteIdx & 1) == 0 ? this.shortData[byteIdx >> 1] : this.shortData[byteIdx >> 1] >> 8) & 255);
        }
    }

    public byte[] getDataAsBytes() {
        return this.data;
    }

    public short[] getDataAsShorts() {
        if (this.data == null) {
            return null;
        }
        if (this.shortData == null) {
            this.shortData = new short[(this.data.length >> 1)];
            int shortIdx = 0;
            int byteBaseIdx = 0;
            while (shortIdx < this.shortData.length) {
                this.shortData[shortIdx] = (short) (((this.data[byteBaseIdx + 1] & 255) << 8) | (this.data[byteBaseIdx] & 255));
                shortIdx++;
                byteBaseIdx += 2;
            }
        }
        return this.shortData;
    }

    public void serialize(OutputStream os) throws IOException {
        ByteBuffer endianBuffer = ByteBuffer.allocate(4);
        endianBuffer.order(ByteOrder.LITTLE_ENDIAN);
        os.write(CHUNKID.getBytes(Charset.forName("US-ASCII")));
        os.write(endianBuffer.putInt(getChunkSize() - 8).array());
        os.write(this.data);
    }

    public int getChunkSize() {
        return (this.data == null ? 0 : this.data.length) + 8;
    }

    protected WavChunkData(ByteBuffer input) throws BufferUnderflowException, RuntimeException {
        input.position(input.position() + 4);
        int dataSize = input.getInt() - 8;
        if (dataSize < 0) {
            throw new RuntimeException("Bad WAV data chunk");
        }
        this.data = new byte[dataSize];
        if (dataSize > 0) {
            input.get(this.data, 0, dataSize);
        }
    }
}
