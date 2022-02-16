package com.example.medicationreminder.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicationreminder.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;


public class HomeFragment extends Fragment {


 View viewRoot;
//    ViewPager viewPager;
//    DemoCollectionPagerAdapter demoCollectionPagerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     viewRoot=inflater.inflate(R.layout.fragment_home, container, false);

//.setupWithViewPager(viewPager);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(viewRoot, R.id.calendarView).showMonthName(false)
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
//        viewPager = view.findViewById(R.id.pager);
//        viewPager.setAdapter(demoCollectionPagerAdapter);
//        viewPager.canScrollHorizontally(2);

    }
//    public List<Model>getData(){
//         List<Model>list=new ArrayList<>();
//         for (int i=0;i<5;i++){
//             Model model=new Model("doaa"+i,"hello"+i);
//         }
//
//       return list;
//
//    }


}