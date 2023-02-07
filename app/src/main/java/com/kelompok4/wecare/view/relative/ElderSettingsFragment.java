package com.kelompok4.wecare.view.relative;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderNameBinding;
import com.kelompok4.wecare.databinding.FragmentElderSettingsBinding;

public class ElderSettingsFragment extends Fragment {

    private FragmentElderSettingsBinding binding;

    public ElderSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentElderSettingsBinding.inflate(inflater, container, false);

        return binding.getRoot();

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_elder_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        binding.imageViewGoSetNama.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.navigateToElderNameSettings);
//            }
//        });
//        binding.imageViewGoSetTanggalLahir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.navigateToElderBirthDateSettings);
//            }
//        });
//        binding.imageViewGoSetGolonganDarah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.navigateToElderBloodTypeSettings);
//            }
//        });
//        binding.textViewGoCheckMedicalCheckup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.navigateToAddCheckupHistory);
//            }
//        });
    }
}