package com.kelompok4.wecare.view.elder;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderHomeBinding;
import com.kelompok4.wecare.model.location.AlwaysUpdate;
import com.kelompok4.wecare.model.notification.DangerResponse;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElderHomeFragment extends Fragment {

    private FragmentElderHomeBinding binding;
    private ApiInterface mApiInterface;
    private User currentUser;

    private com.kelompok4.wecare.model.location.Location myLocation;
    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;

    private String jwtToken;

    public ElderHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialize api interface
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        myLocation = new com.kelompok4.wecare.model.location.Location(0, 0);

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        // get jwt token from shared pref
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        jwtToken = sharedPreferences.getString(getString(R.string.const_token_key), "");

        //        bundle current user
        Bundle bundle = getActivity().getIntent().getExtras();
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        binding.btnSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), currentUser.getName(), Toast.LENGTH_SHORT).show();
                sosBtnClicked(view);
            }
        });

        binding.btnQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrBtnClicked(view);
            }
        });

        binding.tvMinumObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { medicineListBtnClicked(view); }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentElderHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void qrBtnClicked(View v) {
        Navigation.findNavController(v).navigate(R.id.navigateToElderQr);
//        Intent intent =  new Intent(this, ElderQrCodeFragment.class);
//        startActivity(intent);
    }

//    public void medicineListBtnClicked(View v) {
//        Navigation.findNavController(v).navigate(R.id.navigateToMedicineList);
//    }

    public void sosBtnClicked(View v) {
//        mApiInterface.sendDangerSignal()
        binding.progressBar2.setVisibility(View.VISIBLE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            OnGPS();
        }else {
            getLocation();
        }
        mApiInterface.sendDangerSignal("Bearer " + jwtToken, myLocation).enqueue(new Callback<DangerResponse>() {
            @Override
            public void onResponse(Call<DangerResponse> call, Response<DangerResponse> response) {
                Toast.makeText(getActivity(), currentUser.getName(), Toast.LENGTH_SHORT).show();
                String dangerResponse = GsonUtils.getGson().toJson(response.body());
                Bundle bundle = new Bundle();
                bundle.putString("DANGER_SIGNAL_RESPONSE", dangerResponse);
                Navigation.findNavController(v).navigate(R.id.waitingResponseSosFragment, bundle);
                binding.progressBar2.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<DangerResponse> call, Throwable t) {
                binding.progressBar2.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Send danger signal failed.", Toast.LENGTH_SHORT).show();
                Log.e("SOS Failed", "Send danger signal failed." );
            }
        });


    }

    public void medicineListBtnClicked(View v) {
        Navigation.findNavController(v).navigate(R.id.navigateToMedicineList);
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                myLocation.setLatitude(locationGPS.getLatitude());
                myLocation.setLongitude(locationGPS.getLongitude());
            } else {
                Toast.makeText(getActivity(), "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}