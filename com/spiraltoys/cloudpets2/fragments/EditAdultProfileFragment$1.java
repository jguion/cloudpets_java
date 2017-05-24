package com.spiraltoys.cloudpets2.fragments;

import android.text.Editable;
import android.text.TextWatcher;

class EditAdultProfileFragment$1 implements TextWatcher {
    final /* synthetic */ EditAdultProfileFragment this$0;

    EditAdultProfileFragment$1(EditAdultProfileFragment this$0) {
        this.this$0 = this$0;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (this.this$0.mListener != null) {
            this.this$0.mListener.onAdultProfileChanged();
        }
    }

    public void afterTextChanged(Editable s) {
    }
}
