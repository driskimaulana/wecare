package com.kelompok4.wecare.view.relative;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentRelativeSetMedicineScheduleBinding;
import com.kelompok4.wecare.view.components.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SetMedicineScheduleFragment extends Fragment {
    private FragmentRelativeSetMedicineScheduleBinding binding;
    final Calendar calendar = Calendar.getInstance();
    EditText dateStart, dateEnd;


    public SetMedicineScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateStart = binding.getRoot().findViewById(R.id.medicine_date_picker_start);
        dateEnd = binding.getRoot().findViewById(R.id.medicine_date_picker_end);

        DatePickerDialog.OnDateSetListener dateStartListener = (dateView, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel(dateStart);
        };

        DatePickerDialog.OnDateSetListener dateEndListener = (dateView, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel(dateEnd);
        };

        dateStart.setOnClickListener(dateStartView -> {
            new DatePickerDialog(getContext(), R.style.DialogTheme, dateStartListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        dateEnd.setOnClickListener(dateEndView -> {
            new DatePickerDialog(getContext(), R.style.DialogTheme, dateEndListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToHomeScreen);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        binding.btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRelativeSetMedicineScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getParentFragmentManager(), "timePicker");
    }

    public void updateLabel(EditText date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }
}