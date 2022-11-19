package com.kelompok4.wecare.view.elder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderHomeBinding;

public class ElderHomeFragment extends Fragment {

    private FragmentElderHomeBinding binding;

    public ElderHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sosBtnClicked(view);
            }
        });

        binding.btnQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrBtnClicked(view);
            }
        });

        binding.tvMinumObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { medicineListBtnClicked(view); }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentElderHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void qrBtnClicked(View v) {
        Navigation.findNavController(v).navigate(R.id.navigateToElderQr);
//        Intent intent =  new Intent(this, ElderQrCodeFragment.class);
//        startActivity(intent);
    }

//    public void medicineListBtnClicked(View v) {
//        Navigation.findNavController(v).navigate(R.id.navigateToMedicineList);
//    }

    public void sosBtnClicked(View v) {
        Toast.makeText(getActivity(), "SOS Clicked", Toast.LENGTH_SHORT).show();
//        Intent intent =  new Intent(this, ElderQrCodeFragment.class);
//        startActivity(intent);
    }

    public void medicineListBtnClicked(View v) {
        Navigation.findNavController(v).navigate(R.id.navigateToMedicineList);
    }
}