package com.kelompok4.wecare.view.relative.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.HealthEducationItemBinding;
import com.kelompok4.wecare.model.HealthEducationModel;
import com.kelompok4.wecare.model.healthEducation.HealthEducation;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


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

        final int radius = 5;
        final int margin = 5;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        Picasso.get()
                .load(items.get(position).getImgUrl())
                .resize(120, 120)
                .transform(transformation)
                .into(holder.binding.imgHealthAdu);


        holder.binding.healthEducationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String healthEducationSelected = GsonUtils.getGson().toJson(items.get(holder.getAdapterPosition()));
                bundle.putString("HEALTH_EDU_SELECTED", healthEducationSelected);
                Navigation.findNavController(view).navigate(R.id.navigateToDetail, bundle);
            }
        });
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