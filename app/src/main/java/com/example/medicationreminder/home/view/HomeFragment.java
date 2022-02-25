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
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;


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
       // insert();
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
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {

                String day=date.toString().substring(0,3);

              List<Medication> med=selectAllDrugsForHome( "sun");
               if (med==null){
                   Log.e(TAG, "onDateSelected: "+"prafo ya yassmen");
               }
               else {
                   for (int i = 0; i <med.size() ; i++) {
                       Log.e(TAG, "onDateSelected: " + med.get(i).getMedicine_Name());
                   }
               }
//                try {
//                    date= new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(date.toString());
//                    Log.e(TAG, "onDateSelected: "+date);
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Log.e(TAG, "onDateSelected: "+date);
//                Log.e(TAG, "onDateSelected: "+date.toLocaleString());
//                Log.e(TAG, "onDateSelected: "+date.getTime());
//                Log.e(TAG, "onDateSelected: "+date.getMonth());
//                Log.e(TAG, "onDateSelected: pos"+position);

            }



            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {
                Log.e(TAG, "onCalendarScroll: "+"dx="+dx   +"dy   "+dy);

            }

        });

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



    @Override
    public void insert() {
        Log.e(TAG, "insert: ");

        int i = 0;

     ArrayList<String> days1=new ArrayList<String>();
     days1.add("sat");
     days1.add("sun");
        homePresenterInterface.insertMed(new Medication("doaa"+i,"de",days1));
        i++;
        homePresenterInterface.insertMed(new Medication("doaa"+i,"de",days1));
        i++;
        homePresenterInterface.insertMed(new Medication("doaa"+i,"de",days1));
        i++;
        homePresenterInterface.insertMed(new Medication("doaa"+i,"de",days1));

    }

    @Override
    public List<Medication>selectAllDrugsForHome(String day) {
        return homePresenterInterface.selectAllDrugsForHome(day);
    }
}

