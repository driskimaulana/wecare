package com.kelompok4.wecare.view.relative.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.CheckupHistoryItemBinding;
import com.kelompok4.wecare.model.CheckUpHistoryModel;

import java.util.List;

public class CheckUpHistoryAdapter extends RecyclerView.Adapter<CheckUpHistoryAdapter.CheckUpHistoryHolder> {

    private final List<CheckUpHistoryModel> items;

    public CheckUpHistoryAdapter(List<CheckUpHistoryModel> histories) {
        items = histories;
    }


    @NonNull
    @Override
    public CheckUpHistoryAdapter.CheckUpHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckUpHistoryHolder(CheckupHistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckUpHistoryAdapter.CheckUpHistoryHolder holder, int position) {
        holder.binding.tvTanggal.setText(items.get(position).getTanggal());
        holder.binding.tvAsamUrat.setText("Asam Urat: " + items.get(position).getAsamUrat());
        holder.binding.tvGulaDarah.setText("Gula Darah: " + items.get(position).getGulaDarah());
        holder.binding.tvHemoglobin.setText("Hemoglobin: " + items.get(position).getHemoglobin());
        holder.binding.tvKolesterol.setText("Kolesterol: " + items.get(position).getKolesterol());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected class CheckUpHistoryHolder extends RecyclerView.ViewHolder {

        CheckupHistoryItemBinding binding;

        public CheckUpHistoryHolder(CheckupHistoryItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
