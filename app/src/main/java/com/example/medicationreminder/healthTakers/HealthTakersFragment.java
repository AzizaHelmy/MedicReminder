package com.example.medicationreminder.healthTakers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.FragmentHealthTakersBinding;
import com.example.medicationreminder.healthTakers.model.HealthTaker;
import com.example.medicationreminder.healthTakers.view.TakerOnClick;
import com.example.medicationreminder.model.Patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HealthTakersFragment extends Fragment implements TakerOnClick {
    FragmentHealthTakersBinding binding;
    HealthTakerAdapter adapter;
    List<HealthTaker> takerList = new ArrayList<>();
    //FirebaseRecyclerAdapter adapter;

    public HealthTakersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Patient patient = dataSnapshot.getValue(Patient.class);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //===================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHealthTakersBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        setUpRecycler();
        // getdataSource();
        cheackHealthTaker(takerList);
        adapter = new HealthTakerAdapter(takerList, getContext(), this);
        binding.rvTaker.setAdapter(adapter);
        return view;
    }

    private void cheackHealthTaker(List<HealthTaker> healthTakers) {
        if (healthTakers.isEmpty()) {
            binding.rvTaker.setVisibility(View.GONE);
            binding.lottieMain.setVisibility(View.VISIBLE);
        } else {
            binding.rvTaker.setVisibility(View.VISIBLE);
            binding.lottieMain.setVisibility(View.GONE);
        }
    }

    private void setUpRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rvTaker.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(binding.rvTaker.getContext(),
                layoutManager.getOrientation());
        binding.rvTaker.addItemDecoration(decoration);
    }

    private void getdataSource() {
        takerList.add(new HealthTaker("Azzoza", R.drawable.profile2));
        takerList.add(new HealthTaker("Azozay", R.drawable.profile1));
        takerList.add(new HealthTaker("Azozaaaa", R.drawable.profile2));
    }

    //================================================================
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_healthTakersFragment_to_addHealthTakerFragment);
            }
        });
    }

    @Override
    public void onOkClicked(HealthTaker taker, ImageView view) {
        Toast.makeText(getContext(), "" + taker.getTakerName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelClicked(HealthTaker taker, ImageView view) {
        Toast.makeText(getContext(), "" + taker.getTakerImg(), Toast.LENGTH_SHORT).show();
    }
}