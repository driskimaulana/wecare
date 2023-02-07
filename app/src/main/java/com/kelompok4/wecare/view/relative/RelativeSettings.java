package com.kelompok4.wecare.view.relative;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentRelativeSettingsBinding;
import com.kelompok4.wecare.model.BasicResponse;
import com.kelompok4.wecare.model.user.ResetPasswordRequest;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RelativeSettings extends Fragment {

    private FragmentRelativeSettingsBinding binding;

    private ApiInterface apiInterface;
    private String token;
    private ProgressDialog pd;

    public RelativeSettings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRelativeSettingsBinding.inflate(inflater);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        token = sharedPreferences.getString(getString(R.string.const_token_key), "");

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = ProgressDialog.show(getContext(), "Loading...","Mengubah Password", false);
                handleResetPassword();
            }
        });

        binding.btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

    }

    private void handleResetPassword() {
        if (!isInputValid()){
            pd.dismiss();
            return;
        }

        String oldPassword = binding.edtOldPassword.getText().toString();
        String newPassword = binding.edtNewPassword1.getText().toString();


        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest(oldPassword, newPassword);

        Call<BasicResponse> call = apiInterface.resetRelativePassword("Bearer " + token, resetPasswordRequest);

        Log.d("debugdriski", "handleResetPassword: " + oldPassword);
        Log.d("debugdriski", "handleResetPassword: " + newPassword );

        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                pd.dismiss();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                if (response.body() == null) {
                    Snackbar snackbar = Snackbar.make(getView(), "Password lama salah.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
//                    Toast.makeText(getContext(), "Password lama salah.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getStatus() != 200) {
                    Log.d("debugdriski", "onResponse: Call gagal");
                    Toast.makeText(getContext(), "Terjadi kesalahan. Coba lagi nanti :(", Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(getContext(), "Sukses mengubah password.", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigateUp();
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Log.d("debugdriski", "onResponse: Call gagal");
                Toast.makeText(getContext(), "Terjadi kesalahan. Coba lagi nanti :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isInputValid() {
        String password1 = binding.edtNewPassword1.getText().toString();
        String password2 = binding.edtNewPassword2.getText().toString();
        String oldPassword = binding.edtOldPassword.getText().toString();

        if (oldPassword.isEmpty()) {
            binding.edtOldPassword.setError("Tidak boleh kosong.");
            return false;
        }

        if (password1.isEmpty()) {
            binding.edtNewPassword1.setError("Tidak boleh kosong.");
            return false;
        }

        if (password2.isEmpty()) {
            binding.edtNewPassword2.setError("Tidak boleh kosong.");
            return false;
        }

        if (!password1.equals(password2)){
            binding.edtNewPassword2.setError("Password tidak sama.");
            binding.edtNewPassword1.setError("Password tidak sama.");
            return false;
        }

        return true;
    }
}