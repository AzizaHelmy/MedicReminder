package com.example.medicationreminder.medications.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.medications.model.ItemModel;
import com.example.medicationreminder.medications.model.MedicsModel;
import com.example.medicationreminder.medications.view.MedicsOnClick;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MedicsTypeViewHolder> {
    Context context;
    List<ItemModel> medics;
    MedicsOnClick onClick;

    public ItemAdapter(Context context, List<ItemModel> medics,MedicsOnClick onClick) {
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
        ItemModel model = medics.get(position);
        holder.tvMedicName.setText(model.getMedicName());
        holder.tvMedicAdder.setText(model.getMedicAdder());
        holder.tvMedicLeft.setText(model.getMedicLeft());
        holder.tvMedicStrength.setText(model.getMedicStrength());
        holder.imgMedic.setImageResource(model.getMedicImg());
    }

    @Override
    public int getItemCount() {
        return medics.size();
    }

    //===============================================
    public class MedicsTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicName, tvMedicStrength, tvMedicLeft, tvMedicAdder;
        Button buttSuspend;
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
