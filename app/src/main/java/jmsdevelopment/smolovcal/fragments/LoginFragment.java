package jmsdevelopment.smolovcal.fragments;

import android.app.ProgressDialog;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import jmsdevelopment.smolovcal.FragmentController;
import jmsdevelopment.smolovcal.MainActivity;
import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.SharedPreferencesManager;
import jmsdevelopment.smolovcal.Util;
import jmsdevelopment.smolovcal.model.User;

/**
 * Created by Jesper on 01/07/2016.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnLogOn;
    private TextInputLayout tipSignInEmail;
    private TextInputLayout tipSignInPassword;
    private CheckBox cheRememberMe;
    private Button btnNewRegister;
    private ProgressDialog lgnProgressBar;

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
        tipSignInEmail = (TextInputLayout) view.findViewById(R.id.tipSignInEmail);
        tipSignInPassword = (TextInputLayout) view.findViewById(R.id.tipSignInPassword);
        cheRememberMe = (CheckBox) view.findViewById(R.id.chkRememberMe);
        btnLogOn = (Button) view.findViewById(R.id.btnLogOn);
        btnLogOn.setOnClickListener(this);
        btnNewRegister = (Button) view.findViewById(R.id.btnNewRegister);
        btnNewRegister.setOnClickListener(this);
        setUpTextInputLayout();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    User.getCurrentUser().setEmail(user.getEmail());
                    SharedPreferencesManager.get().setUserId(User.getCurrentUser().getUserID());
                    //Kan bruge dette til at tjekke om vi er på eller ej.
                    Log.d(TAG, "User is logged in");
                } else {
                    Log.d(TAG, "User is logged out");
                }
            }
        };
        return view;
    }

    private void setUpProgressBar() {
        lgnProgressBar = new ProgressDialog(getActivity());
        lgnProgressBar.setMessage("Signing in");
        lgnProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        lgnProgressBar.setCancelable(true);
        try {
            lgnProgressBar.show();
        } catch (WindowManager.BadTokenException ex){
            Log.e(TAG, "Activty might be destroyed", ex);
        }
    }

    private void passLoginCredentials() {
        String email;
        String password;
        try {
            email = tipSignInEmail.getEditText().getText().toString();
            password = tipSignInPassword.getEditText().getText().toString();

            if (!Util.get().validateEmail(email)) {
                tipSignInEmail.setError("Not a valid email address!");
            }
            if (!Util.get().validatePassword(password)) {
                tipSignInPassword.setError("Not a valid password!");
            }
            if (Util.get().validateEmail(email) && Util.get().validatePassword(password)) {
                tipSignInEmail.setErrorEnabled(false);
                tipSignInPassword.setErrorEnabled(false);
                hideKeyboard();
                setUpProgressBar();

                SharedPreferencesManager.get().setRememberMe(cheRememberMe.isChecked());
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            proceedToStartScreen();
                            MainActivity.unlockDrawer();
                            Log.d(TAG, "Log in completed");
                        } else if (!task.isSuccessful()) {
                            Log.d(TAG, "Login failed");
                            MainActivity.lockDrawer();
                            tipSignInEmail.setError("Please make sure you enter an correct email");
                            tipSignInPassword.setError("Please make sure you enter a correct password.");
                        }
                    }
                });
            }
        } catch (NullPointerException ex) {
            Log.e(TAG, "Edittext is not present in textinputlayout: " + ex.getLocalizedMessage());
        }
    }

    private void setUpTextInputLayout() {
        tipSignInEmail.setHint("Email");
        tipSignInPassword.setHint("password");
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
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

    private void proceedToStartScreen() {
        try {
            tipSignInEmail.getEditText().setText("");
            tipSignInPassword.getEditText().setText("");
            if (lgnProgressBar.isShowing()) {
                lgnProgressBar.dismiss();
            }
        } catch (NullPointerException ex) {
            Log.e(TAG, "Unable to set text", ex);
        }
        FragmentController.get().transactFragments(getActivity(), new MainFragment(), "main_fragment");
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnLogOn.getId()) {
            passLoginCredentials();
        }
        if (v.getId() == btnNewRegister.getId()) {
            FragmentController.get().transactFragments(getActivity(), new RegisterFragment(), "register_fragment");
        }
    }
}
