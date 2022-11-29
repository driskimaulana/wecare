package com.kelompok4.wecare.view.elder;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.text.DecimalFormat;

public class ElderMainActivity extends AppCompatActivity {

    DecimalFormat numberFormat = new DecimalFormat("0.00");
    Boolean limit = false;
    AlertDialog.Builder builder;
    MediaPlayer alarm;

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
                builder.setTitle("Alert!!!").setMessage("Take me home i'm fallin'").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        alarm.stop();
                        alarm.prepareAsync();

                        limit = false;
                        // code sent notif
                    }
                }).show();
                alarm.start();
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

        builder = new AlertDialog.Builder(this, R.style.DialogTheme);

        alarm = MediaPlayer.create(this, R.raw.warning_alarm);
        alarm.setLooping(true);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Bundle bundle = getIntent().getExtras();
        User currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        Toast.makeText(this, currentUser.getName(), Toast.LENGTH_SHORT).show();
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListener);
    }
}