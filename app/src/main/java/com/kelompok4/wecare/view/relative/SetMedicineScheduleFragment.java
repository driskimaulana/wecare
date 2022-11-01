package com.kelompok4.wecare.view.relative;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentSetMedicineScheduleBinding;
import com.kelompok4.wecare.view.components.TimePickerFragment;

public class SetMedicineScheduleFragment extends Fragment {

    private FragmentSetMedicineScheduleBinding binding;


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

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToHomeScreen);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToHomeScreen);
            }
        });

        binding.btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });

//        // show back button to action bar
//        assert getSupportActionBar() != null;
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(R.string.set_jadwal_minum_obat);
//
//        // change the color of back button
//        final Drawable backArrow = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24);
//        assert backArrow != null;
//        backArrow.setColorFilter(ContextCompat.getColor(this, R.color.blue_cola), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(backArrow);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSetMedicineScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getParentFragmentManager(), "timePicker");
    }
}