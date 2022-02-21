package com.example.medicationreminder.healthTakers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.databinding.HealthTakerItemBinding;
import com.example.medicationreminder.healthTakers.model.HealthTaker;
import com.example.medicationreminder.healthTakers.view.TakerOnClick;

import java.util.List;

public class HealthTakerAdapter extends RecyclerView.Adapter<HealthTakerAdapter.HealthTakerViewHolder> {
    HealthTakerItemBinding binding;
    List<HealthTaker> takerList;
    Context context;
    TakerOnClick onClick;

    public HealthTakerAdapter(List<HealthTaker> takerList, Context context, TakerOnClick onClick) {
        this.takerList = takerList;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public HealthTakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HealthTakerItemBinding.inflate(LayoutInflater.from(parent.getContext()));

        return new HealthTakerViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HealthTakerViewHolder holder, int position) {
        HealthTaker healthTaker = takerList.get(position);
        //binding.profileImage.setImageResource(healthTaker.getTakerImg());
        binding.tvName.setText(healthTaker.getTakerName());
        binding.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onOkClicked(healthTaker,binding.imgCancel);
            }
        });
        binding.imgOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onCancelClicked(healthTaker,binding.imgOk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return takerList.size();
    }

    //==========================================
    public class HealthTakerViewHolder extends RecyclerView.ViewHolder {


        public HealthTakerViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HealthTakerItemBinding.bind(itemView);
        }
    }
}
