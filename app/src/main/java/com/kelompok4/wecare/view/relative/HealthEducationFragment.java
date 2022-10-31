package com.kelompok4.wecare.view.relative;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentHealthEducationBinding;
import com.kelompok4.wecare.model.HealthEducation;
import com.kelompok4.wecare.view.relative.adapter.HealthEducationAdapter;

import java.util.ArrayList;
import java.util.List;

public class HealthEducationFragment extends Fragment {

    private FragmentHealthEducationBinding binding;

    public HealthEducationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.healtheduRecvie.setLayoutManager(layoutManager);

        List<HealthEducation> items = new ArrayList<>();
        items.add(new HealthEducation("Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident"));
        items.add(new HealthEducation("Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident"));
        items.add(new HealthEducation("Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident"));
        items.add(new HealthEducation("Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident"));
        items.add(new HealthEducation("Pertolongan Pertama ketika X", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident"));

        HealthEducationAdapter adapter = new HealthEducationAdapter(items);
        binding.healtheduRecvie.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHealthEducationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}