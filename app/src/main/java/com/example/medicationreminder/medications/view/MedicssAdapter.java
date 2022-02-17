//package com.example.medicationreminder.medications.view;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//
//import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
//import com.example.medicationreminder.R;
//
//import java.util.HashMap;
//import java.util.List;
//
//
//public class MedicsAdapter extends SectionedRecyclerViewAdapter<MedicsAdapter.MedicsViewHolder>{
//
//    HashMap<String, List<ItemModel>> medicsList = new HashMap<>();
//    List<String> sectionList;
//    Context context;
//    Activity activity;
//    int selectedSection = -1;
//    int selectedItem = -1;
//    public final static int MEDIC_STATUS_ACTIVE = 0;
//    public final static int MEDIC_STATUS_INACTIVE = 1;
//
//    public MedicsAdapter(HashMap<String, List<ItemModel>> medicslList, List<String> sections, Activity activity) {
//        this.medicsList = medicslList;
//        sectionList = sections;
//        this.activity=activity;
//    }
//
//    @Override
//    public int getSectionCount() {
//        return sectionList.size();
//    }
//
//    @Override
//    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
//        if (section == 1) {
//            return 0;
//        }
//        return super.getItemViewType(section, relativePosition, absolutePosition);
//    }
//
//    @Override
//    public int getItemCount(int section) {
//        return medicsList.get(sectionList.get(section)).size();
//    }
//
//    @Override
//    public void onBindHeaderViewHolder(MedicsViewHolder holder, int section) {
//
//
//        holder.tvMedicName.setText(sectionList.get(section));
//        holder.tvMedicAdder.setText(sectionList.get(section));
//        holder.tvMedicLeft.setText(sectionList.get(section));
//        holder.tvMedicStrength.setText(sectionList.get(section));
//        // holder.imgMedic.setImageResource(sectionList.get(section));
//    }
//
//    @Override
//    public void onBindViewHolder(MedicsViewHolder holder, int section, int relativePosition, int absolutePosition) {
//        ItemModel model = (ItemModel) medicsList.get(sectionList.get(section));
//        holder.tvMedicName.setText(model.getMedicName());
//        holder.tvMedicAdder.setText(model.getMedicAdder());
//        holder.tvMedicLeft.setText(model.getMedicLeft());
//        holder.tvMedicStrength.setText(model.getMedicStrength());
//        holder.imgMedic.setImageResource(model.getMedicImg());
//        holder.buttSuspend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Active", Toast.LENGTH_SHORT).show();
//                selectedSection = section;
//                selectedItem = relativePosition;
//                notifyDataSetChanged();
//            }
//        });
//        if(selectedSection==section && selectedItem== relativePosition){
//
//        }
//    }
//
//    @Override
//    public MedicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        int layout;
//        if (viewType == VIEW_TYPE_HEADER) {
//            layout = R.layout.header_rv;
//        } else {
//            layout = R.layout.medic_item_rv;
//        }
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
//        return new MedicsViewHolder(view);
//    }
//
//    //====================================================================
//    public class MedicsViewHolder extends RecyclerView.ViewHolder {
//        TextView tvMedicName, tvMedicStrength, tvMedicLeft, tvMedicAdder;
//        Button buttSuspend;
//        ImageView imgMedic;
//
//        public MedicsViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvMedicName = itemView.findViewById(R.id.tv_medic_name);
//            tvMedicStrength = itemView.findViewById(R.id.tv_strngth);
//            tvMedicLeft = itemView.findViewById(R.id.tv_refill);
//            tvMedicAdder = itemView.findViewById(R.id.tv_adder);
//            buttSuspend = itemView.findViewById(R.id.button);
//            imgMedic = itemView.findViewById(R.id.img_medic);
//        }
//    }
////========================================================================
//
//
//}
