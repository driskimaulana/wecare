package com.kelompok4.wecare.view.elder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.databinding.FragmentWaitingResponseSosBinding;
import com.kelompok4.wecare.view.elder.adapter.ElderWaitingResponseSosRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class WaitingResponseSosFragment extends Fragment {

    private FragmentWaitingResponseSosBinding binding;

    public WaitingResponseSosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWaitingResponseSosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager relativeListLayoutManager = new LinearLayoutManager(getContext());
        binding.rvRelativeList.setLayoutManager(relativeListLayoutManager);

        List<String> items = new ArrayList<String>();
        items.add("Relative 1");

        ElderWaitingResponseSosRecyclerViewAdapter adapter = new ElderWaitingResponseSosRecyclerViewAdapter(items);
        binding.rvRelativeList.setAdapter(adapter);

        binding.btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}