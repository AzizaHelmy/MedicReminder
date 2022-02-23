package com.example.medicationreminder.healthTakers.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.FragmentAddHealthTakerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddHealthTakerFragment extends Fragment {
    FragmentAddHealthTakerBinding binding;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private String email;

    public AddHealthTakerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //===============================================================
    private boolean cheachEmail() {
        boolean flag = false;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(binding.emailEt.getText());
        if (binding.emailEt.getText().toString().isEmpty()) {
            binding.emailEt.setError("Email Can't be empty !");
            flag = false;
        } else if (!matcher.find()) {
            binding.emailEt.setError("Wrong Email Format !");
            flag = false;
        } else {
            binding.emailEt.setError("");
            flag = true;
        }
        return flag;
    }

    //================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddHealthTakerBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        return view;
    }

    //================================================================
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (cheachEmail()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    database.getReference("Patient").child("HealthTakers")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dsp : snapshot.getChildren()) {
                                        if (dsp.getValue().equals(binding.emailEt.getText().toString())) {
                                            Toast.makeText(getActivity(), "find", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                    // FirebaseDatabase.getInstance().getReference().push().setValue(email, FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    Navigation.findNavController(view).navigate(R.id.action_addHealthTakerFragment_to_healthTakersFragment);
                    Toast.makeText(getContext(), "Invitation Sent Successfully", Toast.LENGTH_SHORT).show();

                }else{

                }
                //if this email find in firebase
                //send model to it(username , drugs,img)
                //1- get this model from db
                //2- write it
                //so i need to store this data first on firebase!!
            }
        });
    }

    //================================================================
    private boolean cheackUser() {
        final boolean[] flag = {false};
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("Patient").child("HealthTakers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dsp : snapshot.getChildren()) {
                            if (dsp.getValue().equals(binding.emailEt.getText().toString())) {
                                Toast.makeText(getActivity(), "find", Toast.LENGTH_SHORT).show();
                                flag[0] = true;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return flag[0];
    }
}