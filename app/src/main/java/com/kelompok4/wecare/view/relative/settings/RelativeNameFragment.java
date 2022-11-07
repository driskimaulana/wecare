package com.kelompok4.wecare.view.relative.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentDashboardBinding;
import com.kelompok4.wecare.databinding.FragmentRelativeNameBinding;


public class RelativeNameFragment extends Fragment {

    private FragmentRelativeNameBinding binding;

    public RelativeNameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRelativeNameBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relative_name, container, false);
    }
}