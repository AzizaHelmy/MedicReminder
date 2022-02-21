package com.example.medicationreminder.home.view;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.model.Medication;

import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DrugsListViewHolder>{
    private List<Medication> medicationList;

    public DaysAdapter(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    @NonNull
    @Override
    public DrugsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_drags, parent, false);
        return new DrugsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugsListViewHolder holder, int position) {
        Medication item = medicationList.get(position);

    //   holder.drud_image.setImageIcon(item.getIcon().);
       holder.drug_name.setText(item.getMedicine_Name());
       holder.drug_time.setText(item.getDrugAmount());
     holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Log.e(TAG, "onClick: ");
         }
     });

    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    class DrugsListViewHolder extends RecyclerView.ViewHolder {
        ImageView drud_image;
        TextView drug_name;
        TextView drug_amount;
        TextView  drug_time;
        CardView cardView;

        DrugsListViewHolder(View itemView) {
            super(itemView);
                 drud_image=itemView.findViewById(R.id.img_sub_item);
            drug_name=itemView.findViewById(R.id.tv_item_title);
            drud_image=itemView.findViewById(R.id.time);
            cardView=itemView.findViewById(R.id.card);
        }
    }
}


