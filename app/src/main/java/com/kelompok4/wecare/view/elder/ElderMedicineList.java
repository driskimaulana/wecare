package com.kelompok4.wecare.view.elder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.MedicineModel;
import com.kelompok4.wecare.view.elder.adapter.ElderMedicineListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ElderMedicineList extends Fragment {

    public ElderMedicineList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<MedicineModel> items = new ArrayList<MedicineModel>();
        items.add(new MedicineModel("16:00", "Piroxicam", "Obat untuk asam urat"));
        items.add(new MedicineModel("20:00", "Candesartan Cilexetil", "Obat untuk darah tinggi"));
        items.add(new MedicineModel("7:30", "Piroxicam", "Obat untuk asam urat"));
        items.add(new MedicineModel("12:00", "Candesartan Cilexetil", "Obat untuk darah tinggi"));
        items.add(new MedicineModel("16:00", "Piroxicam", "Obat untuk asam urat"));

        ElderMedicineListRecyclerViewAdapter adapter = new ElderMedicineListRecyclerViewAdapter(items);
//        RecyclerView rv =
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elder_medicine_list, container, false);
    }
}