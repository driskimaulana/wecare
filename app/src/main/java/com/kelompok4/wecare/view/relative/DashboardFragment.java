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
import com.kelompok4.wecare.databinding.FragmentDashboardBinding;
import com.kelompok4.wecare.model.CheckUpHistoryModel;
import com.kelompok4.wecare.model.FallHistoryModel;
import com.kelompok4.wecare.view.relative.adapter.CheckUpHistoryAdapter;
import com.kelompok4.wecare.view.relative.adapter.FallHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager checkupLayoutManager = new LinearLayoutManager(getContext());
        binding.rvCheckupHistory.setLayoutManager(checkupLayoutManager);

        RecyclerView.LayoutManager fallLayoutManager = new LinearLayoutManager(getContext());
        binding.rvFallHistory.setLayoutManager(fallLayoutManager);

        List<CheckUpHistoryModel> items = new ArrayList<CheckUpHistoryModel>();
        items.add(new CheckUpHistoryModel(5.5, 7.2, 8.2, 4.2, "20/09/2021"));
        items.add(new CheckUpHistoryModel(5.2, 2.2, 2.1, 7.3, "21/10/2021"));
        items.add(new CheckUpHistoryModel(5.3, 4.3, 8.3, 3.2, "01/11/2021"));
        items.add(new CheckUpHistoryModel(5.6, 3.1, 4.2, 5.6, "29/12/2021"));
        items.add(new CheckUpHistoryModel(5.1, 6.2, 8.6, 6.2, "11/01/2022"));

        List<FallHistoryModel> fallItems = new ArrayList<FallHistoryModel>();
        fallItems.add(new FallHistoryModel("12/10/2021", "-7.317044, 107.864453", "Diselamatkan"));
        fallItems.add(new FallHistoryModel("20/10/2022", "-7.317044, 107.864453", "Diselamatkan"));


        CheckUpHistoryAdapter checkupAdapter = new CheckUpHistoryAdapter(items);
        binding.rvCheckupHistory.setAdapter(checkupAdapter);

        FallHistoryAdapter fallHistoryAdapter = new FallHistoryAdapter(fallItems);
        binding.rvFallHistory.setAdapter(fallHistoryAdapter);

        binding.btnElderSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateDashboardToElderSettings);
            }
        });

        binding.btnAddElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateDashboardToAddElder);
            }
        });

        binding.btnAddCheckupHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToAddCheckupHistory);
            }
        });
    }
}