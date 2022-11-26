package com.kelompok4.wecare.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentSignupBinding;
import com.kelompok4.wecare.model.auth.UserSignin;
import com.kelompok4.wecare.model.auth.UserSignup;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import java.util.regex.Pattern;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateSignupToLogin);
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInputValid()) return;

                handleSignup(view);
//                Navigation.findNavController(view).navigate(R.id.navigateToChooseRole);
            }
        });

        binding.btnSignupGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigateToChooseRole);
            }
        });
    }

    private boolean isInputValid() {
        String name = binding.inputNama.getText().toString();
        String email = binding.inputEmail.getText().toString();
        String password = binding.inputPassword.getText().toString();
        String confirmPassword = binding.inputConfirmPassword.getText().toString();

        if (name.length() == 0) {
            binding.inputNama.setError("Nama tidak boleh kosong.");
            return false;
        }

        if (!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
            binding.inputEmail.setError("Email tidak valid.");
            return false;
        }

        if (password.length() == 0) {
            binding.inputPassword.setError("Password tidak boleh kosong.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            binding.inputPassword.setError("Password tidak sama.");
            binding.inputConfirmPassword.setError("Password tidak sama.");
            return false;
        }

        return true;
    }

    private void handleSignup(View view) {
        String nama = binding.inputNama.getText().toString();
        String email = binding.inputEmail.getText().toString();
        String password = binding.inputPassword.getText().toString();
        UserSignup userSignup = new UserSignup(nama, email, password, "");

        String userSignupJson = GsonUtils.getGson().toJson(userSignup);

        Bundle bundle = new Bundle();
        bundle.putString("SIGNUP_DATA", userSignupJson);

        Navigation.findNavController(view).navigate(R.id.chooseRoleFragment, bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}