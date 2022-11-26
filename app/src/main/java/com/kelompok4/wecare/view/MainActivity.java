package com.kelompok4.wecare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.view.elder.ElderMainActivity;
import com.kelompok4.wecare.view.relative.RelativeMainActivity;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.const_token_key), "");

        if (token.length() == 0) {
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }, 3000);
        }

        Call<AuthResponse> getLoggedinUser = mApiInterface.getLoggedinUser("Bearer " + token);
        getLoggedinUser.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.d("GetLoggedinUser", "SUKSES");
                Bundle bundle = new Bundle();
                bundle.putString("USER_LOGGED_IN" , GsonUtils.getGson().toJson(response.body().getResult()));

                Intent intent;
                if (response.body().getResult().getRole().equals("Relative")) {
                    intent = new Intent(MainActivity.this, RelativeMainActivity.class);
                }else {
                    intent = new Intent(MainActivity.this, ElderMainActivity.class);
                }
                intent.putExtras(bundle);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Bisa " + response.body().getResult().getRole(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("GetLoggedinUser", "onFailure: ");
                Toast.makeText(MainActivity.this, "gabisa :(", Toast.LENGTH_SHORT).show();
            }
        });


//        LayoutInflater mInflater = LayoutInflater.from(this);
//        View mCustomView = mInflater.inflate(R.layout.activity_pengaturan_user, null);
//        ImageView backButton = (ImageView) this.findViewById(R.id.backButton);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}