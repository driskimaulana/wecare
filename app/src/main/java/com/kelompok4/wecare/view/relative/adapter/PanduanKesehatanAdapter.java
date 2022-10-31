package com.kelompok4.wecare.view.relative.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.model.PanduanKesehatanModel;

import java.util.ArrayList;

public class PanduanKesehatanAdapter extends RecyclerView.Adapter<PanduanKesehatanAdapter.MyViewHolder>{

    Context context;
    ArrayList<PanduanKesehatanModel> panduanKesehatanModels;

    public PanduanKesehatanAdapter(Context context, ArrayList<PanduanKesehatanModel> panduanKesehatanModels){
        this.context = context;
        this.panduanKesehatanModels = panduanKesehatanModels;
    }
    @NonNull
    @Override
    public PanduanKesehatanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.panduan_kesehatan_item, parent, false);
        return new PanduanKesehatanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanduanKesehatanAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(panduanKesehatanModels.get(position).getPanduanImage());
        holder.tvNameTitle.setText(panduanKesehatanModels.get(position).getPanduanTitle());
        holder.tvDesc.setText(panduanKesehatanModels.get(position).getPanduanDesc());
    }

    @Override
    public int getItemCount() {
        return panduanKesehatanModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvNameTitle, tvDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewPanduanKesehatan);
            tvNameTitle = itemView.findViewById(R.id.itemTitlePanduanKesehatan);
            tvDesc = itemView.findViewById(R.id.textViewDescPanduan);

        }
    }
}