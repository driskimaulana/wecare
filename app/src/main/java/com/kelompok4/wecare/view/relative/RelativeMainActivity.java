package com.kelompok4.wecare.view.relative;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.ActivityRelativeMainBinding;
import com.kelompok4.wecare.model.user.User;
import com.kelompok4.wecare.viewmodel.utils.GsonUtils;

public class RelativeMainActivity extends AppCompatActivity {

    private ActivityRelativeMainBinding binding;
    Context context = this;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRelativeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//        NavController navController = Navigation.findNavController(binding.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

//        bundle current user
        Bundle bundle = getIntent().getExtras();
        User currentUser = GsonUtils.getGson().fromJson(bundle.getString("USER_LOGGED_IN"), User.class);

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

//        get navigation view
        NavigationView navigationView = findViewById(R.id.navView);
        View header = navigationView.getHeaderView(0);
        TextView profileName = header.findViewById(R.id.tv_profile_name);
        profileName.setText(currentUser.getName());

        DrawerArrowDrawable drawerIcon = toggle.getDrawerArrowDrawable();
        drawerIcon.setColor(0xFFFFFFFF);
        toggle.setDrawerArrowDrawable(drawerIcon);

        toggle.syncState();

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
        if (intentResult.getContents() != null){
            //Alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    RelativeMainActivity.this, R.style.DialogTheme
            );
            //Set title
            builder.setTitle("Result");
            //Set msg
            builder.setMessage(intentResult.getContents());
            //set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();
                }
            });
            //Show alert
            builder.show();
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
        Toast.makeText(this, "add elder", Toast.LENGTH_SHORT).show();
    }
}