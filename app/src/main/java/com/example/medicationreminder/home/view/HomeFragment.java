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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.view.AddMedicationFragment;
import com.example.medicationreminder.databinding.FragmentAddHealthTakerBinding;
import com.example.medicationreminder.databinding.FragmentHomeBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.healthTakers.view.AddHealthTakerFragment;
import com.example.medicationreminder.home.presenter.HomePresenter;
import com.example.medicationreminder.home.presenter.HomePresenterInterface;
import com.example.medicationreminder.home.view.model.HoursModel;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.github.clans.fab.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;


public class HomeFragment extends Fragment implements HomeViewInterface {

    HorizontalCalendar horizontalCalendar;
    RecyclerView recyclerView;
    MedicineAdapter drugAdapter;
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
//لسه
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_home, container, false);
        btnAddMedicine=viewRoot.findViewById(R.id.btn_addMedicine);
        btnHealthTracker=viewRoot.findViewById(R.id.btn_healthTracker);
        homePresenterInterface = new HomePresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
         insert();
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        homePresenterInterface.selectAllDrugsForHome("sun", getViewLifecycleOwner());
        horizontalCalendar = new HorizontalCalendar.Builder(viewRoot, R.id.calendarView).showMonthName(false)
                .dayNumberFormat("d")
                .textSizeDayNumber(16)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .build();

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
                Log.e(TAG, "onCalendarScroll: " + "dx=" + dx + "dy   " + dy);

            }

        });

              btnAddMedicine.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      AddMedicationFragment medicationFragment= new AddMedicationFragment();
                      getActivity().getSupportFragmentManager().beginTransaction()
                              .replace(R.id.medicationsFragment,medicationFragment, "findThisFragment")
                              .addToBackStack(null)
                              .commit();
                  }
              });
              btnHealthTracker.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      AddHealthTakerFragment addHealthTakerFragment=new AddHealthTakerFragment();

                      getActivity().getSupportFragmentManager().beginTransaction()
                              .replace(R.id.addHealthTakerFragment,addHealthTakerFragment, "findThisFragment")
                              .addToBackStack(null)
                              .commit();

                  }
              });
    }


    @Override
    public void insert() {
        Log.e(TAG, "insert: ");
        ArrayList<Medication> medications = new ArrayList<>();
        ArrayList<String> days1 = new ArrayList<String>();
        days1.add("sun");
        ArrayList<String> days2 = new ArrayList<String>();

        days2.add("sun");
        ArrayList<String> days3 = new ArrayList<String>();
        days3.add("sat");
        days3.add("sun");
        ArrayList<String> days4 = new ArrayList<String>();
        days4.add("sat");
        String[] drugs1 = {"10:30 pm", "2:00 pm", "6:40 am"};
        String[] drugs2 = {"8:09 pm", "8:09 pm", "5:00 am"};
        String[] drugs3 = {"11:30 am", "4:16 pm", "6:50 pm"};
        String[] drugs4 = {"10:20 am", "2:49 pm", "2:00 pm"};


        homePresenterInterface.insertMed(new Medication("doaa", "aziza", true,days1, drugs1));

        homePresenterInterface.insertMed(new Medication("salma", "yssmeen", true,days2,  drugs2));

        homePresenterInterface.insertMed(new Medication("azza", "riaaan",true, days3,  drugs3));

        homePresenterInterface.insertMed(new Medication("yasmeen", "salma",true, days4,  drugs4));

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

    public ArrayList<HoursModel> timeList(ArrayList<Medication> med) {
        ArrayList<Medication> medicine = new ArrayList<>();
        ArrayList<HoursModel> hoursModels = new ArrayList<>();
        ArrayList<String> hours = new ArrayList<>();
        List<String> listOfHours=new ArrayList<>();
        //list of all time in day
        for (int i = 0; i < med.size(); i++) {
            String[] hour = med.get(i).getDrugs();    //5 pm
            for (int j = 0; j < hour.length; j++) {
                if (!hours.contains(hour[j])){
                    hours.add(hour[j]);
                } } }
        for (int i=0;i<hours.size();i++){
            Log.e(TAG, "timeList: unsort "+hours.get(i));
        }
    // sort array of time
       convertToTime(hours);
        for (int i=0;i<hours.size();i++){
            Log.e(TAG, "timeList: sort "+hours.get(i));
        }

        int count = 0;
        while (hours.size() > count) {
            for (int i = 0; i < med.size(); i++) {
                for (int j = 0; j < med.get(i).getDrugs().length; j++) {
                    if (med.get(i).getDrugs()[j] == (hours.get(count))) {
                        medicine.add(med.get(i));
                        break;
                    } } }
           // Log.e(TAG, "timeList:hours_index "+hoursModels.size());
            hoursModels.add(new HoursModel(hours.get(count), medicine));
           // Log.e(TAG, "timeList:after "+hoursModels.size());

            medicine.clear();
            count++;
        }
        return hoursModels;
    }

    public ArrayList<Medication> medicationList() {
        ArrayList<Medication> medications = new ArrayList<>();
        ArrayList<String> days1 = new ArrayList<String>();
        days1.add("sun");
        ArrayList<String> days2 = new ArrayList<String>();

        days2.add("sun");
        ArrayList<String> days3 = new ArrayList<String>();
        days3.add("sat");
        days3.add("sun");
        ArrayList<String> days4 = new ArrayList<String>();
        days4.add("sat");
        String[] drugs1 = {"10:30 pm", "2:00 pm", "6:40 am"};
        String[] drugs2 = {"8:09 pm", "8:09 pm", "5:00 am"};
        String[] drugs3 = {"11:30 am", "4:16 pm", "6:50 pm"};
        String[] drugs4 = {"10:20 am", "2:49 pm", "2:00 pm"};

//
//        medications.add(new Medication("doaa", "medicine1", days1, true, drugs1));
//
//        medications.add(new Medication("salma", "medicine2", days2, true, drugs2));
//
//        medications.add(new Medication("azza", "medicine3", days3, true, drugs3));
//
//        medications.add(new Medication("yasmeen", "medicine4", days4, true, drugs4));
        return medications;
    }




 private void convertToTime(ArrayList<String> list){
        Collections.sort(list, new Comparator<String>() {

         @Override
         public int compare(String o1, String o2) {
             try {
                 return new SimpleDateFormat("hh:mm a").parse(o1).compareTo(new SimpleDateFormat("hh:mm a").parse(o2));
             } catch (ParseException e) {
                 return 0;
             }
         }
     });

 }



}

