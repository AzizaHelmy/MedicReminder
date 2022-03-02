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
import com.example.medicationreminder.home.view.model.HoursModel;
import com.example.medicationreminder.model.Medication;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.DrugsListViewHolder>{
    private List<Medication> medicationList;

    public MedicineAdapter(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    @NonNull
    @Override
    public DrugsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drag_item, parent, false);
        return new DrugsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugsListViewHolder holder, int position) {
        Medication medicine = medicationList.get(position);

      holder.drud_image.setImageResource(medicine.getIcon());
       holder.drug_name.setText(medicine.getMedicine_Name());
      // holder.drug_type.setText(medicine.getMedicineType());
      // holder.drug_time.setText(medicine.getDrugAmount());
//     holder.cardView.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//
//
//         }
//     });

    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    class DrugsListViewHolder extends RecyclerView.ViewHolder {
        ImageView drud_image;
        TextView drug_name;
        TextView drug_type;
        TextView  drug_time;
        CardView cardView;

        DrugsListViewHolder(View itemView) {
            super(itemView);
                 drud_image=itemView.findViewById(R.id.med_image);
              drug_name=itemView.findViewById(R.id.med_name);
            //drug_type=itemView.findViewById(R.id.);
          cardView=itemView.findViewById(R.id.card_view);
        }
    }
}


