package com.example.medicationreminder.displayMedics.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenter;
import com.example.medicationreminder.addmedication.presenter.AddMedicationPresenterInterface;
import com.example.medicationreminder.databinding.FragmentAddMedicationBinding;
import com.example.medicationreminder.databinding.FragmentDisplayMedicineBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.displayMedics.presenter.DisplayMedicationPresenter;
import com.example.medicationreminder.displayMedics.presenter.DisplayMedicationPresenterInterface;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.io.Serializable;
import java.util.List;


public class displayMedicineFragment extends Fragment implements DisplayMedicationViewInterface {
    DisplayMedicationPresenterInterface displayMedicationPresenterInterface;
    Medication medication;
    String [] times;
    String [] doses;
    FragmentDisplayMedicineBinding binding;

    public displayMedicineFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//       displayMedicationPresenterInterface =new DisplayMedicationPresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())),this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDisplayMedicineBinding.inflate(getLayoutInflater(), container, false);
        displayMedicationPresenterInterface = new DisplayMedicationPresenter(getActivity(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);

        View view = binding.getRoot();
        //========================================
//        times[0]=binding.timer1.getText().toString();
//        times[1]=binding.timer2.getText().toString();
//        times[2]=binding.timer3.getText().toString();
//        //===========================================
//        doses[0]=binding.number1.getText().toString();
//        doses[1]=binding.number2.getText().toString();
//        doses [2]=binding.number3.getText().toString();
        //============================================
        displayData();
        binding.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                displayMedicationPresenterInterface.deleteMedic(medication);
            }
        });
        //================================================
        binding.editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //Medication clicedItem=new Medication(binding.medicineTxt.getText().toString(),binding.strengthTxt.getText().toString(),binding.duration.getText().toString(),binding.drugAmount.getText().toString(),binding.leftdrug.getText().toString());
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("edite", (Serializable) clicedItem);
                //Navigation.findNavController(view).navigate(R.id.action_displayMedicineFragment_to_updateMedicationFragment,bundle);
            }
        });
        return view;


    }

    @Override
    public LiveData<List<Medication>> displayDrug() {
        return displayMedicationPresenterInterface.displayDrug(displayMedicineFragment.this);
    }

    public void displayData() {
        Bundle args = getArguments();
        if (args != null) {
             medication = (Medication) args.getSerializable("medic");
            binding.medicineTxt.setText(medication.getMedicine_Name());
            binding.strengthTxt.setText(medication.getStrength());
           // binding.duration.setText(medication.getDuration());
            binding.medicineIcon.setId(medication.getIcon());
            binding.drugAmount.setText(medication.getDrugAmount());
            binding.leftdrug.setText(medication.getLeftDrug());
//         binding.number1.setText(medication.getNoOfDose()[0]);
//         binding.number2.setText(medication.getNoOfDose()[1]);
//            binding.number2.setText(medication.getNoOfDose()[2]);
            binding.timer1.setText(medication.getDrugs()[0]);
            binding.timer2.setText(medication.getDrugs()[1]);
            binding.timer3.setText(medication.getDrugs()[2]);

        }


    }


}