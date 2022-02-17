package com.example.medicationreminder.medications.view;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//class MySection extends Section {
//    List<String> itemList = Arrays.asList("Active", "InActive");
//    List<ItemModel> medics=new ArrayList<>();
//
//    @SuppressLint("ResourceType")
//    public MySection() {
//        // call constructor with layout resources for this Section header and items
//        super(SectionParameters.builder()
//                .itemResourceId(R.layout.medic_item_rv)
//                .headerResourceId(R.layout.header_rv)
//                .build());
//    }
//
//    @Override
//    public int getContentItemsTotal() {
//        return itemList.size(); // number of items of this section
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder getItemViewHolder(View view) {
//        // return a custom instance of ViewHolder for the items of this section
//        return new MyItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
//        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
//        ItemModel model = medics.get(position);
//
//        // bind your view here
//        //itemHolder.imgMedic.setImageResource(Integer.parseInt(itemList.get(position)));
//        itemHolder.tvMedicName.setText(model.getMedicName());
//        itemHolder.tvMedicStrength.setText(model.getMedicStrength());
//        itemHolder.tvMedicLeft.setText(model.getMedicLeft());
//        itemHolder.tvMedicAdder.setText(model.getMedicAdder());
//        // itemHolder.buttSuspend.setText(model);
//
//
//        // itemHolder.tvItem.setText(itemList.get(position));
//    }
//
//    @Override
//    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
//        // return an empty instance of ViewHolder for the headers of this section
//        return new EmptyViewHolder(view);
//    }
//
//    //===========================================
//    class MyItemViewHolder extends RecyclerView.ViewHolder {
//        //private final TextView tvItem;
//        TextView tvMedicName, tvMedicStrength, tvMedicLeft, tvMedicAdder;
//        Button buttSuspend;
//        ImageView imgMedic;
//
//        public MyItemViewHolder(View itemView) {
//            super(itemView);
//            tvMedicName = itemView.findViewById(R.id.tv_medic_name);
//            tvMedicStrength = itemView.findViewById(R.id.tv_strngth);
//            tvMedicLeft = itemView.findViewById(R.id.tv_refill);
//            tvMedicAdder = itemView.findViewById(R.id.tv_adder);
//            buttSuspend = itemView.findViewById(R.id.button);
//            imgMedic = itemView.findViewById(R.id.img_medic);
//            //  tvItem = (TextView) itemView.findViewById(R.id.tvItem);
//        }
//    }
//}
