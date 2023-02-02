package com.kelompok4.wecare.view.relative;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentAddCheckUpHistoryBinding;
import com.kelompok4.wecare.model.BasicResponse;
import com.kelompok4.wecare.model.checkupHistory.CheckupHistoryModel;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCheckUpHistoryFragment extends Fragment {

    private FragmentAddCheckUpHistoryBinding binding;
    final private Calendar calendar = Calendar.getInstance();
    private User currentUser;
    private ApiInterface apiInterface;
    private String token;
    private ProgressDialog pd;

    public AddCheckUpHistoryFragment() {
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
        binding = FragmentAddCheckUpHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        token = sharedPreferences.getString(getString(R.string.const_token_key), "");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getActivity().getIntent().getExtras();

        //        get current logged in user
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        Log.d("debugdriski", "onViewCreated: " + currentUser.getElderConnected().get(0));

        DatePickerDialog.OnDateSetListener onDateSetListener = (dateView, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            updateLabel(binding.edtTanggal);
        };

        binding.edtTanggal.setOnClickListener(dateView -> {
            new DatePickerDialog(
                    getContext(),
                    R.style.DialogTheme,
                    onDateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveNewCheckupHistory();
            }
        });

    }

    private void handleSaveNewCheckupHistory() {
        pd = ProgressDialog.show(getActivity(), "Loading", "Menyimpan riwayat checkup...",  false);

        CheckupHistoryModel checkupHistoryRequest = new CheckupHistoryModel();

        checkupHistoryRequest.setDate(binding.edtTanggal.getText().toString());
        checkupHistoryRequest.setPlace(binding.edtTempatCheckup.getText().toString());
        checkupHistoryRequest.setDoctorName(binding.edtDoctorName.getText().toString());
        checkupHistoryRequest.setCholesterol(Double.parseDouble(binding.edtTekananDarah.getText().toString()));
        checkupHistoryRequest.setBloodSugar(Double.parseDouble(binding.edtGulaDarah.getText().toString()));
        checkupHistoryRequest.setHemoglobin(Double.parseDouble(binding.edtHemoglobin.getText().toString()));
        checkupHistoryRequest.setGout(Double.parseDouble(binding.edtGout.getText().toString()));
        checkupHistoryRequest.setElderId(currentUser.getElderConnected().get(0));
        Log.d("debugdriski", "handleSaveNewCheckupHistory: " + checkupHistoryRequest.getDate());

        Call<BasicResponse> call = apiInterface.addNewCheckupHistory("Bearer " + token, checkupHistoryRequest);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().getStatus() != 200) {
                    Toast.makeText(getContext(), "Gagal menambahkan riwayat checkup.", Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();

                Navigation.findNavController(getView()).navigateUp();
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t){
                Toast.makeText(getContext(), "Gagal menambahkan riwayat checkup.", Toast.LENGTH_SHORT).show();
                Log.d("debugdriski", "onFailure: " + t.toString());
                pd.dismiss();
            }
        });
    }

    public void updateLabel(EditText date) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }
}