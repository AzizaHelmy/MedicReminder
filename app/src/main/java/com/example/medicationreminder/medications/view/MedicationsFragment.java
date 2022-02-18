package com.example.medicationreminder.medications.view;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.example.medicationreminder.BaseFragment;
import com.example.medicationreminder.R;

import com.example.medicationreminder.medications.model.MedicsModel;
import com.example.medicationreminder.medications.view.adapter.MedicsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.medicationreminder.model.Drug;

public class MedicationsFragment extends BaseFragment implements MedicsOnClick ,MedicsInterface{
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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_medications;
    }

    @Override
    public void initializeViews(View view) {
        buttAdd = view.findViewById(R.id.butt_add);
        rvMedics = view.findViewById(R.id.rv_medics);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMedics.setLayoutManager(linearLayoutManager);
        adapterMedics = new MedicsAdapter(getContext(), buildItemList());
        list = buildItemList();

        rvMedics.setAdapter(adapterMedics);
    }

    private List<MedicsModel> buildItemList() {
        itemList = new ArrayList<>();

        item = new MedicsModel("Active ", buildSubItemList());
        itemList.add(item);
        itemList.add(new MedicsModel("INActive ", buildSubItemList()));

        return itemList;
    }

    private List<Drug> buildSubItemList() {
        subItemList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            subItem = new Drug(R.drawable.img_medicine, "panadol", "1000 gm", "2 left", "Added by Asmaa");
            subItemList.add(new Drug(R.drawable.pill, "Tusskan", "500 gm", "1 left", " Added by Azza"));
           // subItemList.add(new ItemModel(R.drawable.pillsbottle, "kohol", "500 gm", "0 left", "Added by salma"));
            subItemList.add(subItem);
        }
        return subItemList;
    }

    @Override
    public void setListeners() {
        buttAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_medicationsFragment_to_addMedicationFragment);
//                findNavController(fragment).navigate(
//                        SignInFragmentDirections.actionSignInFragmentToUserNameFragment())

            }
        });
    }

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
    public void ItemOnClick(Drug model) {
        subItemList.remove(model);
        adapterMedics.notifyDataSetChanged();
    }

    @Override
    public void showMedics(List<MedicsModel> movies) {

    }
}

