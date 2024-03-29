package com.kelompok4.wecare.view;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.FragmentChooseRoleBinding;
import com.kelompok4.wecare.model.auth.AuthResponse;
import com.kelompok4.wecare.model.auth.UserSignup;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.elder.ElderMainActivity;
import com.kelompok4.wecare.view.relative.RelativeMainActivity;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseRoleFragment extends Fragment {

    private FragmentChooseRoleBinding binding;
    private ApiInterface mApiInterface;
    private UserSignup userSignup;
    private ProgressDialog pd;

    public ChooseRoleFragment() {
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

        Bundle receiveBundle = getArguments();
        userSignup = GsonUtils.getGson().fromJson(receiveBundle.getString("SIGNUP_DATA"), UserSignup.class);
        binding.tvChooseRole.setText("Halo " + userSignup.getName() + ", Pilih Role Mu");

        binding.containerElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignup.setRole("Elder");
                handleSignup(view);
//                Intent intent = new Intent(getActivity(), ElderMainActivity.class);
//                startActivity(intent);
            }
        });

        binding.containerRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignup.setRole("Relative");
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
                handleSignup(view);
            }
        });
    }

    private void handleSignup(View view) {
        pd = ProgressDialog.show(getContext(), "Loading", "Sign Up ...", false);
        Call<AuthResponse> signupCall = mApiInterface.signup(userSignup);
        signupCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                pd.dismiss();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                if (response.body() == null) {
                    Log.e("debugdriski", "onResponse: body null");
                    Snackbar snackbar = Snackbar.make(getView(), "Email telah digunakan.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    return;
                }

                String userLoggedin = GsonUtils.getGson().toJson(response.body().getResult());

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.const_token_key), response.body().getToken());
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("USER_LOGGED_IN", userLoggedin);
                Intent intent;
                if (response.body().getResult().getRole().equals("Relative")) {

                    intent = new Intent(getActivity(), RelativeMainActivity.class);
                }else {
                    intent = new Intent(getActivity(), ElderMainActivity.class);
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Register Gagal", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+ t.toString() );
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChooseRoleBinding.inflate(inflater, container, false);
        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_choose_role, container, false);
    }
}