package com.kelompok4.wecare.view.components;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.kelompok4.wecare.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private String timeStr = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener tlistener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hoursOfDay, int minute) {
                // Do something with the time chosen by the user
                if(view.isShown()) {
                    c.set(Calendar.HOUR_OF_DAY, hoursOfDay);
                    c.set(Calendar.MINUTE, minute);
                }

                if(minute != 0) {
                    timeStr = "Jam : " + hoursOfDay + ":" + minute;
                } else {
                    timeStr = "Jam : " + hoursOfDay + ":" + "00";
                }

                Button btn = (Button) requireActivity().findViewById(R.id.btn_setTime);
                btn.setText(timeStr);
            }
        };

        TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar, tlistener,
                hour, minute, true);
        dialog.setTitle(R.string.mulai_jam);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        return dialog;
    }

    public void onTimeSet(TimePicker view, int hoursOfDay, int minute) {}
}
