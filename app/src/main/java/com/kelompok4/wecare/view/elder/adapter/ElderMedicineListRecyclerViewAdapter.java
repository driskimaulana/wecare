package com.kelompok4.wecare.view.elder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.MedicineItemBinding;
import com.kelompok4.wecare.model.MedicineModel;
import com.kelompok4.wecare.model.medicineSchedule.MedicineSchedule;
import com.kelompok4.wecare.model.medicineSchedule.MedicineScheduleList;

import java.util.List;

public class ElderMedicineListRecyclerViewAdapter extends RecyclerView.Adapter<ElderMedicineListRecyclerViewAdapter.ElderMedicineHolder> {
    private final List<MedicineSchedule> items;

    public ElderMedicineListRecyclerViewAdapter(List<MedicineSchedule> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ElderMedicineListRecyclerViewAdapter.ElderMedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ElderMedicineHolder(MedicineItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ElderMedicineListRecyclerViewAdapter.ElderMedicineHolder holder, int position) {
        holder.binding.tvEatTime.setText(items.get(position).getEatTime());
        holder.binding.tvMedName.setText(items.get(position).getMedicineName());
        holder.binding.tvNote.setText(items.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class ElderMedicineHolder extends RecyclerView.ViewHolder {
        MedicineItemBinding binding;

        public ElderMedicineHolder(MedicineItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

}
