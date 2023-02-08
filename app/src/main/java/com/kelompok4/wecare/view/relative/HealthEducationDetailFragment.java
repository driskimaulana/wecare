package com.kelompok4.wecare.view.relative;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentHealthEducationBinding;
import com.kelompok4.wecare.databinding.FragmentHealthEducationDetailBinding;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HealthEducationDetailFragment extends Fragment {

    private FragmentHealthEducationDetailBinding binding;
    private HealthEducation healthEducationSelected;

    public HealthEducationDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        healthEducationSelected = GsonUtils.getGson().fromJson(bundle.getString("HEALTH_EDU_SELECTED"), HealthEducation.class);

        binding.tvDetailTitle.setText(healthEducationSelected.getTitle());
        binding.tvDetailContent.setText(healthEducationSelected.getContent());
        binding.tvDetailWriter.setText(healthEducationSelected.getAuthor());

        Picasso.get()
                .load(healthEducationSelected.getImgUrl())
                .resize(200, 100)
                .into(binding.imgDetail);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHealthEducationDetailBinding.inflate(inflater);
        return binding.getRoot();
    }
}