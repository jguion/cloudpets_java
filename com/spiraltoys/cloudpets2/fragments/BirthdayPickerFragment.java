package com.spiraltoys.cloudpets2.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import com.spiraltoys.cloudpets2.free.R;
import java.util.Calendar;

public class BirthdayPickerFragment extends DialogFragment implements OnDateSetListener {
    private OnBirthdayPickerInteractionListener mListener;

    public interface OnBirthdayPickerInteractionListener {
        void onDateSelect(Calendar calendar);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        Dialog dialog = new DatePickerDialog(getActivity(), this, c.get(1), c.get(2), c.get(5));
        dialog.setTitle(R.string.title_dialog_birthday);
        return dialog;
    }

    public void setListener(OnBirthdayPickerInteractionListener listener) {
        this.mListener = listener;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(year, month, day, 0, 0, 0);
        if (this.mListener != null) {
            this.mListener.onDateSelect(selectedDate);
        }
    }
}
