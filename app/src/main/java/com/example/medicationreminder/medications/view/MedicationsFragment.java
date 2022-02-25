package com.example.medicationreminder.medications.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.example.medicationreminder.R;

import com.example.medicationreminder.medications.model.MedicsModel;
import com.example.medicationreminder.medications.view.adapter.MedicsAdapter;

import java.util.ArrayList;
import java.util.List;
import com.example.medicationreminder.model.Drug;
import com.example.medicationreminder.model.Medication;

public class MedicationsFragment extends Fragment implements MedicsOnClick ,MedicsInterface{
    Button buttAdd;
    RecyclerView rvMedics;
    MedicsAdapter adapterMedics;
    List<MedicsModel> list = new ArrayList<>();
    Drug subItem;
    MedicsModel item;
    List<Drug> subItemList;
    List<MedicsModel> itemList;

    public MedicationsFragment() {
        // Required empty public constructor
    }



    private List<MedicsModel> buildItemList() {
        itemList = new ArrayList<>();

//        item = new MedicsModel("Active ", buildSubItemList());
//        itemList.add(item);
//        itemList.add(new MedicsModel("INActive ", buildSubItemList()));

        return itemList;
    }

//    private List<Medication> buildSubItemList() {
//        subItemList = new ArrayList<>();
//        for (int i = 1; i <= 2; i++) {
//           // subItem = new Medication(R.drawable.img_medicine, "panadol", "1000 gm", "2 left", "Added by Asmaa");
//           // subItemList.add(new Drug(R.drawable.pill, "Tusskan", "500 gm", "1 left", " Added by Azza"));
//           // subItemList.add(new ItemModel(R.drawable.pillsbottle, "kohol", "500 gm", "0 left", "Added by salma"));
//            subItemList.add(subItem);
//        }
//        return subItemList;
//    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback pressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigate(R.id.homeFragment2);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, pressedCallback);
    }

    @Override
    public void ItemOnClick(Medication model) {
        subItemList.remove(model);
        adapterMedics.notifyDataSetChanged();
    }

    @Override
    public void showMedics(List<MedicsModel> movies) {

    }
}

