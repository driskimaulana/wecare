package com.kelompok4.wecare.view.relative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.wecare.LoginActivity;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.view.relative.settings.RelativeDateSettings;
import com.kelompok4.wecare.view.relative.settings.RelativeEmailSettings;
import com.kelompok4.wecare.view.relative.settings.RelativeNameSettings;
import com.kelompok4.wecare.view.relative.settings.RelativePasswordSettings;

import org.w3c.dom.Text;

public class RelativeSettingsActivity extends AppCompatActivity {

    String name;
    String email;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_settings);

        //Backbutton
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.activity_relative_settings, null);
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelativeSettingsActivity.this, "Clicked", Toast.LENGTH_LONG);
                finish();
            }
        });

        //Change Name
        ImageView nameButton = (ImageView) this.findViewById(R.id.imageViewGoSetNama);
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelativeSettingsActivity.this, RelativeNameSettings.class));
            }
        });

        //Change Date
        ImageView dateButton = (ImageView) this.findViewById(R.id.imageViewGoSetTanggalLahir);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelativeSettingsActivity.this, RelativeDateSettings.class));
            }
        });

        //Change Email
        ImageView emailButton = (ImageView) this.findViewById(R.id.imageViewGoSetEmail);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelativeSettingsActivity.this, RelativeEmailSettings.class));
            }
        });

        //Change Password
        ImageView passwordButton = (ImageView) this.findViewById(R.id.imageViewGoUpdatePassword);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelativeSettingsActivity.this, RelativePasswordSettings.class));
            }
        });

        //Applying Updates
        TextView tvNama = (TextView) this.findViewById(R.id.textViewNamaIsi);
        if (getIntent().hasExtra("nameValue")) name = getIntent().getExtras().getString("nameValue");
        else name = "Nama";
        tvNama.setText(name);

        TextView tvEmail = (TextView) this.findViewById(R.id.textViewEmailIsi);
        if (getIntent().hasExtra("emailValue")) email = getIntent().getExtras().getString("emailValue");
        else email = "Example@example.com";
        tvEmail.setText(email);

        TextView tvDate = (TextView) this.findViewById(R.id.textViewTanggalLahirIsi);
        if (getIntent().hasExtra("dateValue")) date = getIntent().getExtras().getString("dateValue");
        else date = "01/01/1991";
        tvDate.setText(date);
    }
}