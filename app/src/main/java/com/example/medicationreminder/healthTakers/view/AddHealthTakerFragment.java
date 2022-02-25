package com.example.medicationreminder.healthTakers.view;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicationreminder.Network.FirebaseConnection;
import com.example.medicationreminder.R;
import com.example.medicationreminder.databinding.FragmentAddHealthTakerBinding;
import com.example.medicationreminder.db.ConcereteLocalSource;
import com.example.medicationreminder.healthTakers.presenter.HealthTakerPresenter;
import com.example.medicationreminder.healthTakers.presenter.HealthTakerPresenterInterface;
import com.example.medicationreminder.model.Request;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddHealthTakerFragment extends Fragment implements HealthTakersInterface {
    FragmentAddHealthTakerBinding binding;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    SharedPreferences sharedPreferences;
    String senderName;
    String senderEmail;
    String reciverEmail;
    String senderImg;
    HealthTakerPresenterInterface healthTakerPresenter;

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
        healthTakerPresenter = new HealthTakerPresenter(getContext(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
        //insert();
        healthTakerPresenter.getAllMedics(getViewLifecycleOwner());
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

                    Toast.makeText(getActivity(), "find", Toast.LENGTH_SHORT).show();
                    sharedPreferences = getActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE);
                    senderName = sharedPreferences.getString(RegisterActivity.USER_NAME, "anoynmous");
                    senderEmail = sharedPreferences.getString(RegisterActivity.EMAIL, "anoynmous@gmail.com");
                    reciverEmail = binding.emailEt.getText().toString();
                    senderImg = "https://firebasestorage.googleapis.com/v0/b/profile-34740.appspot.com/o/shopImages%2Fshopcover1.jpg?alt=media";
                    Log.e("TAG", "onClick: " + reciverEmail);
                    List<Medication> medications = new ArrayList<>();
                    medications.add(new Medication("Tuskkan"));
                    medications.add(new Medication("Reyt"));
                    medications.add(new Medication("posdtr"));

                    Request healthTake = new Request(senderName, reciverEmail, senderEmail, senderImg, medications);
                    FirebaseDatabase.getInstance().getReference("Request").push().setValue(healthTake);
                    Toast.makeText(getContext(), "Invitation Sent Successfully", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_addHealthTakerFragment_to_healthTakersFragment);


                    // }else{//not a user
                    //dialoge to ask the user to send an email to him

                    //}
                    //  }

                } else {

                }
            }
            //dialoge =>progress to user
            //cheack:
            //if this email find in firebase(query in user )=>send invitation
            //else:
            //this user is not exist do you need to send invitation to him by email?

        });
    }

    //================================================================
    private boolean cheackUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("user").child("userEmail")
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
        return true;
    }

    @Override
    public void getMedics(List<Medication> medics) {

    }

    @Override
    public void insert() {

    }
}