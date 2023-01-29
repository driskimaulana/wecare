package com.kelompok4.wecare.view.relative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.ActivityConnectSuccessBinding;
import com.kelompok4.wecare.view.MainActivity;

public class ConnectSuccessActivity extends AppCompatActivity {

    private ActivityConnectSuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityConnectSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConnectSuccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}