package com.example.medicationreminder.login.restPassword.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.databinding.ActivityRestPasswordBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.login.presenter.LoginPresenter;
import com.example.medicationreminder.login.restPassword.presnter.RestPasswordPresenter;
import com.example.medicationreminder.login.restPassword.presnter.RestPasswordPresenterInterface;
import com.example.medicationreminder.model.Repository;
import com.google.firebase.auth.FirebaseUser;

public class RestPassword extends AppCompatActivity implements RestPasswordViewInterface {
    ActivityRestPasswordBinding binding;
    RestPasswordPresenterInterface restPasswordPresenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRestPasswordBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


       restPasswordPresenterInterface =new RestPasswordPresenter(this, Repository.getRepository(this, FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(this)),this);

    }

    public void onSendEmailClick(View view) {

        if (restPasswordPresenterInterface.restPassword(binding.restEmail.getEditText().getText().toString()) == true) {

            Toast.makeText(this, "send ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "no ", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void loginFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void successLogin(FirebaseUser user) {
        Toast.makeText(this, "send ", Toast.LENGTH_SHORT).show();
    }
}