package com.spiraltoys.cloudpets2.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.adapters.VoiceMessageAdapter;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;

class VoiceMessagesFragment$2 implements GetCallback<Profile> {
    final /* synthetic */ VoiceMessagesFragment this$0;
    final /* synthetic */ RecyclerView val$recyclerView;

    VoiceMessagesFragment$2(VoiceMessagesFragment this$0, RecyclerView recyclerView) {
        this.this$0 = this$0;
        this.val$recyclerView = recyclerView;
    }

    public void done(Profile profile, ParseException e) {
        if (this.this$0.isAdded() && !this.this$0.isRemoving()) {
            VoiceMessagesFragment.access$102(this.this$0, new VoiceMessageAdapter(ModelHelper.getVoiceMessagesLocalDatastoreQuery(profile, VoiceMessagesFragment.access$200(this.this$0) == ProfileType.CHILD)));
            VoiceMessagesFragment.access$100(this.this$0).registerAdapterDataObserver(new AdapterDataObserver() {
                public void onChanged() {
                    VoiceMessagesFragment.access$300(VoiceMessagesFragment$2.this.this$0);
                    VoiceMessagesFragment.access$000(VoiceMessagesFragment$2.this.this$0).swipeRefreshLayout.setRefreshing(false);
                }
            });
            this.val$recyclerView.setAdapter(VoiceMessagesFragment.access$100(this.this$0));
        }
    }
}
