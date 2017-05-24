package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import com.spiraltoys.cloudpets2.toy.ToyService;
import java.io.File;

class Utils$2 implements Runnable {
    final /* synthetic */ Context val$context;

    Utils$2(Context context) {
        this.val$context = context;
    }

    public void run() {
        File[] toyRecordingFiles = new File(this.val$context.getCacheDir() + File.separator + ToyService.TOY_RECORDINGS_FOLDER_NAME).listFiles();
        if (toyRecordingFiles != null) {
            for (File toyRecordingFile : toyRecordingFiles) {
                toyRecordingFile.delete();
            }
        }
    }
}
