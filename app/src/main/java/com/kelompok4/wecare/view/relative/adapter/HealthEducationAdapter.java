package com.kelompok4.wecare.view.relative.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.FallHistoryItemBinding;
import com.kelompok4.wecare.databinding.HealthEducationItemBinding;
import com.kelompok4.wecare.model.HealthEducation;

import java.util.List;


public class HealthEducationAdapter extends RecyclerView.Adapter<HealthEducationAdapter.HealthEducationHolder> {

    private final List<HealthEducation> items;

    public HealthEducationAdapter(List<HealthEducation> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HealthEducationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HealthEducationHolder(HealthEducationItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HealthEducationHolder holder, int position) {
        holder.binding.tvTitle.setText(items.get(position).getTitle());
        holder.binding.tvDesc.setText(items.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HealthEducationHolder extends RecyclerView.ViewHolder {

        HealthEducationItemBinding binding;

        public HealthEducationHolder(HealthEducationItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}