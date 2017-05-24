package com.spiraltoys.cloudpets2.audio;

import android.media.AudioRecord;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WavRecorder {
    public static final int CHANNELS = 16;
    public static final int ENCODING = 2;
    public static final int SAMPLE_RATE = 16000;
    private final int mBufferSize;
    private boolean mIsRecording;
    private File mOutputFile;
    private AudioRecord mRecorder;

    public WavRecorder(File outputFile) {
        if (outputFile == null) {
            throw new IllegalArgumentException("Output file cannot be null.");
        }
        this.mBufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, 16, 2);
        this.mOutputFile = outputFile;
    }

    public void startRecording() {
        this.mRecorder = new AudioRecord(1, SAMPLE_RATE, 16, 2, this.mBufferSize);
        this.mRecorder.startRecording();
        this.mIsRecording = true;
        new Thread(new Runnable() {
            public void run() {
                WavRecorder.this.writeAudioDataToFile();
            }
        }).start();
    }

    public void stopRecording() {
        if (this.mRecorder != null) {
            this.mIsRecording = false;
            this.mRecorder.stop();
            this.mRecorder.release();
            this.mRecorder = null;
        }
    }

    public File getOutputFile() {
        return this.mOutputFile;
    }

    private void writeAudioDataToFile() {
        IOException e;
        Throwable th;
        byte[] buf = new byte[this.mBufferSize];
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream os = new FileOutputStream(this.mOutputFile);
            while (this.mIsRecording) {
                try {
                    this.mRecorder.read(buf, 0, this.mBufferSize);
                    os.write(buf, 0, this.mBufferSize);
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = os;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = os;
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                    fileOutputStream = os;
                    return;
                }
            }
            fileOutputStream = os;
        } catch (IOException e4) {
            e3 = e4;
            try {
                e3.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }
}
