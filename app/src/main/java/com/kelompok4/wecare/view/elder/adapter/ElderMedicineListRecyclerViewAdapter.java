package com.kelompok4.wecare.view.elder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.MedicineModel;

import java.util.List;

public class ElderMedicineListRecyclerViewAdapter extends RecyclerView.Adapter<ElderMedicineListRecyclerViewAdapter.ViewHolder> {
    private final List<MedicineModel> items;
    private LayoutInflater inflater;

    public ElderMedicineListRecyclerViewAdapter(Context context, List<MedicineModel> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    public ElderMedicineListRecyclerViewAdapter(List<MedicineModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String time = data.get(position);
//        String name = data.get(position);
//        String desc = data.get(position);
//
//        holder.tv_time.setText(time);
//        holder.tv_name.setText(name);
//        holder.tv_desc.setText(desc);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_name;
        TextView tv_desc;

        ViewHolder(View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_medicine_time);
            tv_name = itemView.findViewById(R.id.tv_medicine_name);
            tv_desc = itemView.findViewById(R.id.tv_medicine_desc);
        }
    }

    MedicineModel getItem(int id) {
        return items.get(id);
    }
}
