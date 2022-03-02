package com.example.medicationreminder.healthTakers.request.view;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.FragmentHealthTakersBinding;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Request;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RequestFragment extends Fragment implements TakerOnClick {
    FragmentHealthTakersBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Request> recyclerOptions;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request, RequestViewHolder> firebaseRecyclerAdapter;
    SharedPreferences sharedPreferences;
    String myEmail;
    Request requests = new Request();
    ConstraintLayout requsetLayout;
    List<Request> requestList = new ArrayList<>();

    public RequestFragment() {
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
        //cheackEmptyRequests(requestList);
        start();
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_healthTakersFragment_to_addHealthTakerFragment);
            }
        });
    }

    //====================================================================
    private void start() {
        sharedPreferences = getActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE);
        myEmail = sharedPreferences.getString(RegisterActivity.EMAIL, "anoynmous@gmail.com");
        Log.e("TAG", "myEmail: " + myEmail);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Request");
        Query query = databaseReference.orderByChild("reciverEmail").equalTo(myEmail);
        setUpRecyclerView(query);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Request, RequestViewHolder>(recyclerOptions) {

            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Request model) {
                // requestList= (List<Request>) model;
                holder.tvName.setText(model.getSenderName());
                holder.imgAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //onOkClicked(model, holder.imgAccept);
                        List<Medication>medications=model.getMedication();
                        Toast.makeText(getContext(), "Name " + model.getMedication().get(0).getMedicine_Name(), Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("medo",model.getMedication().get(0).getMedicine_Name());
                        bundle.putSerializable("Medics", (Serializable) medications);

                        Navigation.findNavController(view).navigate(R.id.action_healthTakersFragment_to_patientMedicsListFragment,bundle);
                        Log.e("TAG", "Medic Name: " + model.getMedication().get(0).getMedicine_Name());
                    }
                });
                holder.imgCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onOkClicked(model, holder.imgCancel);
                        Log.e("TAG", "onCanceldClick: ");
                        Toast.makeText(getContext(), "Del", Toast.LENGTH_SHORT).show();
//                        Snackbar snackbar = Snackbar.make(requsetLayout, "Ignor Invitation!", Snackbar.LENGTH_LONG);
//                        snackbar.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
//                        snackbar.setActionTextColor(ContextCompat.getColor(getContext(), R.color.blue));
//                        snackbar.setBackgroundTint(ContextCompat.getColor(getContext(), R.color.purple_500));
//                        snackbar.setAction("undo", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                //repository.insert(movie);
//                               // Toast.makeText(FavMovieActivity.this, "added Again!", Toast.LENGTH_SHORT).show();
//                              // firebaseRecyclerAdapter.getRef(position).get();
//                            }
//                        }).show();

                        firebaseRecyclerAdapter.getRef(position).removeValue();
                    }
                });
                Glide.with(getContext()).load(model.getSenderImg()).placeholder(R.drawable.profileuser).into(holder.imgSender);
                Log.e("TAG", "onBindViewHolder: " + model.getSenderName());
                // Log.e("TAG", "Medication: " + model.getMedication().get(1).getMedicine_Name());
            }

            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_taker_item, null);
                return new RequestViewHolder(view);
            }
        };
        binding.rvTaker.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    //==========================================================
    private void setUpRecyclerView(Query query) {
        recyclerOptions = new FirebaseRecyclerOptions.Builder<Request>()
                .setQuery(query, Request.class)
                .build();
        // layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        DividerItemDecoration decoration = new DividerItemDecoration(binding.rvTaker.getContext(), DividerItemDecoration.VERTICAL);
        binding.rvTaker.addItemDecoration(decoration);
        binding.rvTaker.setLayoutManager(layoutManager);
        binding.rvTaker.setHasFixedSize(true);
    }

    //==============================================================
    @Override
    public void onOkClicked(Request request, ImageView view) {
        //go to all medics and show medics//flag?
        //requests = request;
        // Toast.makeText(getContext(), "" + requests.getMedication().get(0).getMedicine_Name(), Toast.LENGTH_SHORT).show();
    }

    //==============================================================
    @Override
    public void onCancelClicked(Request taker, ImageView view) {
        //delet the item
        //firebaseRecyclerAdapter.getRef()
    }

    //=================================================================
    private void cheackEmptyRequests(List<Request> requests) {
        if (requests.isEmpty()) {
            binding.rvTaker.setVisibility(View.GONE);
            binding.lottieMain.setVisibility(View.VISIBLE);
        } else {
            binding.rvTaker.setVisibility(View.VISIBLE);
            binding.lottieMain.setVisibility(View.GONE);
        }
    }
}

class RequestViewHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    ImageView imgSender, imgCancel, imgAccept;

    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        imgSender = itemView.findViewById(R.id.profile_image);
        imgAccept = itemView.findViewById(R.id.img_ok);
        imgCancel = itemView.findViewById(R.id.img_cancel);
        //itemBinding = HealthTakerItemBinding.bind(itemView);
    }
}
