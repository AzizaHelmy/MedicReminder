package com.example.medicationreminder.home.view;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.animation.content.Content;
import com.example.medicationreminder.R;
import com.example.medicationreminder.home.view.model.HoursModel;

import java.util.ArrayList;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ItemViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
      Context content;
    private ArrayList<HoursModel> medicineList;
    private HomeViewInterface homeViewInterface;


    public DayAdapter(Context content, ArrayList<HoursModel> medicineList, HomeViewInterface homeViewInterface) {
        this.content = content;
        this.medicineList = medicineList;
        this.homeViewInterface = homeViewInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.day_drags, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
  HoursModel  hoursModel = medicineList.get(i);
        Log.e(TAG, "onBindViewHolder: "+hoursModel.getTitle());
       itemViewHolder.hourTitle.setText(hoursModel.getTitle()+"");


        LinearLayoutManager layoutManager = new LinearLayoutManager(
                itemViewHolder.drugs_recycler.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(hoursModel.getMedicationList().size());

     MedicineAdapter medicineAdapter = new MedicineAdapter(hoursModel.getMedicationList());

        itemViewHolder.drugs_recycler.setLayoutManager(layoutManager);
        itemViewHolder.drugs_recycler.setAdapter(medicineAdapter);
        itemViewHolder.drugs_recycler.setRecycledViewPool(viewPool);
    }

    public  void setData(ArrayList<HoursModel>medicineList){
         this.medicineList=medicineList;
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView drugs_recycler;
        private TextView hourTitle;

        ItemViewHolder(View itemView) {
            super(itemView);
            hourTitle = itemView.findViewById(R.id.hour_title);

         drugs_recycler = itemView.findViewById(R.id.drugs_recycler);
        }
    }
}
