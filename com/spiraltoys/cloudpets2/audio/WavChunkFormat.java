package com.spiraltoys.cloudpets2.audio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class WavChunkFormat {
    public static final int AUDIOFORMAT_PCM = 1;
    private static final String BEXT_CHUNKID = "bext";
    private static final String CHUNKID = "fmt ";
    private static final String JUNK_CHUNKID = "JUNK";
    public int audioFormat;
    public int bitsPerSample;
    public int channelsNum;
    public int padding;
    public int sampleRate;

    public static WavChunkFormat tryDeserialize(ByteBuffer input) {
        if (input.remaining() < 8) {
            return null;
        }
        ByteOrder byteOrderPrev = input.order();
        int chunkStartPos = input.position();
        try {
            input.order(ByteOrder.LITTLE_ENDIAN);
            byte[] tag = new byte[4];
            input.get(tag);
            String str = new String(tag, Charset.forName("US-ASCII"));
            if (str.equals(JUNK_CHUNKID)) {
                input.position(input.position() + input.getInt());
                input.get(tag);
                str = new String(tag, Charset.forName("US-ASCII"));
            }
            if (str.equals(BEXT_CHUNKID)) {
                input.position(input.position() + input.getInt());
                input.get(tag);
                str = new String(tag, Charset.forName("US-ASCII"));
            }
            if (!str.equals(CHUNKID)) {
                return null;
            }
            WavChunkFormat wavChunkFormat = new WavChunkFormat(input);
            input.order(byteOrderPrev);
            return wavChunkFormat;
        } catch (Exception e) {
            input.position(chunkStartPos);
            return null;
        } finally {
            input.order(byteOrderPrev);
        }
    }

    public WavChunkFormat(int inSampleRate, int inChannelsNum, int inBitsPerSample) {
        this.audioFormat = 1;
        this.sampleRate = inSampleRate;
        this.channelsNum = inChannelsNum;
        this.bitsPerSample = inBitsPerSample;
        this.padding = 0;
    }

    public void serialize(OutputStream os) throws IOException {
        short blockAlign = (short) ((this.channelsNum * this.bitsPerSample) / 8);
        int byteRate = this.sampleRate * blockAlign;
        ByteBuffer endianBuffer = ByteBuffer.allocate(4);
        endianBuffer.order(ByteOrder.LITTLE_ENDIAN);
        os.write(CHUNKID.getBytes(Charset.forName("US-ASCII")));
        os.write(endianBuffer.putInt(getChunkSize() - 8).array());
        endianBuffer.clear();
        os.write(endianBuffer.putShort((short) this.audioFormat).array(), 0, endianBuffer.position());
        endianBuffer.clear();
        os.write(endianBuffer.putShort((short) this.channelsNum).array(), 0, endianBuffer.position());
        endianBuffer.clear();
        os.write(endianBuffer.putInt((short) this.sampleRate).array(), 0, endianBuffer.position());
        endianBuffer.clear();
        os.write(endianBuffer.putInt((short) byteRate).array(), 0, endianBuffer.position());
        endianBuffer.clear();
        os.write(endianBuffer.putShort(blockAlign).array(), 0, endianBuffer.position());
        endianBuffer.clear();
        os.write(endianBuffer.putShort((short) this.bitsPerSample).array(), 0, endianBuffer.position());
        for (int paddingCount = 0; paddingCount < this.padding; paddingCount++) {
            os.write(0);
        }
    }

    public int getChunkSize() {
        return this.padding + 24;
    }

    protected WavChunkFormat(ByteBuffer input) throws RuntimeException {
        int dataSize = input.getInt();
        int dataSizeStartPos = input.position();
        this.audioFormat = input.getShort();
        this.channelsNum = input.getShort();
        this.sampleRate = input.getInt();
        input.position(input.position() + 6);
        this.bitsPerSample = input.getShort();
        this.padding = dataSize - (input.position() - dataSizeStartPos);
        if (this.padding < 0) {
            throw new RuntimeException("Bad WAV format chunk");
        }
        input.position(dataSizeStartPos + dataSize);
    }
}
