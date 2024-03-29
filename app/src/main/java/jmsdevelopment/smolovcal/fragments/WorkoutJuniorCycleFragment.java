package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedHashMap;

import jmsdevelopment.smolovcal.FragmentController;
import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.SharedPreferencesManager;
import jmsdevelopment.smolovcal.model.Workout;

/**
 * Created by Jesper on 13/07/2016.
 */

public class WorkoutJuniorCycleFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = WorkoutJuniorCycleFragment.class.getSimpleName();
    private static final String WORKOUT_TAG = "workout";
    private static final String PROGRESS_TAG = "progress";

    private TextView txtDay1WeightWeek1, txtDay2WeightWeek1, txtDay3WeightWeek1, txtDay4WeightWeek1;
    private TextView txtDay1WeightWeek2, txtDay2WeightWeek2, txtDay3WeightWeek2, txtDay4WeightWeek2;
    private TextView txtDay1WeightWeek3, txtDay2WeightWeek3, txtDay3WeightWeek3, txtDay4WeightWeek3;
    private CheckBox chcBoxWeek1Day1, chcBoxWeek1Day2, chcBoxWeek1Day3, chcBoxWeek1Day4;
    private CheckBox chcBoxWeek2Day1, chcBoxWeek2Day2, chcBoxWeek2Day3, chcBoxWeek2Day4;
    private CheckBox chcBoxWeek3Day1, chcBoxWeek3Day2, chcBoxWeek3Day3, chcBoxWeek3Day4;
    private DatabaseReference databaseReference;
    private Workout workoutFromSerialization;
    private LinkedHashMap<Integer, CheckBox> checkBoxLinkedHashMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Integer> checkboxIds = new LinkedHashMap<>();

    public static WorkoutJuniorCycleFragment newInstance(Workout workoutToSerialize) {

        Bundle args = new Bundle();
        args.putSerializable(WORKOUT_TAG, workoutToSerialize);
        WorkoutJuniorCycleFragment fragment = new WorkoutJuniorCycleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_junior_cycle, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Week 1
        txtDay1WeightWeek1 = (TextView) view.findViewById(R.id.txtDay1Weight1);
        txtDay2WeightWeek1 = (TextView) view.findViewById(R.id.txtDay2Weight1);
        txtDay3WeightWeek1 = (TextView) view.findViewById(R.id.txtDay3Weight1);
        txtDay4WeightWeek1 = (TextView) view.findViewById(R.id.txtDay4Weight1);
        chcBoxWeek1Day1 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day1);
        chcBoxWeek1Day1.setOnCheckedChangeListener(this);
        chcBoxWeek1Day2 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day2);
        chcBoxWeek1Day2.setOnCheckedChangeListener(this);
        chcBoxWeek1Day3 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day3);
        chcBoxWeek1Day3.setOnCheckedChangeListener(this);
        chcBoxWeek1Day4 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day4);
        chcBoxWeek1Day4.setOnCheckedChangeListener(this);

        //Week 2
        txtDay1WeightWeek2 = (TextView) view.findViewById(R.id.txtDay1Weight2);
        txtDay2WeightWeek2 = (TextView) view.findViewById(R.id.txtDay2Weight2);
        txtDay3WeightWeek2 = (TextView) view.findViewById(R.id.txtDay3Weight2);
        txtDay4WeightWeek2 = (TextView) view.findViewById(R.id.txtDay4Weight2);
        chcBoxWeek2Day1 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day1);
        chcBoxWeek2Day1.setOnCheckedChangeListener(this);
        chcBoxWeek2Day2 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day2);
        chcBoxWeek2Day2.setOnCheckedChangeListener(this);
        chcBoxWeek2Day3 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day3);
        chcBoxWeek2Day3.setOnCheckedChangeListener(this);
        chcBoxWeek2Day4 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day4);
        chcBoxWeek2Day4.setOnCheckedChangeListener(this);

        //Week 3
        txtDay1WeightWeek3 = (TextView) view.findViewById(R.id.txtDay1Weight3);
        txtDay2WeightWeek3 = (TextView) view.findViewById(R.id.txtDay2Weight3);
        txtDay3WeightWeek3 = (TextView) view.findViewById(R.id.txtDay3Weight3);
        txtDay4WeightWeek3 = (TextView) view.findViewById(R.id.txtDay4Weight3);
        chcBoxWeek3Day1 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day1);
        chcBoxWeek3Day1.setOnCheckedChangeListener(this);
        chcBoxWeek3Day2 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day2);
        chcBoxWeek3Day2.setOnCheckedChangeListener(this);
        chcBoxWeek3Day3 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day3);
        chcBoxWeek3Day3.setOnCheckedChangeListener(this);
        chcBoxWeek3Day4 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day4);
        chcBoxWeek3Day4.setOnCheckedChangeListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkboxIds.putAll(mapOfCheckboxIDAndProgress());
        checkBoxLinkedHashMap.putAll(putCheckboxesInCheckboxMap());

        Bundle args = getArguments();
        workoutFromSerialization = (Workout) args.getSerializable(WORKOUT_TAG);
        String workoutTitle = workoutFromSerialization == null ? "Workout" : "Workout " + workoutFromSerialization.getExercise();
        FragmentController.get().setToolbarTitle(workoutTitle);
        int progress = workoutFromSerialization == null ? 0 : workoutFromSerialization.getProgress();
        initialLockingOfCheckboxes(progress);

        calculateWeightIncrease(workoutFromSerialization);
        assignProgressOfWorkoutAfterViewIsCreated();
    }

    private LinkedHashMap<Integer, CheckBox> putCheckboxesInCheckboxMap() {
        LinkedHashMap<Integer, CheckBox> checkboxesFromView = new LinkedHashMap<>();
        checkboxesFromView.put(1, chcBoxWeek1Day1);
        checkboxesFromView.put(2, chcBoxWeek1Day2);
        checkboxesFromView.put(3, chcBoxWeek1Day3);
        checkboxesFromView.put(4, chcBoxWeek1Day4);
        checkboxesFromView.put(5, chcBoxWeek2Day1);
        checkboxesFromView.put(6, chcBoxWeek2Day2);
        checkboxesFromView.put(7, chcBoxWeek2Day3);
        checkboxesFromView.put(8, chcBoxWeek2Day4);
        checkboxesFromView.put(9, chcBoxWeek3Day1);
        checkboxesFromView.put(10, chcBoxWeek3Day2);
        checkboxesFromView.put(11, chcBoxWeek3Day3);
        checkboxesFromView.put(12, chcBoxWeek3Day4);
        return checkboxesFromView;
    }

    private LinkedHashMap<Integer, Integer> mapOfCheckboxIDAndProgress() {
        LinkedHashMap<Integer, Integer> checkboxIds = new LinkedHashMap<>();
        checkboxIds.put(chcBoxWeek1Day1.getId(), 1);
        checkboxIds.put(chcBoxWeek1Day2.getId(), 2);
        checkboxIds.put(chcBoxWeek1Day3.getId(), 3);
        checkboxIds.put(chcBoxWeek1Day4.getId(), 4);
        checkboxIds.put(chcBoxWeek2Day1.getId(), 5);
        checkboxIds.put(chcBoxWeek2Day2.getId(), 6);
        checkboxIds.put(chcBoxWeek2Day3.getId(), 7);
        checkboxIds.put(chcBoxWeek2Day4.getId(), 8);
        checkboxIds.put(chcBoxWeek3Day1.getId(), 9);
        checkboxIds.put(chcBoxWeek3Day2.getId(), 10);
        checkboxIds.put(chcBoxWeek3Day3.getId(), 11);
        checkboxIds.put(chcBoxWeek3Day4.getId(), 12);
        return checkboxIds;
    }

    private void calculateWeightIncrease(Workout workoutFromSerialization) {
        if (workoutFromSerialization != null) {
            double weightIncreaseSeventyPercent = 0.70;
            double weightIncreaseSeventyFivePercent = 0.75;
            double weightIncreaseEightyPercent = 0.80;
            double weightIncreaseEightyFivePercent = 0.85;

            int userChosenMaxWeight = workoutFromSerialization.getMax();
            int userChosenIncrease = workoutFromSerialization.getIncrement();

            double week1Day1Weight = userChosenMaxWeight * weightIncreaseSeventyPercent;
            double week1Day2Weight = userChosenMaxWeight * weightIncreaseSeventyFivePercent;
            double week1Day3Weight = userChosenMaxWeight * weightIncreaseEightyPercent;
            double week1Day4Weight = userChosenMaxWeight * weightIncreaseEightyFivePercent;

            double week2Day1Weight = userChosenIncrease + week1Day1Weight;
            double week2Day2Weight = userChosenIncrease + week1Day2Weight;
            double week2Day3Weight = userChosenIncrease + week1Day3Weight;
            double week2Day4Weight = userChosenIncrease + week1Day4Weight;

            double week3Day1Weight = week2Day1Weight + userChosenIncrease;
            double week3Day2Weight = week2Day2Weight + userChosenIncrease;
            double week3Day3Weight = week2Day3Weight + userChosenIncrease;
            double week3Day4Weight = week2Day4Weight + userChosenIncrease;

            //Week 1
            txtDay1WeightWeek1.setText(String.valueOf(week1Day1Weight));
            txtDay2WeightWeek1.setText(String.valueOf(week1Day2Weight));
            txtDay3WeightWeek1.setText(String.valueOf(week1Day3Weight));
            txtDay4WeightWeek1.setText(String.valueOf(week1Day4Weight));

            //Week 2
            txtDay1WeightWeek2.setText(String.valueOf(week2Day1Weight));
            txtDay2WeightWeek2.setText(String.valueOf(week2Day2Weight));
            txtDay3WeightWeek2.setText(String.valueOf(week2Day3Weight));
            txtDay4WeightWeek2.setText(String.valueOf(week2Day4Weight));

            //Week 3
            txtDay1WeightWeek3.setText(String.valueOf(week3Day1Weight));
            txtDay2WeightWeek3.setText(String.valueOf(week3Day2Weight));
            txtDay3WeightWeek3.setText(String.valueOf(week3Day3Weight));
            txtDay4WeightWeek3.setText(String.valueOf(week3Day4Weight));
        }
    }

    private void updateCheckboxes(int progress) {
        for (int i = 0; i <= progress && progress <= checkBoxLinkedHashMap.size(); i++) {
            if (checkBoxLinkedHashMap.get(i) != null) {
                checkBoxLinkedHashMap.get(i).setChecked(true);
            }
        }
    }

    private void initialLockingOfCheckboxes(int progress) {
        Log.d(TAG, "Progress: " + progress);
        for (int i = progress + 2; i <= checkBoxLinkedHashMap.size(); i++) {
            if (checkBoxLinkedHashMap.get(i) != null) {
                checkBoxLinkedHashMap.get(i).setEnabled(false);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Log.d(TAG, "Checked true");
            saveProgress(buttonView);
        } else {
            undoProgress(buttonView);
            Log.d(TAG, "Checked false");
        }
    }

    private void saveProgress(View view) {
        LinkedHashMap<Integer, Integer> checkBoxIds = mapOfCheckboxIDAndProgress();
        if (checkBoxIds.containsKey(view.getId())) {
            if (workoutFromSerialization != null) {
                int progress = checkBoxIds.get(view.getId());
                updateFirebaseNode(progress);
                unlockCheckBoxAccordingToProgress(progress);
            }
        }
    }

    private void undoProgress(View view) {
        LinkedHashMap<Integer, Integer> checkBoxIds = mapOfCheckboxIDAndProgress();
        if (workoutFromSerialization != null) {
            int progressToUndo = checkBoxIds.get(view.getId());
            if (progressToUndo == 1) {
                updateProgressAfterUndo(0);
                lockCheckBoxAfterUndoOfProgress(progressToUndo + 1);
            } else {
                updateProgressAfterUndo(progressToUndo);
                lockCheckBoxAfterUndoOfProgress(progressToUndo + 1);
            }
        }
    }

    private void updateProgressAfterUndo(int progressToUndo) {
        for (int i = progressToUndo; i <= checkBoxLinkedHashMap.size(); i++) {
            if (checkBoxLinkedHashMap.get(i) != null) {
                checkBoxLinkedHashMap.get(i).setChecked(false);
                updateFirebaseNode(progressToUndo);
            }
        }
    }

    private void updateFirebaseNode(int progress) {
        databaseReference.child(SharedPreferencesManager.get().getUserId())
                .child(workoutFromSerialization.getFirebaseKey())
                .child(PROGRESS_TAG)
                .setValue(progress);
    }

    private void assignProgressOfWorkoutAfterViewIsCreated() {
        if (workoutFromSerialization != null) {
            int progress = workoutFromSerialization.getProgress();
            updateCheckboxes(progress);
            unlockCheckBoxAccordingToProgress(progress);
        }
    }

    private void lockCheckBoxAfterUndoOfProgress(int progress) {
        if (progress <= checkBoxLinkedHashMap.size()) {
            if (checkBoxLinkedHashMap.get(progress) != null) {
                checkBoxLinkedHashMap.get(progress).setEnabled(false);
            }
        }
    }

    private void unlockCheckBoxAccordingToProgress(int progress) {
        int newCheckboxToUnlockAtPosition = progress + 1;
        if (newCheckboxToUnlockAtPosition <= checkBoxLinkedHashMap.size()) {
            if (checkBoxLinkedHashMap.get(newCheckboxToUnlockAtPosition) != null) {
                checkBoxLinkedHashMap.get(newCheckboxToUnlockAtPosition).setEnabled(true);
            }
        }
    }
}
