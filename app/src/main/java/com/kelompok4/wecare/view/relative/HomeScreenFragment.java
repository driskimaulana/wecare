package com.kelompok4.wecare.view.relative;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentHomeScreenBinding;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenFragment extends Fragment {

    private FragmentHomeScreenBinding binding;
    private User currentUser;
    private User currentElder;

    private int elderKey;

    private ApiInterface mApiInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setVisibility(View.VISIBLE);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.const_token_key), "");
        String elderName = sharedPreferences.getString("ELDER_NAME", "");

        if (elderName.length() != 0 ) {
            binding.elderName.setText(elderName);
        }

        elderKey = sharedPreferences.getInt(getString(R.string.ELDER_KEY), 1);

        Log.d("debugdriski", "onViewCreated: " + elderKey);
        Bundle bundle = getActivity().getIntent().getExtras();

        showMaps();

//        get current logged in user
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        if (currentUser.getElderConnected().size() == 0) {
            binding.layoutThereIsElder.setVisibility(View.GONE);
            binding.layoutNoElder.setVisibility(View.VISIBLE);

            binding.btnAddNewElder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    IntentIntegrator intentIntegrator = new IntentIntegrator(
                            getActivity()
                    );
                    intentIntegrator.setPrompt("Press 'VOLUME UP' to activate flash\nPress 'VOLUME DOWN' to deactivate flash.");
                    //Set beep
                    intentIntegrator.setBeepEnabled(true);
                    //lock orientation
                    intentIntegrator.setOrientationLocked(true);
                    //set capture activity
                    intentIntegrator.setCaptureActivity(Capture.class);
                    //Initiate scan
                    intentIntegrator.initiateScan();
                }
            });
        }else {
            binding.layoutNoElder.setVisibility(View.GONE);
            binding.layoutThereIsElder.setVisibility(View.VISIBLE);
            //        get elder data that connected with current logged in relative
            Call<AuthResponse> call = mApiInterface.getUserDetails(currentUser.getElderConnected().get(elderKey), "Bearer " + token);
            call.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    assert response.body() != null;
                    currentElder = response.body().getResult();
                    binding.elderName.setText(response.body().getResult().getName());
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("ELDER_NAME", response.body().getResult().getName());
//                save token to shared preferences
                    editor.putString("ELDER_LATITUDE", Double.toString(currentElder.getLocation().get(0)));
                    editor.putString("ELDER_LONGITUDE", Double.toString(currentElder.getLocation().get(1)));
                    editor.apply();
                    Log.d("HomeScreenFragment", "onResponse: " + response.body().getResult().toString());
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    Log.e("FAILEDDDDDD", "onFailure: " + t.toString());
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

            binding.btnSetMedicine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.navigateToSetMedicine);
                }
            });

            binding.btnElderSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.navigateToElderSettings);
                }
            });

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void showMaps(){
        Fragment fragment = new MapFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMaps, fragment).commit();
    }
}