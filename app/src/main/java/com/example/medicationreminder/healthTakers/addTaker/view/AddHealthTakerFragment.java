package com.example.medicationreminder.healthTakers.addTaker.view;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
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
import com.example.medicationreminder.healthTakers.addTaker.presenter.AddTakerPresenter;
import com.example.medicationreminder.healthTakers.addTaker.presenter.AddTakerPresenterInterface;
import com.example.medicationreminder.model.Request;
import com.example.medicationreminder.model.Medication;
import com.example.medicationreminder.model.Repository;
import com.example.medicationreminder.register.view.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddHealthTakerFragment extends Fragment implements AddTakerInterface {
    FragmentAddHealthTakerBinding binding;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static int REQUEST_COUNTER = 0;
    AddTakerPresenterInterface addTakerPresenterInterface;
    List<Medication> medications;
    ProgressDialog progressDialog;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String senderEmail;

    public AddHealthTakerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medications = new ArrayList<>();
        OnBackPressedCallback pressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(getView()).navigate(R.id.homeFragment2);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, pressedCallback);
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
        progressDialog = new ProgressDialog(getContext());
        addTakerPresenterInterface = new AddTakerPresenter(getContext(), Repository.getRepository(getContext(), FirebaseConnection.getFirebaseConnection(), ConcereteLocalSource.getInstance(getContext())), this);
        addTakerPresenterInterface.getMedicList(getViewLifecycleOwner());
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
                    //  shawingDialog();
                    preferences = getContext().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE);
                    senderEmail = preferences.getString(RegisterActivity.EMAIL, "anoynmous@gmail.com");
                    if(senderEmail.equals(binding.emailEt.getText().toString())){
                        Toast.makeText(getContext(), "you can't send invitation to your self!", Toast.LENGTH_SHORT).show();
                    }else{
                        addTakerPresenterInterface.checkUser(binding.emailEt.getText().toString());
                    }
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

    private void shawingDialog() {
        progressDialog.setTitle("Cheacking Email");
        progressDialog.setMessage("Please wait while we are cheacking this email in our system.");
        progressDialog.show();
        //progressDialog.set
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);
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

    //=======================================================
    @Override
    public void getMedics(List<Medication> medics) {
        //get the list from db by presenter and push it
        medications = medics;
    }

    @Override
    public void onSuccess(boolean result) {
        if (result) {//exist , installed the app
            addTakerPresenterInterface.addRequest(binding.emailEt.getText().toString());
            Toast.makeText(getContext(), "Invitation Sent Successfully", Toast.LENGTH_SHORT).show();
        }else{
            //intent to  send by email
          //  Log.e("TAG", "onSuccess: ", );
            Toast.makeText(getContext(), " user not found ", Toast.LENGTH_SHORT).show();
        }
    }
    //=======================================================
}