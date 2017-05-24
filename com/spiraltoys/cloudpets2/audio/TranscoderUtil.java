package com.spiraltoys.cloudpets2.audio;

import com.android.AudioCodec.Audio32Encoder;
import com.android.AudioCodec.MsAdpcm;
import com.android.Convert;
import com.google.common.primitives.Shorts;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TranscoderUtil {
    static final int ENCODER_BUFFER_SIZE = 320;
    static final int HEADER_LENGTH = 16;

    public static short[] encode(WavAudio wavAudio) throws AudioFormatException {
        Convert convert = new Convert();
        short[] data = wavAudio.getData();
        int chunkCount = (int) Math.ceil(((double) data.length) / 320.0d);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        Audio32Encoder.init(WavRecorder.SAMPLE_RATE);
        for (int i = 0; i < chunkCount; i++) {
            int offset = i * ENCODER_BUFFER_SIZE;
            short[] chunk = Arrays.copyOfRange(data, offset, Math.min(offset + ENCODER_BUFFER_SIZE, data.length));
            try {
                if (wavAudio.getSampleRate() == WavRecorder.SAMPLE_RATE) {
                    dataOutputStream.write(convert.Shorts2Bytes(encodeFrom16KHzPCMMono(chunk)));
                } else if (wavAudio.getSampleRate() == 8000) {
                    dataOutputStream.write(convert.Shorts2Bytes(encodeFrom8KHzPCMMono(chunk)));
                } else {
                    throw new AudioFormatException(wavAudio.getSampleRate() + "Hz wav audio is not supported. Use 16KHz or 8KHz");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        short[] encodedData = convert.Bytes2Shorts(byteArrayOutputStream.toByteArray());
        Audio32Encoder.getHeader(new short[16]);
        return Shorts.concat(new short[][]{header, encodedData});
    }

    private static short[] encodeFrom16KHzPCMMono(short[] data16KHzPCM) {
        return Audio32Encoder.encode(new Convert().Shorts2Bytes(data16KHzPCM));
    }

    private static short[] encodeFrom8KHzPCMMono(short[] data8KHzPCM) {
        short[] data16KHzPCM = new short[(data8KHzPCM.length * 2)];
        for (int i = 0; i < data8KHzPCM.length; i++) {
            short sample8kHz = data8KHzPCM[i];
            int j = i * 2;
            data16KHzPCM[j] = sample8kHz;
            data16KHzPCM[j + 1] = sample8kHz;
        }
        return Shorts.concat(new short[][]{Audio32Encoder.encode(new Convert().Shorts2Bytes(Arrays.copyOfRange(data16KHzPCM, 0, data16KHzPCM.length / 2))), Audio32Encoder.encode(new Convert().Shorts2Bytes(Arrays.copyOfRange(data16KHzPCM, data16KHzPCM.length / 2, data16KHzPCM.length)))});
    }

    public static WavAudio decode(short[] source, int sampleRate) {
        if (source.length < 16) {
            return null;
        }
        short[] header = new short[16];
        System.arraycopy(source, 0, header, 0, 16);
        if (!MsAdpcm.init(header)) {
            return null;
        }
        short inSize = MsAdpcm.getInputSize();
        short outSize = MsAdpcm.getOutputSize();
        int chunkCount = (source.length - 16) / inSize;
        if ((source.length - 16) % inSize > 0) {
            chunkCount++;
        }
        short[] buffer = new short[inSize];
        short[] output = new short[((outSize * chunkCount) + 16)];
        short[] deData = new short[outSize];
        for (int i = 0; i < chunkCount; i++) {
            if (source.length - 16 > (i * inSize) + inSize) {
                System.arraycopy(source, (i * inSize) + 16, buffer, 0, inSize);
                MsAdpcm.decode(buffer, deData);
                System.arraycopy(deData, 0, output, (i * outSize) + 16, outSize);
            }
        }
        return new WavAudio(output, sampleRate, 4, 2);
    }
}
