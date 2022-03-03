package com.example.medicationreminder.healthTakers.addTaker.view;

import static com.example.medicationreminder.register.view.RegisterActivity.SHARD_NAME;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
                    if (senderEmail.equals(binding.emailEt.getText().toString())) {
                        shawingAlertDialoge(view);
                    } else {
                        addTakerPresenterInterface.checkUser(binding.emailEt.getText().toString());
                    }

                } else {

                }
            }
        });
    }

    //=========================================================================
    private void shawingAlertDialoge(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Warning !!");
        builder.setMessage("you can't send invitation to your self !\n\n Try Again?");
        builder.setIcon(R.drawable.warning);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                binding.emailEt.setText("");
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Navigation.findNavController(view).navigate(R.id.action_addHealthTakerFragment_to_healthTakersFragment);

            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //================================================================
    @Override
    public void getMedics(List<Medication> medics) {
        medications = medics;
    }

    @Override
    public void onSuccess(boolean result) {
        if (result) {//exist , installed the app
            addTakerPresenterInterface.addRequest(binding.emailEt.getText().toString());
            Toast.makeText(getContext(), "Invitation Sent Successfully", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(getView()).navigate(R.id.action_addHealthTakerFragment_to_healthTakersFragment);

        } else {
            shawingDialogeForIntent();
            Toast.makeText(getContext(), " user not found ", Toast.LENGTH_SHORT).show();
        }
    }

    //==================================================
    private void shawingDialogeForIntent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Warning !!");
        builder.setMessage("User not found ..\nDo you need to send The Invitation by Gmail?");
        builder.setIcon(R.drawable.sad);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //intent for gmail
//                final Intent intent = new Intent(Intent.ACTION_VIEW)
//                        .setType("plain/text")
//                        .setData(Uri.parse("test@gmail.com"))
//                        .setPackage("com.google.android.gm")
//                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"test@gmail.com"})
//                        .putExtra(Intent.EXTRA_SUBJECT, "test")
//                        .putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from my demo app :-)");
//                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                binding.emailEt.setText("");
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    //=======================================================
}