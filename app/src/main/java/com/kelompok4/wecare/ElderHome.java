package com.kelompok4.wecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kelompok4.wecare.databinding.ActivityElderHomeBinding;

public class ElderHome extends AppCompatActivity {
    private ActivityElderHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityElderHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void qrBtnClicked(View v) {
        Intent intent =  new Intent(this, ElderAccountConnection.class);
        startActivity(intent);
    }
}