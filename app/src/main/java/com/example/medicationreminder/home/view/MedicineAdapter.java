package com.example.medicationreminder.home.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.home.view.model.HoursModel;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.register.view.RegisterActivity;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.DrugsListViewHolder>{
    private List<Medication> medicationList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
     Context context;
    public MedicineAdapter(List<Medication> medicationList,Context context) {
        this.medicationList = medicationList;
      preferences  = context.getSharedPreferences(RegisterActivity.SHARD_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
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
       holder.drug_type.setText(medicine.getMedicineType());
        holder.drug_amount.setText(medicine.getDrugAmount());
        if (!preferences.getString(RegisterActivity.USER_NAME, "").isEmpty()) {
            holder.drugAdder.setText(preferences.getString(RegisterActivity.USER_NAME, ""));
        }
     holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             Navigation.findNavController(view).navigate(R.id.action_homeFragment2_to_displayMedicineFragment);
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
        TextView drug_type;
        TextView  drug_amount;
        CardView cardView;
       TextView drugAdder;
        DrugsListViewHolder(View itemView) {
            super(itemView);
                 drud_image=itemView.findViewById(R.id.med_image);
              drug_name=itemView.findViewById(R.id.med_name);
           drug_type=itemView.findViewById(R.id.med_type);
              drugAdder=itemView.findViewById(R.id.med_adder);
            drug_amount=itemView.findViewById(R.id.med_amount);
          cardView=itemView.findViewById(R.id.card_view_item);
        }
    }
}


