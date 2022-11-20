package com.kelompok4.wecare.view.elder;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentElderMedicineListBinding;
import com.kelompok4.wecare.model.MedicineModel;
import com.kelompok4.wecare.view.elder.adapter.ElderMedicineListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ElderMedicineList extends Fragment {

    private FragmentElderMedicineListBinding binding;

    public ElderMedicineList() {
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
        binding = FragmentElderMedicineListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Drawable backArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        backArrow.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
        binding.elderMedicineListToolbar.setNavigationIcon(backArrow);
        binding.elderMedicineListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        RecyclerView.LayoutManager medicineListLayoutManager = new LinearLayoutManager(getContext());
        binding.rvMedicineList.setLayoutManager(medicineListLayoutManager);

        List<MedicineModel> items = new ArrayList<MedicineModel>();
        items.add(new MedicineModel(R.mipmap.medicine_placeholder, "16:00", "Piroxicam", "Obat untuk asam urat"));
        items.add(new MedicineModel(R.mipmap.medicine_placeholder2, "20:00", "Candesartan Cilexetil", "Obat untuk darah tinggi"));
        items.add(new MedicineModel(R.mipmap.medicine_placeholder, "7:30", "Piroxicam", "Obat untuk asam urat"));
        items.add(new MedicineModel(R.mipmap.medicine_placeholder2, "12:00", "Candesartan Cilexetil", "Obat untuk darah tinggi"));
        items.add(new MedicineModel(R.mipmap.medicine_placeholder, "16:00", "Piroxicam", "Obat untuk asam urat"));

        ElderMedicineListRecyclerViewAdapter adapter = new ElderMedicineListRecyclerViewAdapter(items);
        binding.rvMedicineList.setAdapter(adapter);
    }
}