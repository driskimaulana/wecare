package com.kelompok4.wecare.view.relative.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.ElderListItemBinding;
import com.kelompok4.wecare.databinding.FallHistoryItemBinding;
import com.kelompok4.wecare.model.user.ListElderConnected;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.utils.SelectListener;

import java.util.List;

import retrofit2.Callback;

public class ElderListAdapter extends RecyclerView.Adapter<ElderListAdapter.ElderListHolder> {

    private final List<User> items;
    private SelectListener listener;

    public ElderListAdapter(List<User> items, SelectListener selectListener) {
        this.items = items;
        this.listener = selectListener;
    }

    @NonNull
    @Override
    public ElderListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ElderListHolder(ElderListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ElderListHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvElderName.setText(items.get(position).getName());

        holder.binding.mainItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(items.get(position), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ElderListHolder extends RecyclerView.ViewHolder {

        ElderListItemBinding binding;

        public ElderListHolder(ElderListItemBinding b) {
            super(b.getRoot());
            binding = b;


        }
    }


}
