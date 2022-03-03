package com.example.medicationreminder.home.view;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.view.AddMedicationFragment;

import com.example.medicationreminder.databinding.FragmentHomeBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.healthTakers.view.AddHealthTakerFragment;
import com.example.medicationreminder.home.presenter.HomePresenter;
import com.example.medicationreminder.home.presenter.HomePresenterInterface;
import com.example.medicationreminder.home.view.model.HoursModel;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.github.clans.fab.FloatingActionButton;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;


public class HomeFragment extends Fragment implements HomeViewInterface {

    HorizontalCalendar horizontalCalendar;
    RecyclerView recyclerView;
    MedicineAdapter drugAdapter;
    TextView toDayDate;
    View viewRoot;
    HomePresenterInterface homePresenterInterface;
    DayAdapter dayAdapter;
    FloatingActionButton btnAddMedicine;
    FloatingActionButton btnHealthTracker;
     private FragmentHomeBinding binding;
    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback pressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, pressedCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_home, container, false);
        btnAddMedicine=viewRoot.findViewById(R.id.btn_addMedicine);
        btnHealthTracker=viewRoot.findViewById(R.id.btn_healthTracker);

        homePresenterInterface = new HomePresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
        toDayDate=viewRoot.findViewById(R.id.day_date);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
//        homePresenterInterface.selectAllDrugsForHome("sun", getViewLifecycleOwner());
        horizontalCalendar = new HorizontalCalendar.Builder(viewRoot, R.id.calendarView).showMonthName(false)
                .dayNumberFormat("d")
                .textSizeDayNumber(16)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .build();
       toDayDate.setText(getCurrentData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView=viewRoot.findViewById(R.id.day_recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        dayAdapter=new DayAdapter(getContext(),new ArrayList<HoursModel>(),this);
       recyclerView.setAdapter(dayAdapter);
        homePresenterInterface.selectAllDrugsForHome1(this);


        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                String day = date.toString().substring(0, 3);

                homePresenterInterface.selectAllDrugsForHome1(getViewLifecycleOwner()).observe(getViewLifecycleOwner(), new Observer<List<Medication>>() {

                    @Override
                    public void onChanged(List<Medication> medications) {


                        dayAdapter.setData(list((ArrayList<Medication>) medications));
                        dayAdapter.notifyDataSetChanged();


                    }
                });
            }


            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

        });

              btnAddMedicine.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_addMedicationFragment);
                  }
              });
              btnHealthTracker.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_addHealthTakerFragment);


                  }
              });
    }




    @Override
    public LiveData<List<Medication>> selectAllDrugsForHome(String day, LifecycleOwner owner) {
        return homePresenterInterface.selectAllDrugsForHome(day, getViewLifecycleOwner());
    }

    @Override
    public LiveData<List<Medication>> selectAllDrugsForHome1(LifecycleOwner owner) {
        return homePresenterInterface.selectAllDrugsForHome1(getViewLifecycleOwner());
    }



private ArrayList<HoursModel>list(ArrayList<Medication>med){
    LinkedHashMap<String,ArrayList<Medication>> times=new LinkedHashMap<>();
    for (Medication medicien: med){
        for(String time:medicien.getDrugs()){
            times.put(time ,new ArrayList<Medication>());
        }
    }
    //12:50 pm
    for (Medication medicien: med){
        for(String time:medicien.getDrugs()){
            times.get(time).add(medicien);


        }
    }
    ArrayList<HoursModel>hourList=new ArrayList<>();
    for(Map.Entry<String,ArrayList<Medication>>entry:times.entrySet()){
        hourList.add(new HoursModel(entry.getKey(),entry.getValue()));
    }
    return hourList;
}





private  String getCurrentData(){
    Date c = Calendar.getInstance().getTime();
    System.out.println("Current time => " + c);

    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    String formattedDate = df.format(c);
    return formattedDate;
}

}

