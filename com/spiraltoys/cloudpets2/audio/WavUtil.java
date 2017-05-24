package com.spiraltoys.cloudpets2.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetFileDescriptor.AutoCloseInputStream;
import android.net.Uri;
import android.util.Log;
import com.google.common.io.ByteStreams;
import com.parse.ParseException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class WavUtil {
    public static byte[] sampleToFileBytesSync(WavAudio sample) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writeFileData(os, sample);
            return os.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean saveSync(WavAudio sample, File file) {
        try {
            writeFileData(new FileOutputStream(file), sample);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static WavAudio loadSync(Context context, int resource) throws RuntimeException {
        return loadSync(context.getResources().openRawResource(resource));
    }

    public static WavAudio loadSync(Context context, Uri uri) throws RuntimeException {
        Exception ex;
        WavAudio wavAudio = null;
        if ("android.resource".equals(uri.getScheme())) {
            AssetFileDescriptor fd = null;
            try {
                fd = context.getContentResolver().openAssetFileDescriptor(uri, "r");
                if (fd != null) {
                    wavAudio = loadSync(new AutoCloseInputStream(fd));
                    if (fd != null) {
                        try {
                            fd.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (fd != null) {
                    try {
                        fd.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                ex = e3;
                try {
                    ex.printStackTrace();
                    if (fd != null) {
                        try {
                            fd.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return wavAudio;
                } catch (Throwable th) {
                    if (fd != null) {
                        try {
                            fd.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                }
            } catch (Exception e32) {
                ex = e32;
                ex.printStackTrace();
                if (fd != null) {
                    fd.close();
                }
                return wavAudio;
            }
        }
        try {
            wavAudio = loadSync(new FileInputStream(new File(uri.getPath())));
        } catch (IOException e4) {
        }
        return wavAudio;
    }

    private static WavAudio loadSync(InputStream is) throws RuntimeException {
        IOException e;
        ByteBuffer dataBuffer;
        WavChunkFormat formatChunk;
        int channelConfig;
        WavChunkData dataChunk;
        Throwable th;
        byte[] wavData = null;
        ByteArrayOutputStream os = null;
        try {
            ByteArrayOutputStream os2 = new ByteArrayOutputStream();
            try {
                wavData = (ByteStreams.copy(is, os2) > 0 ? 1 : (ByteStreams.copy(is, os2) == 0 ? 0 : -1)) > 0 ? os2.toByteArray() : null;
                try {
                    is.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                if (os2 != null) {
                    try {
                        os2.close();
                        os = os2;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        os = os2;
                    }
                }
            } catch (IOException e3) {
                e22 = e3;
                os = os2;
                try {
                    e22.printStackTrace();
                    try {
                        is.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    if (wavData != null) {
                        return null;
                    }
                    dataBuffer = ByteBuffer.wrap(wavData);
                    dataBuffer.order(ByteOrder.LITTLE_ENDIAN);
                    try {
                        if (WavHeader.tryDeserialize(dataBuffer) != null) {
                            return null;
                        }
                        formatChunk = WavChunkFormat.tryDeserialize(dataBuffer);
                        if (formatChunk != null) {
                            return null;
                        }
                        if (formatChunk.audioFormat == 1) {
                            return null;
                        }
                        if (formatChunk.bitsPerSample == 16) {
                            return null;
                        }
                        switch (formatChunk.channelsNum) {
                            case 1:
                                channelConfig = 4;
                                break;
                            case 2:
                                channelConfig = 12;
                                break;
                            case 4:
                                channelConfig = ParseException.EMAIL_MISSING;
                                break;
                            default:
                                return null;
                        }
                        while (true) {
                            dataChunk = WavChunkData.tryDeserialize(dataBuffer);
                            if (dataChunk == null) {
                                return new WavAudio(dataChunk.getDataAsShorts(), formatChunk.sampleRate, channelConfig, 2);
                            }
                            skipChunk(dataBuffer);
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        is.close();
                    } catch (IOException e22222) {
                        e22222.printStackTrace();
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e222222) {
                            e222222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                os = os2;
                is.close();
                if (os != null) {
                    os.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            e222222 = e5;
            e222222.printStackTrace();
            is.close();
            if (os != null) {
                os.close();
            }
            if (wavData != null) {
                return null;
            }
            dataBuffer = ByteBuffer.wrap(wavData);
            dataBuffer.order(ByteOrder.LITTLE_ENDIAN);
            if (WavHeader.tryDeserialize(dataBuffer) != null) {
                return null;
            }
            formatChunk = WavChunkFormat.tryDeserialize(dataBuffer);
            if (formatChunk != null) {
                return null;
            }
            if (formatChunk.audioFormat == 1) {
                return null;
            }
            if (formatChunk.bitsPerSample == 16) {
                return null;
            }
            switch (formatChunk.channelsNum) {
                case 1:
                    channelConfig = 4;
                    break;
                case 2:
                    channelConfig = 12;
                    break;
                case 4:
                    channelConfig = ParseException.EMAIL_MISSING;
                    break;
                default:
                    return null;
            }
            while (true) {
                dataChunk = WavChunkData.tryDeserialize(dataBuffer);
                if (dataChunk == null) {
                    return new WavAudio(dataChunk.getDataAsShorts(), formatChunk.sampleRate, channelConfig, 2);
                }
                skipChunk(dataBuffer);
            }
        }
        if (wavData != null) {
            return null;
        }
        dataBuffer = ByteBuffer.wrap(wavData);
        dataBuffer.order(ByteOrder.LITTLE_ENDIAN);
        if (WavHeader.tryDeserialize(dataBuffer) != null) {
            return null;
        }
        formatChunk = WavChunkFormat.tryDeserialize(dataBuffer);
        if (formatChunk != null) {
            return null;
        }
        if (formatChunk.audioFormat == 1) {
            return null;
        }
        if (formatChunk.bitsPerSample == 16) {
            return null;
        }
        switch (formatChunk.channelsNum) {
            case 1:
                channelConfig = 4;
                break;
            case 2:
                channelConfig = 12;
                break;
            case 4:
                channelConfig = ParseException.EMAIL_MISSING;
                break;
            default:
                return null;
        }
        while (true) {
            dataChunk = WavChunkData.tryDeserialize(dataBuffer);
            if (dataChunk == null) {
                return new WavAudio(dataChunk.getDataAsShorts(), formatChunk.sampleRate, channelConfig, 2);
            }
            skipChunk(dataBuffer);
        }
    }

    public static void writeFileData(OutputStream os, WavAudio sample) throws IOException {
        try {
            WavHeader header = new WavHeader();
            WavChunkFormat formatChunk = new WavChunkFormat(sample.getSampleRate(), sample.getChannelsNum(), 16);
            WavChunkFiller fillerChunk = new WavChunkFiller();
            WavChunkData dataChunk = new WavChunkData();
            fillerChunk.setPadding(4096 - (((header.getChunkSize() + formatChunk.getChunkSize()) + fillerChunk.getChunkSize()) + dataChunk.getChunkSize()));
            dataChunk.setShorts(sample.getData());
            header.size = (header.getChunkSize() + formatChunk.getChunkSize()) + dataChunk.getChunkSize();
            header.serialize(os);
            formatChunk.serialize(os);
            fillerChunk.serialize(os);
            dataChunk.serialize(os);
        } finally {
            os.close();
        }
    }

    private static void skipChunk(ByteBuffer input) {
        byte[] chunkTag = new byte[4];
        input.get(chunkTag);
        int chunkSize = input.getInt();
        if (chunkSize < 8) {
            throw new RuntimeException("Encountered a bad size for chunk '" + new String(chunkTag, Charset.forName("US-ASCII")) + "'.");
        }
        Log.i("WavUtil", "Skipping chunk '" + new String(chunkTag, Charset.forName("US-ASCII")) + "'.");
        input.position(input.position() + chunkSize);
    }
}
