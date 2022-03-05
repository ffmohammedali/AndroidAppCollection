package com.example.projectdirectory;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    @NonNull
    private Calendar c =Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int min =c.get(Calendar.MINUTE);
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),hour,min,android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}

