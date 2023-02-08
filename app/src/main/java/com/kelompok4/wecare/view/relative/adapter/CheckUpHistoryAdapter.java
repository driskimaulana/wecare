package com.kelompok4.wecare.view.relative.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.CheckupHistoryItemBinding;
import com.kelompok4.wecare.model.CheckUpHistoryModel;
import com.kelompok4.wecare.model.checkupHistory.CheckupHistoryResponse;
import com.kelompok4.wecare.model.checkupHistory.ListCheckupHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CheckUpHistoryAdapter extends RecyclerView.Adapter<CheckUpHistoryAdapter.CheckUpHistoryHolder> {

    private final List<CheckupHistoryResponse> items;

    public CheckUpHistoryAdapter(List<CheckupHistoryResponse> histories) {
        items = histories;
    }

    @NonNull
    @Override
    public CheckUpHistoryAdapter.CheckUpHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckUpHistoryHolder(CheckupHistoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckUpHistoryAdapter.CheckUpHistoryHolder holder, int position) {
        holder.binding.tvTanggal.setText(items.get(position).getDate().substring(0, 10));

        holder.binding.tvAsamUrat.setText("Asam Urat: " + items.get(position).getGout());
        holder.binding.tvGulaDarah.setText("Gula Darah: " + items.get(position).getBloodSugar());
        holder.binding.tvHemoglobin.setText("Hemoglobin: " + items.get(position).getHemoglobin());
        holder.binding.tvKolesterol.setText("Kolesterol: " + items.get(position).getCholesterol());
        holder.binding.tvHospital.setText(items.get(position).getPlace());
        holder.binding.tvDoctor.setText(items.get(position).getDoctorName());
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
