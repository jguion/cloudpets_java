package com.spiraltoys.cloudpets2.fragments;

import android.net.Uri;
import android.widget.Toast;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.CacheUtil.Callback;

class AudioPlayerFragment$2 implements Callback {
    final /* synthetic */ AudioPlayerFragment this$0;

    AudioPlayerFragment$2(AudioPlayerFragment this$0) {
        this.this$0 = this$0;
    }

    public void done(Uri localCacheUri, Exception e) {
        if (this.this$0.isAdded() && !this.this$0.isRemoving()) {
            if (e != null) {
                Toast.makeText(this.this$0.getActivity(), R.string.error_message_could_not_be_downloaded, 1).show();
                return;
            }
            AudioPlayerFragment.access$302(this.this$0, true);
            AudioPlayerFragment.access$000(this.this$0).init(this.this$0.getActivity(), localCacheUri);
        }
    }
}
