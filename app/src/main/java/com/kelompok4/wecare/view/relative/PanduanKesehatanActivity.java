package com.kelompok4.wecare.view.relative;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.PanduanKesehatanModel;
import com.kelompok4.wecare.view.relative.adapter.PanduanKesehatanAdapter;

import java.util.ArrayList;

public class PanduanKesehatanActivity extends Activity {

    ArrayList<PanduanKesehatanModel> panduanKesehatanModels = new ArrayList<>();

    int panduanKesehatanImages = R.drawable.person_presses_heart; // ganti ke int array kalau udah ada foto selain yang ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_kesehatan);

        RecyclerView recyclerView = findViewById(R.id.recyclerPanduan);

        setUpPanduanKesehatanModels();

        PanduanKesehatanAdapter adapter = new PanduanKesehatanAdapter(this, panduanKesehatanModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.activity_panduan_kesehatan, null);
        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PanduanKesehatanActivity.this, "Clicked", Toast.LENGTH_LONG);
                finish();
            }
        });
    }

    private void setUpPanduanKesehatanModels(){
        String[] panduanTitle = getResources().getStringArray(R.array.titlePanduan);
        String panduanDesc = getResources().getString(R.string.lorem); //ganti ke array of string kalau udah ada selain lorem ipsum

        for (int i = 0; i < panduanTitle.length; i++){
            panduanKesehatanModels.add(new PanduanKesehatanModel(panduanTitle[i], panduanDesc, panduanKesehatanImages));
        }
    }
}