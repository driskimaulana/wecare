package com.kelompok4.wecare.view.relative;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.ActivityRelativeMainBinding;
import com.kelompok4.wecare.model.BasicResponse;
import com.kelompok4.wecare.model.user.ListElderConnected;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.view.MainActivity;
import com.kelompok4.wecare.view.relative.adapter.ElderListAdapter;
import com.kelompok4.wecare.viewmodel.rest.ApiClient;
import com.kelompok4.wecare.viewmodel.rest.ApiInterface;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;
import com.kelompok4.wecare.viewmodel.utils.SelectListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RelativeMainActivity extends AppCompatActivity implements SelectListener {

    private ActivityRelativeMainBinding binding;
    Context context = this;
    private ActionBarDrawerToggle toggle;
    ApiInterface mApiInterface;
    private User currentUser;
    private String jwtToken;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRelativeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // get jwt token from shared pref
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        jwtToken = sharedPreferences.getString(getString(R.string.const_token_key), "");

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//        NavController navController = Navigation.findNavController(binding.fragmentContainerView);
        navController = navHostFragment.getNavController();

        // check if there is notification

//        bundle current user
        Bundle bundle = getIntent().getExtras();
        currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

        Toast.makeText(this, currentUser.getName(), Toast.LENGTH_SHORT).show();

        navController.setGraph(R.navigation.relative_nav, getIntent().getExtras());

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>WeCare</font>"));

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        sidebar
        toggle = new ActionBarDrawerToggle(this, binding.layoutDrawer, R.string.open, R.string.close);
        binding.layoutDrawer.addDrawerListener(toggle);

        // side bar elder lists
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvElderList.setLayoutManager(layoutManager);

        Call<ListElderConnected> call = mApiInterface.getElderConnected("Bearer " + jwtToken);

        call.enqueue(new Callback<ListElderConnected>() {
            @Override
            public void onResponse(Call<ListElderConnected> call, Response<ListElderConnected> response) {
                if (response.body().getStatus() != 200){
                    Log.d("debugdriski", "onResponse: Fail to get elder connected data.");
                    Toast.makeText(RelativeMainActivity.this, "Gagal mendapatkan data elder.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ElderListAdapter adapter = new ElderListAdapter(response.body().getListElderConnected(), RelativeMainActivity.this);
                binding.rvElderList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListElderConnected> call, Throwable t) {
                Log.d("debugdriski", "onResponse: Fail to get elder connected data.");
                Toast.makeText(RelativeMainActivity.this, "Gagal mendapatkan data elder.", Toast.LENGTH_SHORT).show();
            }
        });


//        get navigation view
        NavigationView navigationView = findViewById(R.id.navView);
        View header = navigationView.getHeaderView(0);
        TextView profileName = header.findViewById(R.id.tv_profile_name);
        profileName.setText(currentUser.getName());

        ImageButton logoutButton = header.findViewById(R.id.btn_profile_logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogout();
            }
        });

        DrawerArrowDrawable drawerIcon = toggle.getDrawerArrowDrawable();
        drawerIcon.setColor(0xFFFFFFFF);
        toggle.setDrawerArrowDrawable(drawerIcon);

        toggle.syncState();

        binding.btnAddElder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAddEldersButton(view);
            }
        });

        // Drawer menu event listener
        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.elder1:
                    Toast.makeText(RelativeMainActivity.this, "elder1", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.elder2:
                    Toast.makeText(RelativeMainActivity.this, "elder2", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.elder3:
                    Toast.makeText(RelativeMainActivity.this, "elder3", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    return false;
            }
            return true;
        });

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //QR SCAN
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //Check condition
        Toast.makeText(context, "Scanned", Toast.LENGTH_SHORT).show();

        if (intentResult.getContents() != null) {
            binding.loadingBar.setVisibility(View.VISIBLE);
            Call<BasicResponse> connectResponseCall = mApiInterface.connectAccount(intentResult.getContents(), "Bearer " + jwtToken);
            connectResponseCall.enqueue(new Callback<BasicResponse>() {
                @Override
                public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                    if (response.body().getStatus() == 200) {
                        Toast.makeText(context, intentResult.getContents(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RelativeMainActivity.this, ConnectSuccessActivity.class);
                        startActivity(intent);
                        return;
                    } else {
                        Toast.makeText(RelativeMainActivity.this, "Failed to connect account. Network Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BasicResponse> call, Throwable t) {
                    Toast.makeText(RelativeMainActivity.this, "Failed to connect account. Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //if content null
            Toast.makeText(getApplicationContext(), "Did not scan anything", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            Toast.makeText(this, "drawer toggle clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void handleElderSettingsButton(View view) {
        Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();

        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navCtrl = hostFragment.getNavController();

        navCtrl.navigateUp();
        navCtrl.navigate(R.id.elderSettingsFragment);

        binding.layoutDrawer.closeDrawer(GravityCompat.START);
    }

    public void handleAddEldersButton(View view) {
        Log.d("debugdriski", "handleAddEldersButton: add clicked");
//        navController.navigate(R.id.navigateToAddElder);
        IntentIntegrator intentIntegrator = new IntentIntegrator(
                RelativeMainActivity.this
        );
        intentIntegrator.setPrompt("Press 'VOLUME UP' to activate flash\nPress 'VOLUME DOWN' to deactivate flash.");
        //Set beep
        intentIntegrator.setBeepEnabled(true);
        //lock orientation
        intentIntegrator.setOrientationLocked(true);
        //set capture activity
        intentIntegrator.setCaptureActivity(Capture.class);
        //Initiate scan
        intentIntegrator.initiateScan();
//        Navigation.findNavController(view).navigate(R.id.navigateDashboardToAddElder);
        Toast.makeText(this, "add elder", Toast.LENGTH_SHORT).show();
    }

    private void handleLogout() {
        Log.d("driskidebug", "onClick: Logout");
        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Runtime.getRuntime().exit(0);
    }

    @Override
    public void onItemClicked(User user, int position) {
        Log.d("debugdriski", "onItemClicked: clicked rv " + user.getName() + position);
        Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.const_sharedpref_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.ELDER_KEY), position);
        editor.apply();

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
//        Runtime.getRuntime().exit(0);
    }
}