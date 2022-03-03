package com.example.medicationreminder.healthTakers.patientMedics;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.FragmentMedicationsBinding;
import com.example.medicationreminder.databinding.FragmentPatientMedicsListBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.medications.presenter.MedicationPresenter;
import com.example.medicationreminder.medications.presenter.MedicsPresenter;
import com.example.medicationreminder.medications.view.MedicsInterface;
import com.example.medicationreminder.medications.view.MedicsOnClick;
import com.example.medicationreminder.medications.view.adapter.MedicsAdapter;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;

import java.util.List;


public class PatientMedicsListFragment extends Fragment implements MedicsOnClick, MedicsInterface {
    FragmentPatientMedicsListBinding binding;
    PatientMedicsAdapter adapterMedics;
    List<Medication> list;
    MedicsPresenter medicsPresenter;
    ImageView empty;

    public PatientMedicsListFragment() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPatientMedicsListBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        if (list != null) {
            setUpRecyclerView();
        }
        cheackMedic(list);
        empty = view.findViewById(R.id.empty_iv_patient);
        medicsPresenter = new MedicationPresenter(getContext(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
        medicsPresenter.getMedics(getViewLifecycleOwner());
        return view;
    }

    //==================================================================
    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        binding.rvMedicsPatient.setLayoutManager(layoutManager);
        Log.e("TAG", "showMedics: " + list);
        Bundle args = getArguments();
        String name = args.getString("medo");
        Log.e("TAG", "show me  " + name);
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        if (args != null) {
            list = (List<Medication>) args.getSerializable("Medics");
        }
        adapterMedics = new PatientMedicsAdapter(getContext(), list, this, Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())));
        binding.rvMedicsPatient.setAdapter(adapterMedics);
    }

    //========================================================================
    @Override
    public void showMedics(List<Medication> medics) {

    }

    @Override
    public void ItemOnClick(Medication medic) {

    }

    @Override
    public void alarmOnClick(Medication medic, ImageView view) {

    }

    //=======================================================================
    private void cheackMedic(List<Medication> medications) {
        if (medications.isEmpty()) {
            binding.rvMedicsPatient.setVisibility(View.GONE);
            binding.emptyIvPatient.setVisibility(View.VISIBLE);
        } else {
            binding.rvMedicsPatient.setVisibility(View.GONE);
            binding.emptyIvPatient.setVisibility(View.VISIBLE);
        }
    }
}