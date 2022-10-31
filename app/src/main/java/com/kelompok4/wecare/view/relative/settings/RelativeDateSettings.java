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

import java.util.Date;

public class RelativeDateSettings extends AppCompatActivity {
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_date_settings);

        //Backbutton
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RelativeDateSettings.this, "Clicked", Toast.LENGTH_LONG);
                finish();
            }
        });

        //Email Changes
        TextView okbtn = (TextView) this.findViewById(R.id.textViewAcceptUpdates);
        EditText inputDate = (EditText) this.findViewById(R.id.inputRelativeDate);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RelativeDateSettings.this, RelativeSettingsActivity.class);
                date = inputDate.getText().toString();
                if (inputDate.getText().toString().trim().length() == 0) date = "01/01/1991";
                i.putExtra("dateValue", date);
                startActivity(i);
                finish();
            }
        });

    }
}