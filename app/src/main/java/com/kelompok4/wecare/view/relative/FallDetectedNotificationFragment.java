package com.kelompok4.wecare.view.relative;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentFallDetectedNotificationBinding;


public class FallDetectedNotificationFragment extends Fragment {

    private FragmentFallDetectedNotificationBinding binding;

    public FallDetectedNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFallDetectedNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}