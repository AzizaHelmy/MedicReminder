package com.example.medicationreminder;

import static com.example.medicationreminder.medications.view.MedicationsFragment.TAG;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicationreminder.databinding.ActivityMainBinding;
import com.example.medicationreminder.login.view.LoginActivity;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    NavHostFragment navHostFragment;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ImageView userImage;
    String saveUserImage;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        preferences = getSharedPreferences(RegisterActivity.SHARD_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        ////////////////////////////////////
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController( binding.bottomNavView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.addMedicationFragment || navDestination.getId() == R.id.addHealthTakerFragment || navDestination.getId() == R.id.displayMedicineFragment) {
                 binding.bottomNavView.setVisibility(View.GONE);
                } else {
                   binding.bottomNavView .setVisibility(View.VISIBLE);
                }
            }
        });

//////////////////////////////////////////////
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.addMedicationFragment || navDestination.getId() == R.id.addHealthTakerFragment || navDestination.getId() == R.id.displayMedicineFragment) {
                binding.bottomNavView  .setVisibility(View.GONE);
                } else {
                 binding.bottomNavView   .setVisibility(View.VISIBLE);
                }
            }
        });
/////////////////////////////////////


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavigationUI.setupWithNavController(binding.navigationView, navController);
        View headerView = binding.navigationView.getHeaderView(0);
        headerContent(headerView);


    }


    public void openDrawer(View view) {
        if(binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        }else{
            binding.drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    private  void headerContent(View headerView){
         userImage=headerView.findViewById(R.id.user_image);
         TextView userName=headerView.findViewById(R.id.user_name_header);
         TextView userEmail=headerView.findViewById(R.id.user_email_header);
         userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage.launch("image/*");
            }
        });


        userName.setText(preferences.getString(RegisterActivity.USER_NAME,""));
        binding.include.userName.setText(preferences.getString(RegisterActivity.USER_NAME,""));
        userEmail.setText(preferences.getString(RegisterActivity.EMAIL,""));
        userImage.setImageURI(Uri.parse(preferences.getString("image","kkk")));
        Log.e(TAG, "headerContent: "+Uri.parse(preferences.getString("image","")));
    }
    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    Uri selected_image = uri;
                    // this.grantUriPermission(this.getPackageName(), selected_image, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    // final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    // this.getContentResolver().takePersistableUriPermission(selected_image, takeFlags);

                    editor.putString("image",uri.toString());
                    editor.apply();
                    binding.include.userImageToolbar.setImageURI(uri);
                    userImage.setImageURI(uri);
                }
            });
}