package jmsdevelopment.smolovcal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import jmsdevelopment.smolovcal.FragmentController;
import jmsdevelopment.smolovcal.MainActivity;
import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.Util;

/**
 * Created by Jesper on 02/07/2016.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = RegisterFragment.class.getSimpleName();

    private Button btnRegister;
    private TextInputLayout tipRegisterEmail;
    private TextInputLayout tipRegisterPassword;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        tipRegisterEmail = (TextInputLayout) view.findViewById(R.id.tipRegisterEmail);
        tipRegisterPassword = (TextInputLayout) view.findViewById(R.id.tipRegisterPassword);
        mAuth = FirebaseAuth.getInstance();
        setUpTextInputLayout();

        return view;
    }

    private void setUpTextInputLayout() {
        tipRegisterEmail.setHint("Email");
        tipRegisterPassword.setHint("password");
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @SuppressWarnings("nullpointerexception")
    private void passCredentialsForRegister() {
        String email;
        String password;
        try {
            email = tipRegisterEmail.getEditText().getText().toString();
            password = tipRegisterPassword.getEditText().getText().toString();

            if (!Util.get().validateEmail(email)) {
                tipRegisterEmail.setError("Not a valid email address!");
            }
            if (!Util.get().validatePassword(password)) {
                tipRegisterPassword.setError("Not a valid password!");
            }
            if (Util.get().validateEmail(email) && Util.get().validatePassword(password)) {
                tipRegisterEmail.setErrorEnabled(true);
                tipRegisterPassword.setErrorEnabled(false);
                hideKeyboard();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User created", Toast.LENGTH_LONG).show();
                            FragmentController.get().transactFragments(getActivity(), new MainFragment(), "main_fragment");
                            MainActivity.unlockDrawer();
                        } else if (!task.isSuccessful()) {
                            Log.d(TAG, "User: " + task.getResult().toString());
                            Toast.makeText(getActivity(), "User creation failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        } catch (NullPointerException ex) {
            Log.e(TAG, "Edittext is not present in textinputlayout: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnRegister.getId()) {
            passCredentialsForRegister();
        }
    }
}
