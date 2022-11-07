package com.kelompok4.wecare.view.elder.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderBloodTypeBinding;
import com.kelompok4.wecare.databinding.FragmentElderSettingsBinding;

public class ElderBloodTypeFragment extends Fragment {

    private FragmentElderBloodTypeBinding binding;
    public RadioGroup radioBloodType;

    public ElderBloodTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioBloodTypeAPos:

                    break;
                case R.id.radioBloodTypeANeg:
                    // Write your code here
                    break;
                case R.id.radioBloodTypeBPos:

                    break;
                case R.id.radioBloodTypeBNeg:

                    break;
                case R.id.radioBloodTypeOPos:

                    break;
                case R.id.radioBloodTypeONeg:

                    break;
                case R.id.radioBloodTypeABPos:

                    break;
                case R.id.radioBloodTypeABNeg:

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentElderBloodTypeBinding.inflate(inflater, container, false);

        radioBloodType = binding.bloodTypeRhSystemRadioGroup;
        radioBloodType.setOnCheckedChangeListener(onCheckedChangeListener);
        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_elder_blood_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}