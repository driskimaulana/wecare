package com.kelompok4.wecare.view.relative;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kelompok4.wecare.R;
import com.kelompok4.wecare.databinding.ActivityRelativeMainBinding;

public class RelativeMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityRelativeMainBinding binding;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setNavigationViewListener();

        binding = ActivityRelativeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//        NavController navController = Navigation.findNavController(binding.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>WeCare</font>"));

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        sidebar
        toggle = new ActionBarDrawerToggle(this, binding.layoutDrawer, R.string.open, R.string.close);
        binding.layoutDrawer.addDrawerListener(toggle);

        DrawerArrowDrawable drawerIcon = toggle.getDrawerArrowDrawable();
        drawerIcon.setColor(0xFFFFFFFF);
        toggle.setDrawerArrowDrawable(drawerIcon);

        toggle.syncState();

        binding.btnElderSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RelativeMainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.navigateToElderSettings);
            }
        });

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void handleElderSettingsButton(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }

    public void handleAddEldersButton(View view) {
        Intent intent = new Intent(this, AddElderActivity.class);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.elder1: {
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
//            default:
//                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.layoutDrawer);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
    }

}