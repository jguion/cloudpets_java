package com.spiraltoys.cloudpets2.fragments;

import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.model.util.AccountHelper$PasswordCheckCallback;

class ProfileSwitcherDialogFragment$1 implements AccountHelper$PasswordCheckCallback {
    final /* synthetic */ ProfileSwitcherDialogFragment this$0;

    ProfileSwitcherDialogFragment$1(ProfileSwitcherDialogFragment this$0) {
        this.this$0 = this$0;
    }

    public void onPasswordChecked(boolean isCorrect) {
        if (isCorrect) {
            if (ProfileSwitcherDialogFragment.access$000(this.this$0) != null) {
                ProfileSwitcherDialogFragment.access$000(this.this$0).onProfileSelected(ProfileSwitcherDialogFragment.access$100(this.this$0));
            }
            this.this$0.dismiss();
        } else {
            ProfileSwitcherDialogFragment.access$200(this.this$0);
        }
        if (this.this$0.getActivity() instanceof BaseActivity) {
            ((BaseActivity) this.this$0.getActivity()).hideProgressDelayed();
        }
    }
}
