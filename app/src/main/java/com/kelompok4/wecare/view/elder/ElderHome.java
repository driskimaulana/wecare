package com.kelompok4.wecare.view.elder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kelompok4.wecare.databinding.ActivityElderHomeBinding;
import com.kelompok4.wecare.view.relative.RelativeSetSchedule;

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

    public void sosBtnClicked(View v) {
        Intent intent =  new Intent(this, RelativeSetSchedule.class);
        startActivity(intent);
    }
}