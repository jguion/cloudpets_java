package com.spiraltoys.cloudpets2.fragments;

import android.text.Editable;
import android.text.TextWatcher;

class SelectPetAvatarFragment$1 implements TextWatcher {
    final /* synthetic */ SelectPetAvatarFragment this$0;

    SelectPetAvatarFragment$1(SelectPetAvatarFragment this$0) {
        this.this$0 = this$0;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (SelectPetAvatarFragment.access$000(this.this$0) != null) {
            SelectPetAvatarFragment.access$000(this.this$0).onPetChanged();
        }
    }

    public void afterTextChanged(Editable editable) {
    }
}
