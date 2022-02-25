package com.example.medicationreminder.medications.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.medications.model.MedicsModel;
import com.example.medicationreminder.medications.view.MedicsOnClick;

import com.example.medicationreminder.model.Medication;

import java.util.List;

public class MedicsAdapter extends RecyclerView.Adapter<MedicsAdapter.MedicsViewHolder> implements MedicsOnClick {
    Context context;
    List<MedicsModel> medics;
    ItemAdapter itemAdapter;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public MedicsAdapter(Context context, List<MedicsModel> list) {
        this.context = context;
        this.medics = list;
    }

    @NonNull
    @Override
    public MedicsAdapter.MedicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medic_rv, parent, false);
        return new MedicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicsViewHolder holder, int position) {
        MedicsModel model = medics.get(position);

        holder.tvTitle.setText(model.getTitle());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context.getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setInitialPrefetchItemCount(model.getList().size());
       // itemAdapter =new ItemAdapter(context,model.getList(),this);
        holder.rvMedicItem.setLayoutManager(linearLayoutManager);
        holder.rvMedicItem.setAdapter(itemAdapter);
        //holder.rvMedicItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return medics.size();
    }


    @Override
    public void ItemOnClick(Medication medic) {

    }


    //====================================================================
    public class MedicsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvMedicItem;

        public MedicsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_active);
            rvMedicItem = itemView.findViewById(R.id.rv_activ);


        }
    }
}
