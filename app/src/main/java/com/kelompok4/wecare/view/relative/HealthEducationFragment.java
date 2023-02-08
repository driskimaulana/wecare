package com.kelompok4.wecare.view.relative;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.FragmentHealthEducationBinding;
import com.kelompok4.wecare.model.healthEducation.GetHealthEducation;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;
import com.kelompok4.wecare.view.relative.adapter.HealthEducationAdapter;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthEducationFragment extends Fragment {

    private FragmentHealthEducationBinding binding;
    private HealthEducationAdapter adapter;

    ApiInterface mApiInterface;
    public static Fragment thisFragment;

    public HealthEducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.healtheduRecvie.setLayoutManager(layoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        thisFragment = this;
        binding.progressBar.setVisibility(View.VISIBLE);
        refresh();
    }

    private void refresh() {
        Call<GetHealthEducation> healthEducationCall = mApiInterface.getHealthEducation();
        healthEducationCall.enqueue(new Callback<GetHealthEducation>() {

            @Override
            public void onResponse(Call<GetHealthEducation> call, Response<GetHealthEducation> response) {
                List<HealthEducation> HealthEducationList = response.body().getListHealthEducations();
                Log.d("Retrofit GET", "jumlah healthEducation data: " + String.valueOf(HealthEducationList.size()));
                adapter = new HealthEducationAdapter(HealthEducationList);
                binding.healtheduRecvie.setAdapter(adapter);
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetHealthEducation> call, Throwable t) {
                Log.e("Retrofit GET", "onFailure: " + t.toString());
            }

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHealthEducationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}