package com.example.medicationreminder.home.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.home.presenter.HomePresenter;
import com.example.medicationreminder.home.presenter.HomePresenterInterface;
import com.example.medicationreminder.login.restPassword.presnter.RestPasswordPresenter;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Drug;
import com.example.medicationreminder.model.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;


public class HomeFragment extends Fragment  implements HomeViewInterface{

    HorizontalCalendar horizontalCalendar;
    RecyclerView recyclerView;
    DaysAdapter drugAdapter;
    View viewRoot;
    HomePresenterInterface homePresenterInterface;


    public HomeFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     viewRoot=inflater.inflate(R.layout.fragment_home, container, false);
        homePresenterInterface =new HomePresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendar = new HorizontalCalendar.Builder(viewRoot, R.id.calendarView).showMonthName(false)
                .dayNumberFormat("d")
                .textSizeDayNumber(16)
                .startDate(startDate.getTime())

                .endDate(endDate.getTime())

                .build();


        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

//        recyclerView=viewRoot.findViewById(R.id.day_recycler);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        drugAdapter=new DaysAdapter(list());
//        recyclerView.setAdapter(drugAdapter);


    }

  public void list(){
       List<Medication> listOfData=new ArrayList<>();
       for(int i=0;i<10;i++){
           listOfData.add(new Medication("doaa"+i,"de"+i,"solution"+i,R.id.img_medic,200,"mg"+i,2));


       }
      // return listOfData;
  }

    @Override
    public void insert() {
        for (int i=0;i<10;i++){
          homePresenterInterface.insertMed(new Medication("doaa"+i,"de"+i,"solution"+i,R.id.img_medic,200,"mg"+i,2));
    }
}
}
