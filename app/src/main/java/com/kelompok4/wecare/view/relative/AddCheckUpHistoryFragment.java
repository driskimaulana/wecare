package com.kelompok4.wecare.view.relative;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentAddCheckUpHistoryBinding;

public class AddCheckUpHistoryFragment extends Fragment {

    private FragmentAddCheckUpHistoryBinding binding;

    public AddCheckUpHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCheckUpHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}