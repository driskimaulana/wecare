package com.kelompok4.wecare.view.elder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import com.kelompok4.wecare.databinding.FragmentElderMedicineListBinding;
import com.kelompok4.wecare.model.MedicineModel;
import com.kelompok4.wecare.model.medicineSchedule.MedicineSchedule;
import com.kelompok4.wecare.model.medicineSchedule.MedicineScheduleList;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.elder.adapter.ElderMedicineListRecyclerViewAdapter;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElderMedicineList extends Fragment {

    private FragmentElderMedicineListBinding binding;
    private ApiInterface apiInterface;
    private ProgressDialog pd;

    private User currentUser;
    private String token;

    public ElderMedicineList() {
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
        binding = FragmentElderMedicineListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pd = ProgressDialog.show(getContext(), "Loading...", "Mendapatkan data");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Drawable backArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24);
        backArrow.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
        binding.elderMedicineListToolbar.setNavigationIcon(backArrow);
        binding.elderMedicineListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        RecyclerView.LayoutManager medicineListLayoutManager = new LinearLayoutManager(getContext());
        binding.rvMedicineList.setLayoutManager(medicineListLayoutManager);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        token = sharedPreferences.getString(getString(R.string.const_token_key), "");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getActivity().getIntent().getExtras();

        //        get current logged in user
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        Call<MedicineScheduleList> call = apiInterface.getTodayMedicineSchedules("Bearer " + token);

        call.enqueue(new Callback<MedicineScheduleList>() {
            @Override
            public void onResponse(Call<MedicineScheduleList> call, Response<MedicineScheduleList> response) {
                if (response.body().getStatus() != 200) {
                    Log.d("debugdriski", "onResponse: Gagal mendapatkan data.");
                    Toast.makeText(getContext(), "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }

                if (response.body().getMedicineScheduleList().isEmpty()) {
                    Toast.makeText(getContext(), "Data tidak ditemukan.", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }

                ElderMedicineListRecyclerViewAdapter adapter = new ElderMedicineListRecyclerViewAdapter(response.body().getMedicineScheduleList());
                binding.rvMedicineList.setAdapter(adapter);
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<MedicineScheduleList> call, Throwable t) {
                pd.dismiss();
                Log.d("debugdriski", "onResponse: Gagal mendapatkan data.");
                Toast.makeText(getContext(), "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
            }
        });

//        List<MedicineModel> items = new ArrayList<MedicineModel>();
//        items.add(new MedicineModel(R.mipmap.medicine_placeholder, "16:00", "Piroxicam", "Obat untuk asam urat"));
//        items.add(new MedicineModel(R.mipmap.medicine_placeholder2, "20:00", "Candesartan Cilexetil", "Obat untuk darah tinggi"));
//        items.add(new MedicineModel(R.mipmap.medicine_placeholder, "7:30", "Piroxicam", "Obat untuk asam urat"));
//        items.add(new MedicineModel(R.mipmap.medicine_placeholder2, "12:00", "Candesartan Cilexetil", "Obat untuk darah tinggi"));
//        items.add(new MedicineModel(R.mipmap.medicine_placeholder, "16:00", "Piroxicam", "Obat untuk asam urat"));


    }
}