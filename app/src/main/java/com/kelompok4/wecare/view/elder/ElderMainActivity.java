package com.kelompok4.wecare.view.elder;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.notification.DangerResponse;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElderMainActivity extends AppCompatActivity {

    DecimalFormat numberFormat = new DecimalFormat("0.00");
    Boolean limit = false;
    AlertDialog.Builder builder;
    MediaPlayer alarm;

    private NavController navController;

    private ApiInterface mApiInterface;
    private String jwtToken;

    private ProgressDialog pd;

    private com.kelompok4.wecare.model.location.Location myLocation;
    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            double x = sensorEvent.values[0];
            double y = sensorEvent.values[1];
            double z = sensorEvent.values[2];

            double acceleration = Math.sqrt(x * x + y * y + z * z);
            double finalAcc = Double.parseDouble(numberFormat.format(acceleration));

            if (!limit && finalAcc < 3) {
                pd = ProgressDialog.show(ElderMainActivity.this, "Loading ...", "Mengirim sinyal bahaya.", false);

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    OnGPS();
                }else {
                    getLocation();
                }
                mApiInterface.sendDangerSignal("Bearer " + jwtToken, myLocation).enqueue(new Callback<DangerResponse>() {
                    @Override
                    public void onResponse(Call<DangerResponse> call, Response<DangerResponse> response) {
                        Toast.makeText(ElderMainActivity.this, response.body().getSendTo().get(0).getName(), Toast.LENGTH_SHORT).show();
                        String dangerResponse = GsonUtils.getGson().toJson(response.body());
                        Bundle bundle = new Bundle();
                        bundle.putString("DANGER_SIGNAL_RESPONSE", dangerResponse);
                        navController.navigate(R.id.waitingResponseSosFragment, bundle);
//                                Navigation.findNavController(myView).navigate(R.id.waitingResponseSosFragment, bundle);
                    }
                    @Override
                    public void onFailure(Call<DangerResponse> call, Throwable t) {
                        Toast.makeText(ElderMainActivity.this, "Send danger signal failed.", Toast.LENGTH_SHORT).show();
                        Log.e("SOS Failed", "Send danger signal failed." );
                    }
                });
                limit = true;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder_main);

        getSupportActionBar().hide();

        // get navcontroller
        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        navController = hostFragment.getNavController();
        
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        myLocation = new com.kelompok4.wecare.model.location.Location(0, 0);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        // get jwt token from shared pref
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        jwtToken = sharedPreferences.getString(getString(R.string.const_token_key), "");

        //        bundle current user
        Bundle bundle = getIntent().getExtras();
        User currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        Toast.makeText(this, currentUser.getName(), Toast.LENGTH_SHORT).show();

        builder = new AlertDialog.Builder(this, R.style.DialogTheme);

        alarm = MediaPlayer.create(this, R.raw.warning_alarm);
        alarm.setLooping(true);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListener);
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
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                myLocation.setLatitude(locationGPS.getLatitude());
                myLocation.setLongitude(locationGPS.getLongitude());
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}