package com.kelompok4.wecare.view.elder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.MedicineItemBinding;
import com.kelompok4.wecare.model.MedicineModel;

import java.util.List;

public class ElderMedicineListRecyclerViewAdapter extends RecyclerView.Adapter<ElderMedicineListRecyclerViewAdapter.ElderMedicineHolder> {
    private final List<MedicineModel> items;

    public ElderMedicineListRecyclerViewAdapter(List<MedicineModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ElderMedicineListRecyclerViewAdapter.ElderMedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ElderMedicineHolder(MedicineItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ElderMedicineListRecyclerViewAdapter.ElderMedicineHolder holder, int position) {
        holder.binding.imgMedicine.setImageResource(items.get(position).getImageId());
        holder.binding.tvMedicineTime.setText(items.get(position).getTime());
        holder.binding.tvMedicineName.setText(items.get(position).getName());
        holder.binding.tvMedicineDesc.setText(items.get(position).getDesc());
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

    MedicineModel getItem(int id) {
        return items.get(id);
    }
}
