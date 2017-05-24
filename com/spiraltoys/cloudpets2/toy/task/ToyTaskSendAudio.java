package com.spiraltoys.cloudpets2.toy.task;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.android.Convert;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.spiraltoys.cloudpets2.audio.AudioFormatException;
import com.spiraltoys.cloudpets2.audio.TranscoderUtil;
import com.spiraltoys.cloudpets2.audio.WavAudio;
import com.spiraltoys.cloudpets2.audio.WavUtil;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyAudioSlot;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral;
import com.spiraltoys.cloudpets2.toy.ToyPeripheral.State;
import com.spiraltoys.cloudpets2.toy.ToyState;
import com.spiraltoys.cloudpets2.toy.ToyUtil;
import com.spiraltoys.cloudpets2.toy.command.ToyCommandSendAudio;
import com.spiraltoys.cloudpets2.toy.event.ToyEventUpdateProgress;
import com.spiraltoys.cloudpets2.toy.task.ToyTask.Listener;
import de.greenrobot.event.EventBus;
import java.util.Arrays;
import java.util.UUID;

public class ToyTaskSendAudio extends ToyTaskWriteData {
    private static final byte COMMAND_START_UPLOAD = (byte) 0;
    private static final byte COMMAND_STOP_UPLOAD = (byte) 1;
    private static final int DATA_CHUNK_BYTES = 20;
    private static final long REQUEST_TIMEOUT = 5000;
    private static final int STREAMING_PAD_SIZE = 32;
    private static final long SUCCESS_SAFETY_DELAY = 250;
    private static final String TAG = ToyTaskSendAudio.class.getSimpleName();
    private ToyCommandSendAudio mCommand;
    private byte[] mCompressedData;
    private int mCompressedDataIndex;
    private Context mContext;
    private Handler mHandler;
    private boolean mIsSafeToStartWriting;
    private int mPercentage;
    private Runnable mTimeoutRunnable = new Runnable() {
        public void run() {
            Log.e(ToyTaskSendAudio.TAG, "ERROR: Audio transfer timed out!");
            ToyTaskSendAudio.this.getListener().onFailure(ToyTaskSendAudio.this, ToyTaskSendAudio.this.mCommand.getCommandIdentifier(), new Error(ToyTaskSendAudio.this.getPeripheral().getContext().getString(R.string.error_toy_gatt)));
        }
    };
    private boolean mToyRequestedData;

    public ToyTaskSendAudio(ToyPeripheral peripheral, ToyCommandSendAudio command, Context context) throws NullPointerException {
        super(peripheral, ToyPeripheral.SEND_AUDIO_CHAR_UUID);
        this.mContext = context;
        this.mCommand = command;
        this.mHandler = new Handler();
        this.mPercentage = -1;
    }

    public boolean isStreaming() {
        return this.mCommand.getSlot() == ToyAudioSlot.STREAMING;
    }

    public ToyState getState() {
        return ToyState.SENDING_AUDIO;
    }

    public void start(Listener listener) throws NullPointerException {
        super.start(listener);
        loadAudio();
    }

    private void loadAudio() {
        new AsyncTask<Uri, Void, WavAudio>() {
            protected WavAudio doInBackground(Uri... params) {
                return WavUtil.loadSync(ToyTaskSendAudio.this.mContext, params[0]);
            }

            protected void onPostExecute(WavAudio wavAudio) {
                super.onPostExecute(wavAudio);
                ToyTaskSendAudio.this.compressAudio(wavAudio);
            }
        }.execute(new Uri[]{this.mCommand.getLocalUri()});
    }

    private void compressAudio(final WavAudio wavAudio) {
        new AsyncTask<WavAudio, Void, short[]>() {
            protected short[] doInBackground(WavAudio... params) {
                short[] sArr = null;
                if (wavAudio != null) {
                    try {
                        sArr = TranscoderUtil.encode(wavAudio);
                    } catch (AudioFormatException e) {
                    }
                }
                return sArr;
            }

            protected void onPostExecute(short[] encoded) {
                if (encoded == null) {
                    ToyTaskSendAudio.this.getListener().onFailure(ToyTaskSendAudio.this, ToyTaskSendAudio.this.mCommand.getCommandIdentifier(), new Error(ToyTaskSendAudio.this.getPeripheral().getContext().getString(R.string.error_encode_audio_failed)));
                    return;
                }
                byte[] compressedData = new Convert().Shorts2Bytes(encoded);
                if (ToyTaskSendAudio.this.isStreaming()) {
                    ToyTaskSendAudio.this.mCompressedData = new byte[(compressedData.length + 32)];
                    System.arraycopy(compressedData, 0, ToyTaskSendAudio.this.mCompressedData, 0, 32);
                    System.arraycopy(compressedData, 0, ToyTaskSendAudio.this.mCompressedData, 32, compressedData.length);
                } else {
                    ToyTaskSendAudio.this.mCompressedData = compressedData;
                }
                ToyTaskSendAudio.this.startSendAudio();
            }
        }.execute(new WavAudio[]{wavAudio});
    }

