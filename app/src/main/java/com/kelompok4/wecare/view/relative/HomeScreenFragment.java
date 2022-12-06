package com.kelompok4.wecare.view.relative;

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

import com.google.zxing.integration.android.IntentIntegrator;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentHomeScreenBinding;
import com.kelompok4.wecare.model.CheckUpHistoryModel;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.relative.adapter.CheckUpHistoryAdapter;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenFragment extends Fragment {

    private FragmentHomeScreenBinding binding;
    private User currentUser;
    private User currentElder;

    private ApiInterface mApiInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.const_token_key), "");

        Bundle bundle = getActivity().getIntent().getExtras();
//        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);
//
//        if (currentUser != null) {
//            binding.elderName.setText(currentUser.getName());
//        }

//        Fragment fragment = new MapFragment();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

        showMaps();

//        get current logged in user
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        //        get elder data that connected with current logged in relative
        Call<AuthResponse> call = mApiInterface.getUserDetails(currentUser.getElderConnected().get(0), "Bearer " + token);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                assert response.body() != null;
                currentElder = response.body().getResult();
                binding.elderName.setText(response.body().getResult().getName());
//                showMaps();
//                showMaps(currentElder);
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

                Log.d("HomeScreenFragment", "onResponse: " + response.body().getResult().toString());
//                Toast.makeText(getActivity(), "SUKSES ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("FAILEDDDDDD", "onFailure: " + t.toString());
//                Toast.makeText(getActivity(), "FAILED", Toast.LENGTH_SHORT).show();
            }
        });
//        Toast.makeText(getActivity(), currentElder.toString(), Toast.LENGTH_SHORT).show();
//        binding.elderAge.setText(currentElder.getEmail());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvCheckupHistory.setLayoutManager(layoutManager);

        List<CheckUpHistoryModel> items = new ArrayList<CheckUpHistoryModel>();
        items.add(new CheckUpHistoryModel(5.5, 7.2, 8.2, 4.2, "20/09/2021"));
        items.add(new CheckUpHistoryModel(5.2, 2.2, 2.1, 7.3, "21/10/2021"));
        items.add(new CheckUpHistoryModel(5.3, 4.3, 8.3, 3.2, "01/11/2021"));
        items.add(new CheckUpHistoryModel(5.6, 3.1, 4.2, 5.6, "29/12/2021"));
        items.add(new CheckUpHistoryModel(5.1, 6.2, 8.6, 6.2, "11/01/2022"));

        CheckUpHistoryAdapter adapter = new CheckUpHistoryAdapter(items);

        binding.rvCheckupHistory.setAdapter(adapter);

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

        binding.btnAddElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToAddElder);
            }
        });

        //Add Elder QR
        binding.btnAddElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
//        Bundle bundle = new Bundle();
//        bundle.putString("MAPS_USER", GsonUtils.getGson().toJson(user));
//        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }
}