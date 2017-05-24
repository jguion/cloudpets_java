package com.spiraltoys.cloudpets2.toy.task;

import android.net.Uri;
import android.os.AsyncTask;
import com.android.Convert;
import com.spiraltoys.cloudpets2.audio.TranscoderUtil;
import com.spiraltoys.cloudpets2.audio.WavAudio;
import com.spiraltoys.cloudpets2.audio.WavUtil;
import com.spiraltoys.cloudpets2.free.R;
import java.io.ByteArrayOutputStream;
import java.io.File;

class ToyTaskReceiveAudio$1 extends AsyncTask<ByteArrayOutputStream, Void, Uri> {
    final /* synthetic */ ToyTaskReceiveAudio this$0;

    ToyTaskReceiveAudio$1(ToyTaskReceiveAudio this$0) {
        this.this$0 = this$0;
    }

    protected Uri doInBackground(ByteArrayOutputStream... params) {
        WavAudio wavAudio = TranscoderUtil.decode(new Convert().Bytes2Shorts(params[0].toByteArray()), 8000);
        if (wavAudio == null) {
            return null;
        }
        File outFile = new File(ToyTaskReceiveAudio.access$000(this.this$0));
        if (!outFile.getParentFile().mkdirs() && !outFile.getParentFile().isDirectory()) {
            return null;
        }
        WavUtil.saveSync(wavAudio, outFile);
        return Uri.parse(ToyTaskReceiveAudio.access$000(this.this$0));
    }

    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
        if (uri != null) {
            this.this$0.getListener().onSuccess(this.this$0, null, uri);
            return;
        }
        this.this$0.getListener().onFailure(this.this$0, null, new Error(this.this$0.getPeripheral().getContext().getString(R.string.error_decode_audio_failed)));
    }
}
