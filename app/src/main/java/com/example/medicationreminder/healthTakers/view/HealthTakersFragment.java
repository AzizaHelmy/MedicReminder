package com.example.medicationreminder.healthTakers.view;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.FragmentHealthTakersBinding;
import com.example.medicationreminder.model.Request;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class HealthTakersFragment extends Fragment implements TakerOnClick {
    FragmentHealthTakersBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Request> recyclerOptions;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request, HealthTakerViewHolder> firebaseRecyclerAdapter;
    SharedPreferences sharedPreferences;
    String myEmail;
    public HealthTakersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //===================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHealthTakersBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        return view;
    }

    //================================================================
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_healthTakersFragment_to_addHealthTakerFragment);
            }
        });
    }

    //====================================================================
    private void init() {
        sharedPreferences = getActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE);
        myEmail = sharedPreferences.getString(RegisterActivity.EMAIL, "anoynmous@gmail.com");
        Log.e("TAG", "myEmail: "+myEmail);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Request");
        Query query = databaseReference.orderByChild("reciverEmail").equalTo(myEmail);
        recyclerOptions = new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(query, Request.class)
                .build();
        layoutManager = new LinearLayoutManager(getActivity());
        binding.rvTaker.setLayoutManager(layoutManager);
        binding.rvTaker.setHasFixedSize(true);

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Request, HealthTakerViewHolder>(recyclerOptions) {

            @Override
            protected void onBindViewHolder(@NonNull HealthTakerViewHolder holder, int position, @NonNull Request model) {
                holder.tvName.setText(model.getSenderName());
                Glide.with(getContext()).load(model.getSenderImg()).placeholder(R.drawable.profileuser).into(holder.imgSender);
                //holder.imgSender.setImageResource(Integer.parseInt(model.getSenderImg()));
                Log.e("TAG", "onBindViewHolder: " + model.getSenderName());
                Log.e("TAG", "Medication: " + model.getMedication().get(1).getMedicine_Name());
                Toast.makeText(getContext(), "Medic " + model.getMedication().get(0).getMedicine_Name(), Toast.LENGTH_SHORT).show();
            }

//            @Override
//            public int getItemViewType(int position) {
//                return firebaseRecyclerAdapter.getItemCount();
//            }

            @NonNull
            @Override
            public HealthTakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_taker_item, null);
                // itemBinding = HealthTakerItemBinding.inflate(LayoutInflater.from(parent.getContext()));
                return new HealthTakerViewHolder(view);
            }
        };
        binding.rvTaker.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onOkClicked(Request taker, ImageView view) {
        Toast.makeText(getContext(), "" + taker.getSenderName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelClicked(Request taker, ImageView view) {
        Toast.makeText(getContext(), "" + taker.getReciverEmail(), Toast.LENGTH_SHORT).show();
    }

    //========================================================================
}

class HealthTakerViewHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    ImageView imgSender;

    public HealthTakerViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        imgSender=itemView.findViewById(R.id.profile_image);
        //itemBinding = HealthTakerItemBinding.bind(itemView);
    }
}
