package com.spiraltoys.cloudpets2.toy.task;

import android.os.AsyncTask;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.toy.ToyUtil;
import java.io.IOException;
import java.io.InputStream;

class ToyTaskUpdateFirmware$1 extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ ToyTaskUpdateFirmware this$0;

    ToyTaskUpdateFirmware$1(ToyTaskUpdateFirmware this$0) {
        this.this$0 = this$0;
    }

    protected Void doInBackground(Void... params) {
        int chunkIndex = 0;
        try {
            InputStream inputStream = this.this$0.getPeripheral().getContext().getAssets().open("firmware/CloudPets_Img" + (ToyTaskUpdateFirmware.access$000(this.this$0) ? "A" : "B") + "_" + "1.0.19" + ".bin");
            int len;
            do {
                byte[] chunk = new byte[18];
                System.arraycopy(ToyUtil.getBytes((short) chunkIndex), 0, chunk, 0, 2);
                len = inputStream.read(chunk, 2, 16);
                this.this$0.addData(chunk);
                if (chunkIndex == 0) {
                    ToyTaskUpdateFirmware.access$102(this.this$0, new byte[12]);
                    System.arraycopy(chunk, 2, ToyTaskUpdateFirmware.access$100(this.this$0), 0, 12);
                }
                chunkIndex++;
            } while (len != -1);
            inputStream.close();
            ToyTaskUpdateFirmware.access$202(this.this$0, chunkIndex);
        } catch (IOException e) {
        }
        return null;
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (ToyTaskUpdateFirmware.access$200(this.this$0) <= 0) {
            this.this$0.getListener().onFailure(this.this$0, null, new Error(this.this$0.getPeripheral().getContext().getString(R.string.error_firmware_not_valid)));
            return;
        }
        ToyTaskUpdateFirmware.access$302(this.this$0, ToyTaskUpdateFirmware$State.VERIFY);
        ToyTaskUpdateFirmware.access$400(this.this$0, true);
    }
}
