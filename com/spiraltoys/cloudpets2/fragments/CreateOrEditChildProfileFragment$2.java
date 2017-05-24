package com.spiraltoys.cloudpets2.fragments;

import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

class CreateOrEditChildProfileFragment$2 implements InputFilter {
    final /* synthetic */ CreateOrEditChildProfileFragment this$0;

    CreateOrEditChildProfileFragment$2(CreateOrEditChildProfileFragment this$0) {
        this.this$0 = this$0;
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String textToCheck = source.subSequence(start, end).toString();
        if (source instanceof SpannableStringBuilder) {
            boolean isChanged = false;
            SpannableStringBuilder stringBuilder = (SpannableStringBuilder) source;
            for (int i = end - 1; i >= start; i--) {
                if (CreateOrEditChildProfileFragment.INVALID_USERNAME_CHARACTER_SEQUENCES_PATTERN.matcher(source.subSequence(i, i + 1)).matches()) {
                    stringBuilder.delete(i, i + 1);
                    isChanged = true;
                }
            }
            if (isChanged) {
                return stringBuilder;
            }
            return null;
        }
        String replacement = CreateOrEditChildProfileFragment.INVALID_USERNAME_CHARACTER_SEQUENCES_PATTERN.matcher(source.subSequence(start, end)).replaceAll("");
        if (textToCheck.equals(replacement)) {
            replacement = null;
        }
        return replacement;
    }
}
