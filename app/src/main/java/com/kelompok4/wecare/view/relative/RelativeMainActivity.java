package com.kelompok4.wecare.view.relative;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.ActivityRelativeMainBinding;

public class RelativeMainActivity extends AppCompatActivity {

    private ActivityRelativeMainBinding binding;

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

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#16558F"));

        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("WeCare");
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        sidebar
        toggle = new ActionBarDrawerToggle(this, binding.layoutDrawer, R.string.open, R.string.close);
        binding.layoutDrawer.addDrawerListener(toggle);

        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}