    private void startSendAudio() {
        byte[] data = new byte[]{(byte) 0, this.mCommand.getSlotAsByte(), (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, Ascii.VT, Ascii.FF, Ascii.CR, Ascii.SO, Ascii.SI};
        this.mHandler.postDelayed(this.mTimeoutRunnable, 5000);
        getPeripheral().writeCharacteristic(ToyPeripheral.COMMAND_CHAR_UUID, data);
    }

    private void stopSendAudio() {
        getPeripheral().writeCharacteristic(ToyPeripheral.COMMAND_CHAR_UUID, new byte[]{(byte) 1});
    }

    void onProgress(int dataRemaining) {
        float progress = ((float) (this.mCompressedDataIndex - (dataRemaining * 20))) / ((float) this.mCompressedData.length);
        int percentage = Math.max(0, Math.min((int) (100.0f * progress), 99));
        if (this.mPercentage != percentage) {
            this.mPercentage = percentage;
            EventBus.getDefault().post(new ToyEventUpdateProgress(progress));
        }
    }

    void onCompleted(Error error) {
        if (error != null) {
            getListener().onFailure(this, this.mCommand.getCommandIdentifier(), error);
        } else if (isWriteComplete()) {
            stopSendAudio();
        } else {
            this.mHandler.postDelayed(this.mTimeoutRunnable, 5000);
        }
    }

    private boolean isWriteComplete() {
        if (this.mCompressedData == null || hasDataRemaining() || this.mCompressedDataIndex < this.mCompressedData.length) {
            return false;
        }
        return true;
    }

    private synchronized boolean queueData(int requestedSize) {
        boolean z;
        if (requestedSize != 0) {
            if (this.mCompressedDataIndex < this.mCompressedData.length) {
                int chunkCount = requestedSize / 20;
                for (int i = 0; i < chunkCount; i++) {
                    addChunk(20);
                }
                int remainder = requestedSize % 20;
                if (remainder > 0) {
                    addChunk(remainder);
                }
                z = true;
            }
        }
        z = false;
        return z;
    }

    private void addChunk(int chunkSize) {
        byte[] chunk = new byte[chunkSize];
        int dataRemaining = this.mCompressedData.length - this.mCompressedDataIndex;
        int usedCount = 0;
        if (dataRemaining > 0) {
            int length = chunkSize;
            if (dataRemaining < length) {
                length = dataRemaining;
            }
            System.arraycopy(this.mCompressedData, this.mCompressedDataIndex, chunk, 0, length);
            this.mCompressedDataIndex += length;
            usedCount = length;
        }
        for (int i = usedCount; i < chunkSize; i++) {
            chunk[i] = UnsignedBytes.MAX_POWER_OF_TWO;
        }
        addData(chunk);
    }

    public synchronized void onCharacteristicWrite(ToyPeripheral peripheral, UUID characteristic, byte[] value, Error error) {
        super.onCharacteristicWrite(peripheral, characteristic, value, error);
        if (ToyPeripheral.COMMAND_CHAR_UUID.equals(characteristic)) {
            if (!Arrays.equals(value, new byte[]{(byte) 1})) {
                this.mIsSafeToStartWriting = true;
                if (this.mToyRequestedData) {
                    startWriting();
                }
            }
        }
    }

    public synchronized void onCharacteristicChanged(ToyPeripheral peripheral, UUID characteristic, byte[] value) {
        super.onCharacteristicChanged(peripheral, characteristic, value);
        if (ToyPeripheral.DATA_REQUEST_CHAR_UUID.equals(characteristic)) {
            this.mHandler.removeCallbacks(this.mTimeoutRunnable);
            int requestedSize = ToyUtil.getNumber(value, 0, 2);
            if (requestedSize == 0) {
                int transferredCount = ToyUtil.getNumber(value, 2, 2);
                if (!isWriteComplete() || transferredCount <= 0) {
                    Log.e(TAG, "Toy notified failure to transfer audio");
                    getListener().onFailure(this, this.mCommand.getCommandIdentifier(), new Error(getPeripheral().getContext().getString(R.string.error_toy_gatt)));
                } else {
                    Log.i(TAG, "Toy notified " + transferredCount + " requests written successfully.");
                }
            } else {
                Log.v(TAG, "Toy requested " + requestedSize + " bytes of data");
                this.mToyRequestedData = true;
                if (queueData(requestedSize) && this.mIsSafeToStartWriting) {
                    startWriting();
                }
            }
        }
        if (isWriteComplete() && ToyPeripheral.STATE_CHAR_UUID.equals(characteristic) && getPeripheral().getCurrentState() == State.IDLE) {
            Log.v(TAG, "Write complete.");
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ToyTaskSendAudio.this.getListener().onSuccess(ToyTaskSendAudio.this, ToyTaskSendAudio.this.mCommand.getCommandIdentifier(), Boolean.valueOf(true));
                }
            }, SUCCESS_SAFETY_DELAY);
        }
    }
}
