package com.example.medicationreminder.home.presenter;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.medicationreminder.home.view.HomeViewInterface;
import com.example.medicationreminder.login.view.LoginViewInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.RepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter  implements HomePresenterInterface {


    Context context;
    RepositoryInterface repositoryInterface;
    HomeViewInterface homeViewInterface;

    public HomePresenter(Context context, RepositoryInterface repositoryInterface, HomeViewInterface homeViewInterface) {
        this.context = context;
        this.repositoryInterface = repositoryInterface;
        this.homeViewInterface = homeViewInterface;
    }

    @Override
    public void insertMed(Medication medication) {
        repositoryInterface.insertMed(medication);

    }

    @Override
    public LiveData<List<Medication>> selectAllDrugsForHome(String day, LifecycleOwner owner) {

        return repositoryInterface.selectAllDrugsForHome(day, owner);
    }

    @Override
    public LiveData<List<Medication>> selectAllDrugsForHome1(LifecycleOwner owner) {
        return repositoryInterface.selectAllDrugsForHome1(owner);
    }
}