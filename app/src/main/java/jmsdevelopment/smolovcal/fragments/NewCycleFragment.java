package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.SharedPreferencesManager;
import jmsdevelopment.smolovcal.model.User;
import jmsdevelopment.smolovcal.model.Workout;

/**
 * Created by Jesper on 11/07/2016.
 */

public class NewCycleFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = NewCycleFragment.class.getSimpleName();
    private static final String CYCLE_TAG = "Which_cycle";

    private Button btnSave;
    private Button btnCancel;
    private TextInputLayout tipExercise;
    private TextInputLayout tipOneRepMax;
    private TextInputLayout tipIncrement;
    private boolean fullCycle = false;
    private DatabaseReference databaseReference;

    public static NewCycleFragment newInstance(int cycle) {

        Bundle args = new Bundle();
        args.putInt(CYCLE_TAG, cycle);
        NewCycleFragment fragment = new NewCycleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_new, container, false);
        TextView txtCycle = (TextView) view.findViewById(R.id.txtCycle);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        tipExercise = (TextInputLayout) view.findViewById(R.id.txtIPExercise);
        tipOneRepMax = (TextInputLayout) view.findViewById(R.id.txtIPOneRepMax);
        tipIncrement = (TextInputLayout) view.findViewById(R.id.txtIPIncrement);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle args = getArguments();

        int cycle = args.getInt(CYCLE_TAG);
        if(cycle == 0) {
            txtCycle.setText(getString(R.string.dialog_info_text));
        } else if(cycle == 1) {
            txtCycle.setText(getString(R.string.dialog_info_full_text));
            fullCycle = true;
        }

        return view;
    }

    private void fetchExercise() {
        try {
            if (tipExercise.getEditText().getText().toString().matches("")) {
                tipExercise.setError("You did not provide an exercise");
            } else {
                tipExercise.setErrorEnabled(false);
            }

            if (!tipIncrement.getEditText().getText().toString().matches("[0-9]+")) {
                tipIncrement.setError("You need to provide a number for an increment");
            } else {
                tipIncrement.setErrorEnabled(false);
            }

            if (!tipOneRepMax.getEditText().getText().toString().matches("[0-9]+")) {
                tipOneRepMax.setError("You need to provide a number for a one rep max");
            } else {
                tipOneRepMax.setErrorEnabled(false);
            }
            Log.d(TAG, "before save");
            if(!tipExercise.getEditText().toString().matches("") &&
                    tipOneRepMax.getEditText().getText().toString().matches("[0-9]+") &&
                    tipIncrement.getEditText().getText().toString().matches("[0-9]+")) {
                tipExercise.setErrorEnabled(false);
                tipIncrement.setErrorEnabled(false);
                tipOneRepMax.setErrorEnabled(false);

                Workout workout = new Workout();
                workout.setExercise(tipExercise.getEditText().getText().toString());
                workout.setMax(Integer.parseInt(tipOneRepMax.getEditText().getText().toString()));
                workout.setIncrement(Integer.parseInt(tipIncrement.getEditText().getText().toString()));
                if(fullCycle) {
                    workout.setJunior(false);
                    workout.setFull(true);
                } else {
                    workout.setFull(false);
                    workout.setJunior(true);
                }

                DatabaseReference key = databaseReference.child(SharedPreferencesManager.get().getUserId());
                String child = databaseReference.child("Workout").push().getKey();
                workout.setFirebaseKey(child);
                Map<String, Object> values = workout.toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(child, values);
                Log.d(TAG, "New: " + childUpdates.toString());
                key.updateChildren(childUpdates);
                Log.d(TAG, "Saving to db");
            }
        } catch (NullPointerException ex) {
            Log.e(TAG, "TextInputLayout was null", ex);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnSave.getId()) {
            fetchExercise();
        }

        if(v.getId() == btnCancel.getId()) {
           getFragmentManager().popBackStack();
        }
    }
}
