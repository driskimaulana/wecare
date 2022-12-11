package com.kelompok4.wecare.view.elder;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentWaitingResponseSosBinding;
import com.kelompok4.wecare.model.notification.DangerResponse;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.elder.adapter.ElderWaitingResponseSosRecyclerViewAdapter;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

public class WaitingResponseSosFragment extends Fragment {

    private FragmentWaitingResponseSosBinding binding;
    private DangerResponse response;

    private MediaPlayer alarm;

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

        Bundle receiveBundle = getArguments();
        response = GsonUtils.getGson().fromJson(receiveBundle.getString("DANGER_SIGNAL_RESPONSE"), DangerResponse.class);

        List<User> relativeList = response.getSendTo();

        ElderWaitingResponseSosRecyclerViewAdapter adapter = new ElderWaitingResponseSosRecyclerViewAdapter(relativeList);
        binding.rvRelativeList.setAdapter(adapter);

        alarm = MediaPlayer.create(getActivity(), R.raw.warning_alarm);
        alarm.setLooping(true);
        alarm.start();

        binding.btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarm.stop();
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}