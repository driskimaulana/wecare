package com.kelompok4.wecare.view.relative;

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

import com.kelompok4.wecare.R;


import com.kelompok4.wecare.databinding.FragmentHomeScreenBinding;
import com.kelompok4.wecare.model.CheckUpHistoryModel;
import com.kelompok4.wecare.view.relative.adapter.CheckUpHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenFragment extends Fragment {

    private FragmentHomeScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvCheckupHistory.setLayoutManager(layoutManager);

        List<CheckUpHistoryModel> items = new ArrayList<CheckUpHistoryModel>();
        items.add(new CheckUpHistoryModel(5.5, 7.2, 8.2, 4.2, "20/09/2021"));
        items.add(new CheckUpHistoryModel(5.2, 2.2, 2.1, 7.3, "21/10/2021"));
        items.add(new CheckUpHistoryModel(5.3, 4.3, 8.3, 3.2, "01/11/2021"));
        items.add(new CheckUpHistoryModel(5.6, 3.1, 4.2, 5.6, "29/12/2021"));
        items.add(new CheckUpHistoryModel(5.1, 6.2, 8.6, 6.2, "11/01/2022"));

        CheckUpHistoryAdapter adapter = new CheckUpHistoryAdapter(items);

        binding.rvCheckupHistory.setAdapter(adapter);

        binding.btnSetMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToSetMedicine);
            }
        });

        binding.btnElderSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToElderSettings);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}