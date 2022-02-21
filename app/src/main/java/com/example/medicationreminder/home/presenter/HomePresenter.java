package com.example.medicationreminder.home.presenter;

import android.content.Context;

import com.example.medicationreminder.home.view.HomeViewInterface;
import com.example.medicationreminder.login.view.LoginViewInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;

public class HomePresenter  implements HomePresenterInterface{


    Context context;
    RepositoryInterface repositoryInterface;
    HomeViewInterface  homeViewInterface;

    public HomePresenter(Context context, RepositoryInterface repositoryInterface, HomeViewInterface homeViewInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.homeViewInterface = homeViewInterface;
    }

    @Override
    public void insertMed(Medication medication) {
        repositoryInterface.insertMed(medication);

    }
}
