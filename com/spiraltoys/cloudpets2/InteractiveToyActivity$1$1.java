package com.spiraltoys.cloudpets2;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import com.spiraltoys.cloudpets2.InteractiveToyActivity.1;

class InteractiveToyActivity$1$1 implements Runnable {
    final /* synthetic */ 1 this$1;

    InteractiveToyActivity$1$1(1 this$1) {
        this.this$1 = this$1;
    }

    public void run() {
        this.this$1.this$0.mVideoPlaceholder.setImageDrawable(new BitmapDrawable(this.this$1.this$0.getResources(), InteractiveToyActivity.access$000(this.this$1.this$0)));
        if (VERSION.SDK_INT >= 21) {
            this.this$1.this$0.mContainer.removeView(this.this$1.this$0.mPetVideoSurfaceView);
        }
    }
}
