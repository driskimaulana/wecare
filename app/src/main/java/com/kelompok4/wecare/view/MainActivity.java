package com.kelompok4.wecare.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.location.AlwaysUpdate;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.elder.ElderMainActivity;
import com.kelompok4.wecare.view.relative.FallNotificationActivity;
import com.kelompok4.wecare.view.relative.RelativeMainActivity;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AlwaysUpdate alwaysUpdate;
    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    private User elderFall;

    private ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        alwaysUpdate = new AlwaysUpdate(0, 0, "");

        //        bundle current user
        Bundle bundle = getIntent().getExtras();
//        User elderFall = GsonUtils.getGson().fromJson(bundle.getString("data"), User.class);
        if (bundle != null) {
            if (bundle.getString("data") != null) {
                elderFall = GsonUtils.getGson().fromJson(bundle.getString("data"), User.class);
                Bundle elderFallen = new Bundle();
                elderFallen.putString("ELDER_FALL", GsonUtils.getGson().toJson(elderFall));
                Intent intent = new Intent(MainActivity.this, FallNotificationActivity.class);
                intent.putExtras(elderFallen);
                startActivity(intent);
                Toast.makeText(this, "data: " + elderFall.getName(), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

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
//            return;
        }else {

//        generate token untuk device
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()){
                                Log.w("wecare_debug", "Fetching FCM token is failed.", task.getException());
                                return;
                            }

                            // get new FCM token
                            String fcmToken = task.getResult();

                            Log.d("wecare_debug", fcmToken);
                            alwaysUpdate.setFcmToken(fcmToken);
                            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                                OnGPS();
                            }else {
                                getLocation();
                            }

                            Call<AuthResponse> getLoggedinUser = mApiInterface.getLoggedinUser("Bearer " + token, alwaysUpdate);
                            getLoggedinUser.enqueue(new Callback<AuthResponse>() {
                                @Override
                                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                                    Log.d("GetLoggedinUser", "SUKSES");
                                    if (response.body() == null) {
                                        return;
                                    }
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
                        }
                    });


        }


    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                alwaysUpdate.setLatitude(locationGPS.getLatitude());
                alwaysUpdate.setLongitude(locationGPS.getLongitude());
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}