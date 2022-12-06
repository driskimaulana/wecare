package com.kelompok4.wecare.view.relative;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.MainActivity;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

public class MapFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // get data from homescreen
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        String latitude = sharedPreferences.getString("ELDER_LATITUDE", "");
        String longitude = sharedPreferences.getString("ELDER_LONGITUDE", "");

        // initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_maps);

        // async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng elderCurrentLocation;

                if (latitude.length() > 0 && longitude.length() > 0) {
//                    Toast.makeText(getActivity(), "ini map custom", Toast.LENGTH_SHORT).show();
                    elderCurrentLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                }else {
                    elderCurrentLocation = new LatLng(-6.862491666666667, 107.58731833333334);
                }

//                if (user != null) {
//                    sydney = new LatLng(user.getLocation().get(0), user.getLocation().get(1));
//                }

                googleMap.addMarker(new MarkerOptions()
                        .position(elderCurrentLocation)
                        .title("Lokasi Elder"));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(elderCurrentLocation, 14));

            }
        });
        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}