package com.kelompok4.wecare.view.relative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.ActivityFallNotificationBinding;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.Locale;

public class FallNotificationActivity extends AppCompatActivity {

    private ActivityFallNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFallNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        User elderFall = GsonUtils.getGson().fromJson(bundle.getString("ELDER_FALL"), User.class);

        binding.namaTerjatuh.setText("Pak " + elderFall.getName() + " Terjatuh");
        binding.lihatLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=17&q=%f,%f", elderFall.getLocation().get(0), elderFall.getLocation().get(1), elderFall.getLocation().get(0), elderFall.getLocation().get(1));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }
}