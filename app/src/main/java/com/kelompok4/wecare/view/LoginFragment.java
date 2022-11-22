package com.kelompok4.wecare.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kelompok4.wecare.databinding.FragmentLoginBinding;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.auth.UserSignin;
import com.kelompok4.wecare.model.healthEducation.GetHealthEducation;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private ApiInterface mApiInterface;

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
                if (!validateLogin()) return;
//
                handleLogin(binding.inputEmail.getText().toString(), binding.inputPassword.getText().toString());
            }

        });

    }

    private Boolean validateLogin() {

        String email = binding.inputEmail.getText().toString();
        String password = binding.inputPassword.getText().toString();

        String regexPattern = "^(.+)@(\\S+)$";
        if (!Pattern.compile(regexPattern).matcher(email).matches()){
            binding.inputEmail.setError("Email tidak valid.");
            return false;
        }

        if (password.length() < 6) {
            binding.inputPassword.setError("Password harus lebih dari 6 karakter.");
            return false;
        }

        return true;
    }

    private void handleLogin(String email, String password){
        UserSignin userSignin = new UserSignin(email, password);
//        Call<GetHealthEducation> healthEducationCall = mApiInterface.getHealthEducation();
//        Call<AuthResponse> signinCall = mApiInterface.signIn(userSignin);
//        Call<AuthResponse> signinCall = mApiInterface.signIn();
        Toast.makeText(getActivity(), "bisa kok", Toast.LENGTH_SHORT).show();
//        signinCall.enqueue(new Callback<AuthResponse>() {
//            @Override
//            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
//                Toast.makeText(getActivity(), "Call", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<AuthResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}