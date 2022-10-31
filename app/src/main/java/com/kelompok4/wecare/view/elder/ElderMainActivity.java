package com.kelompok4.wecare.view.elder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kelompok4.wecare.R;

public class ElderMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder_main);

        getSupportActionBar().hide();
    }
}