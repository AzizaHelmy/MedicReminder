package com.example.medicationreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.medicationreminder.healthTakers.addTaker.view.AddHealthTakerFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    NavController navController;
    NavHostFragment navHostFragment;
     DrawerLayout drawerLayout;
    BadgeDrawable badgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        badgeDrawable=bottomNavigationView.getOrCreateBadge(R.id.healthTakersFragment);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(AddHealthTakerFragment.REQUEST_COUNTER);

//==============================================
//        @SuppressLint("IdleBatteryChargingConstraints")
//        Constraints constraints = new Constraints.Builder()
//                .setRequiresCharging(true)
//                .build();
//        PeriodicWorkRequest refillReminderRequest = new PeriodicWorkRequest
//                .Builder(RefillReminderWorker.class, 1, TimeUnit.DAYS)
//                .setConstraints(constraints)
//                // .setInitialDelay(diff,TimeUnit.MILLISECONDS )
//                .build();
//        WorkManager.getInstance(getBaseContext()).enqueue(refillReminderRequest);
//==========================================================
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.addMedicationFragment || navDestination.getId() == R.id.addHealthTakerFragment || navDestination.getId() == R.id.displayMedicineFragment) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
///========================================================
        drawerLayout=findViewById(R.id.drawerLayout);
        Toolbar toolbar=findViewById(R.id.toolbar);

       // toolbar.setNavigationIcon(R.drawable.user_icon);

        NavigationView navigationView=findViewById(R.id.navigation_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupWithNavController(toolbar, navController);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
}