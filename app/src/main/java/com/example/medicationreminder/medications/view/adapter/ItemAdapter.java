package com.example.medicationreminder.medications.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.medications.view.MedicsOnClick;
import com.example.medicationreminder.model.Medication;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MedicsTypeViewHolder> {
    Context context;
    List<Medication> medics;
    MedicsOnClick onClick;

    public ItemAdapter(Context context, List<Medication> medics, MedicsOnClick onClick) {
        this.context = context;
        this.medics = medics;
        this.onClick=onClick;

    }

    @NonNull
    @Override
    public MedicsTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medic_item_rv, parent, false);
        return new MedicsTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicsTypeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Medication model = medics.get(position);
       // holder.tvMedicName.setText(model.getMedicName());
        holder.tvMedicAdder.setText(model.getDrugAdder());
        holder.tvMedicLeft.setText(model.getLeftDrug()+"");
        holder.tvMedicStrength.setText(model.getStrength()+"");
        holder.imgMedic.setImageResource(model.getIcon());
    }

    @Override
    public int getItemCount() {
        return medics.size();
    }

    //===============================================
    public class MedicsTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicName, tvMedicStrength, tvMedicLeft, tvMedicAdder;
        ImageView imgMedic;


        public MedicsTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMedicName = itemView.findViewById(R.id.tv_medic_name);
            tvMedicStrength = itemView.findViewById(R.id.tv_strngth);
            tvMedicLeft = itemView.findViewById(R.id.tv_refill);
            tvMedicAdder = itemView.findViewById(R.id.tv_adder);
            imgMedic = itemView.findViewById(R.id.img_medic);
        }
    }
}
