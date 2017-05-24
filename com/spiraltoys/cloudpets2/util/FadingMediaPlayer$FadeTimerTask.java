package com.spiraltoys.cloudpets2.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer.FadeTransitionListener;
import java.util.TimerTask;

class FadingMediaPlayer$FadeTimerTask extends TimerTask {
    final /* synthetic */ FadingMediaPlayer this$0;

    private FadingMediaPlayer$FadeTimerTask(FadingMediaPlayer fadingMediaPlayer) {
        this.this$0 = fadingMediaPlayer;
    }

    public void run() {
        if (FadingMediaPlayer.access$100(this.this$0)) {
            FadingMediaPlayer.access$200(this.this$0).cancel();
            FadingMediaPlayer.access$200(this.this$0).purge();
            if (FadingMediaPlayer.access$300(this.this$0) == 0.0f) {
                try {
                    if (!(this.this$0.getTrackInfo() == null || this.this$0.getTrackInfo()[0] == null || this.this$0.getTrackInfo()[0].getTrackType() == 1)) {
                        this.this$0.pause();
                    }
                } catch (RuntimeException ex) {
                    Log.d(FadingMediaPlayer.access$400(), "Ignoring RuntimeException on pause()");
                    ex.printStackTrace();
                }
            }
            if (FadingMediaPlayer.access$500(this.this$0) != null) {
                final FadeTransitionListener fadeTransitionListener = FadingMediaPlayer.access$500(this.this$0);
                FadingMediaPlayer.access$502(this.this$0, null);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        fadeTransitionListener.onFadeComplete();
                    }
                });
            }
        }
    }
}
