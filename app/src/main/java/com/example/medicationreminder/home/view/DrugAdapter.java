package com.example.medicationreminder.home.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.R;
import com.example.medicationreminder.model.Medication;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ItemViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Medication> itemList;

    DrugAdapter(List<Medication> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drag_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
//  Drug item = itemList.get(i);
//        itemViewHolder.tvItemTitle.setText(item.getItemTitle());
//
//        Log.e(TAG, "onBindViewHolder: "+item.getItemTitle());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(
//                itemViewHolder.rvSubItem.getContext(),
//                LinearLayoutManager.VERTICAL,
//                false
//        );
//        layoutManager.setInitialPrefetchItemCount(item.getSubItemList().size());
//
//     SubItemAdapter subItemAdapter = new SubItemAdapter(item.getSubItemList());
//
//        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
//        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
//        itemViewHolder.rvSubItem.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvSubItem;
        private TextView tvItemTitle;

        ItemViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);

            rvSubItem = itemView.findViewById(R.id.rv_sub_item);
        }
    }
}
