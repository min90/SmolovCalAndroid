package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import jmsdevelopment.smolovcal.FragmentController;
import jmsdevelopment.smolovcal.MainActivity;
import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.SharedPreferencesManager;

/**
 * Created by Jesper on 01/07/2016.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnLogOn;
    private EditText edtEmail;
    private EditText edtPassword;
    private CheckBox chkRememerMe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.lockDrawer();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        chkRememerMe = (CheckBox) view.findViewById(R.id.chkRememberMe);
        btnLogOn = (Button) view.findViewById(R.id.btnLogOn);
        btnLogOn.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "User is logged in");
                } else {
                    Log.d(TAG, "User is logget out");
                }
            }
        };
        return view;
    }

    private void passLoginCredentials() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        SharedPreferencesManager.get().setRememberMe(chkRememerMe.isChecked());
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                proceedToStartScreen();
                MainActivity.unlockDrawer();
                Log.d(TAG, "Log in completed");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void proceedToStartScreen() {
        edtEmail.setText("");
        edtPassword.setText("");
        FragmentController.get().transactFragments(getActivity(), new MainFragment(), "main_fragment");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnLogOn.getId()) {
            passLoginCredentials();
        }
    }
}
