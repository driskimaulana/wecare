package com.kelompok4.wecare.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentLoginBinding;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.auth.UserSignin;
import com.kelompok4.wecare.view.elder.ElderMainActivity;
import com.kelompok4.wecare.view.relative.RelativeMainActivity;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    ApiInterface mApiInterface;
    private ProgressDialog pd;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isInputValid()) return;

                handleLogin(view);
            }
        });
    }

    private void handleLogin(View view) {
        pd = ProgressDialog.show(getContext(), "Loading", "Login...", false);
        UserSignin userSignin = new UserSignin(binding.inputEmail.getText().toString(), binding.inputPassword.getText().toString());
        Call<AuthResponse> signinCall = mApiInterface.signin(userSignin);
        signinCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
//                save token to shared preferences
                if (response.body() != null) {
                    editor.putString(getString(R.string.const_token_key), response.body().getToken());
                    editor.apply();
                }

//                send logged in user to activity based on their role
                Bundle bundle = new Bundle();
                bundle.putString("USER_LOGGED_IN", GsonUtils.getGson().toJson(response.body().getResult()));
                Intent intent;
                if (response.body().getResult().getRole().equals("Relative")) {
                    editor.putInt(getString(R.string.ELDER_KEY), 0);
                    editor.apply();
                    intent = new Intent(getActivity(), RelativeMainActivity.class);
                }else {
                    intent = new Intent(getActivity(), ElderMainActivity.class);
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("Error", "onFailure: "+ t.toString() );
                pd.dismiss();
                Toast.makeText(getActivity(), "ga bisaa :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInputValid() {
        String email = binding.inputEmail.getText().toString();
        String password = binding.inputPassword.getText().toString();

        if (email.length() == 0) {
            binding.inputEmail.setError("Email tidak boleh kosong.");
            return false;
        }

        if (password.length() == 0) {
            binding.inputPassword.setError("Password tidak boleh kosong.");
            return false;
        }

        if (!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
            binding.inputEmail.setError("Email tidak valid.");
            return false;
        }

        if (password.length() < 6) {
            binding.inputPassword.setError("Password harus lebih dari 6 karakter.");
            return false;
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}