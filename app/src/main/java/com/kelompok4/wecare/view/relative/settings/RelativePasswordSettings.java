package com.kelompok4.wecare.view.relative.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kelompok4.wecare.R;

public class RelativePasswordSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_password_settings);

        //Backbutton
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelativePasswordSettings.this, "Clicked", Toast.LENGTH_LONG);
                finish();
            }
        });
    }
}