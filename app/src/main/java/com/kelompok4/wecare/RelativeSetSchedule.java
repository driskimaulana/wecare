package com.kelompok4.wecare;

import android.app.ActionBar;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.kelompok4.wecare.databinding.ActivityRelativeSetScheduleBinding;

public class RelativeSetSchedule extends AppCompatActivity {

    private ActivityRelativeSetScheduleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRelativeSetScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // show back button to action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}