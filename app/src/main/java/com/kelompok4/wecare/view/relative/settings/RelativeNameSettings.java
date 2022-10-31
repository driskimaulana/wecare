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

public class RelativeNameSettings extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_name_settings);

        //Backbutton
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelativeNameSettings.this, "Clicked", Toast.LENGTH_LONG);
                finish();
            }
        });

        //Name Changes
        TextView okbtn = (TextView) this.findViewById(R.id.textViewAcceptUpdates);
        EditText inputName = (EditText) this.findViewById(R.id.inputRelativeName);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RelativeNameSettings.this, RelativeSettingsActivity.class);
                name = inputName.getText().toString();
//                if (inputName.getText().toString().trim().length() == 0) name = "Nama";
                i.putExtra("nameValue", name);
                startActivity(i);
                finish();
            }
        });

    }
}