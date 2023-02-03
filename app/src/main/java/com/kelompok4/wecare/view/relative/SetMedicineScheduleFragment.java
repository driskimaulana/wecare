package com.kelompok4.wecare.view.relative;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentRelativeSetMedicineScheduleBinding;
import com.kelompok4.wecare.model.BasicResponse;
import com.kelompok4.wecare.model.medicineSchedule.MedicineSchedule;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.components.TimePickerFragment;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetMedicineScheduleFragment extends Fragment {
    private FragmentRelativeSetMedicineScheduleBinding binding;
    final Calendar calendar = Calendar.getInstance();
    EditText dateStart, dateEnd;
    private int hour, minutes;
    private String timePicked;

    private ApiInterface apiInterface;

    private User currentUser;
    private String token;

    private ProgressDialog pd;

    public SetMedicineScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timePicked = "";

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setVisibility(View.GONE);

        dateStart = binding.getRoot().findViewById(R.id.medicine_date_picker_start);
        dateEnd = binding.getRoot().findViewById(R.id.medicine_date_picker_end);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        token = sharedPreferences.getString(getString(R.string.const_token_key), "");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getActivity().getIntent().getExtras();

        //        get current logged in user
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        DatePickerDialog.OnDateSetListener dateStartListener = (dateView, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel(dateStart);
        };

        DatePickerDialog.OnDateSetListener dateEndListener = (dateView, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel(dateEnd);
        };

        dateStart.setOnClickListener(dateStartView -> {
            new DatePickerDialog(getContext(), R.style.DialogTheme, dateStartListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        dateEnd.setOnClickListener(dateEndView -> {
            new DatePickerDialog(getContext(), R.style.DialogTheme, dateEndListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = ProgressDialog.show(getActivity(), "Loading ...", "Menyimpan data", false);
                handleSave();
                //Navigation.findNavController(view).navigate(R.id.navigateToHomeScreen);
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        binding.btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRelativeSetMedicineScheduleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void handleSave() {
        if (
                binding.inputNamaObat.length() == 0
                || binding.medicineDatePickerStart.getText().length() == 0
                || binding.medicineDatePickerEnd.getText().length() == 0
                || timePicked.length() == 0
        ) {
            Toast.makeText(getContext(), "Silakan lengkapi data!", Toast.LENGTH_SHORT).show();
            return;
        }

        MedicineSchedule newMedicineSchedule = new MedicineSchedule(
                binding.inputNamaObat.getText().toString(),
                timePicked,
                binding.medicineDatePickerStart.getText().toString(),
                binding.medicineDatePickerEnd.getText().toString(),
                binding.tvNote.getText().toString(),
                currentUser.getElderConnected().get(0)
        );

        Call<BasicResponse> call = apiInterface.addNewMedicineSchedules("Bearer " + token, newMedicineSchedule);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().getStatus() != 200) {
                    Log.d("debugdriski", "onResponse: " + response.body().getMessage());
                    pd.dismiss();
                    Toast.makeText(getContext(), "Gagal menyimpan jadwal baru.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getContext(), "Sukses menyimpan jadwal obat baru.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigateUp();
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.d("debugdriski", "onFailure: " + t.toString());
                pd.dismiss();
                Toast.makeText(getContext(), "Gagal menyimpan jadwal baru.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showTimePickerDialog(View v) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinutes) {
                hour = selectedHour;
                minutes = selectedMinutes;
                timePicked = String.format(Locale.getDefault(), "%02d:%02d", hour, minutes);

                binding.btnSetTime.setText(timePicked);
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, onTimeSetListener, hour, minutes, true);
        timePickerDialog.setTitle("Pilih waktu makan");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
//        DialogFragment newFragment = new TimePickerFragment();
//        newFragment.show(getParentFragmentManager(), "timePicker");
    }

    public void updateLabel(EditText date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }
}