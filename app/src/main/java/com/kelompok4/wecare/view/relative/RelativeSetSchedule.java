package com.kelompok4.wecare.view.relative;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.TimePickerFragment;
import com.kelompok4.wecare.databinding.FragmentRelativeSetScheduleBinding;

public class RelativeSetSchedule extends AppCompatActivity {

    private FragmentRelativeSetScheduleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentRelativeSetScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // show back button to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.set_jadwal_minum_obat);

        // change the color of back button
        final Drawable backArrow = ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24);
        assert backArrow != null;
        backArrow.setColorFilter(ContextCompat.getColor(this, R.color.blue_cola), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}