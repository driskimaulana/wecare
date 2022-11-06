package com.kelompok4.wecare.view.elder.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderNameBinding;
import com.kelompok4.wecare.databinding.FragmentRelativeNameBinding;

public class ElderNameFragment extends Fragment {

    private FragmentElderNameBinding binding;

    public ElderNameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentElderNameBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elder_name, container, false);
    }
}