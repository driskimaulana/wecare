package com.kelompok4.wecare.view.elder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.RvRelativeListItemsBinding;
import com.kelompok4.wecare.model.user.User;

import java.util.List;

public class ElderWaitingResponseSosRecyclerViewAdapter extends RecyclerView.Adapter<ElderWaitingResponseSosRecyclerViewAdapter.RelativeListHolder> {
    private final List<User> items;

    public ElderWaitingResponseSosRecyclerViewAdapter(List<User> items) { this.items = items; }

    @NonNull
    @Override
    public ElderWaitingResponseSosRecyclerViewAdapter.RelativeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RelativeListHolder(RvRelativeListItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ElderWaitingResponseSosRecyclerViewAdapter.RelativeListHolder holder, int position) {
        holder.binding.tvRelativeName.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() { return items.size(); }

    protected class RelativeListHolder extends RecyclerView.ViewHolder {
        RvRelativeListItemsBinding binding;

        public RelativeListHolder(RvRelativeListItemsBinding b) {
            super(b.getRoot());
            binding = b;
        }

        // User getItem(int id) { return items.get(id); }
    }
}
