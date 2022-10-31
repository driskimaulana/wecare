package com.kelompok4.wecare.view.relative.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.view.relative.RelativeSettingsActivity;

public class RelativeEmailSettings extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_email_settings);

        //Backbutton
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelativeEmailSettings.this, "Clicked", Toast.LENGTH_LONG);
                finish();
            }
        });

        //Email Changes
        TextView okbtn = (TextView) this.findViewById(R.id.textViewAcceptUpdates);
        EditText inputEmail = (EditText) this.findViewById(R.id.inputRelativeEmail);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RelativeEmailSettings.this, RelativeSettingsActivity.class);
                email = inputEmail.getText().toString();
                if (inputEmail.getText().toString().trim().length() == 0) email = "Example@example.com";
                i.putExtra("emailValue", email);
                startActivity(i);
                finish();
            }
        });

    }
}