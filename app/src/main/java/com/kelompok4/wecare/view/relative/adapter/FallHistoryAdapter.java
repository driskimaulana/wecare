package com.kelompok4.wecare.view.relative.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.FallHistoryItemBinding;
import com.kelompok4.wecare.model.FallHistoryModel;

import java.util.List;

public class FallHistoryAdapter extends RecyclerView.Adapter<FallHistoryAdapter.FallHistoryHolder> {

    private final List<FallHistoryModel> items;

    public FallHistoryAdapter(List<FallHistoryModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FallHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FallHistoryHolder(FallHistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FallHistoryHolder holder, int position) {
        holder.binding.tvLocation.setText("Location: " + items.get(position).getLocation());
        holder.binding.tvTanggal.setText(items.get(position).getTanggal());
        holder.binding.tvStatus.setText("Status: " + items.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class FallHistoryHolder extends RecyclerView.ViewHolder {

        FallHistoryItemBinding binding;

        public FallHistoryHolder(FallHistoryItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
