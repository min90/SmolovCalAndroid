package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import jmsdevelopment.smolovcal.R;

/**
 * Created by Jesper on 13/07/2016.
 */

public class WorkoutJuniorCycleFragment extends Fragment {
    private TextView txtDay1WeightWeek1, txtDay2WeightWeek1, txtDay3WeightWeek1, txtDay4WeightWeek1;
    private TextView txtDay1WeightWeek2, txtDay2WeightWeek2, txtDay3WeightWeek2, txtDay4WeightWeek2;
    private TextView txtDay1WeightWeek3, txtDay2WeightWeek3, txtDay3WeightWeek3, txtDay4WeightWeek3;
    private CheckBox chcBoxWeek1Day1, chcBoxWeek1Day2, chcBoxWeek1Day3, chcBoxWeek1Day4;
    private CheckBox chcBoxWeek2Day1, chcBoxWeek2Day2, chcBoxWeek2Day3, chcBoxWeek2Day4;
    private CheckBox chcBoxWeek3Day1, chcBoxWeek3Day2, chcBoxWeek3Day3,chcBoxWeek3Day4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_junior_cycle, container, false);

        //Week 1
        txtDay1WeightWeek1 = (TextView) view.findViewById(R.id.txtDay1Weight1);
        txtDay2WeightWeek1 = (TextView) view.findViewById(R.id.txtDay2Weight1);
        txtDay3WeightWeek1 = (TextView) view.findViewById(R.id.txtDay3Weight1);
        txtDay4WeightWeek1 = (TextView) view.findViewById(R.id.txtDay4Weight1);
        chcBoxWeek1Day1 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day1);
        chcBoxWeek1Day2 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day2);
        chcBoxWeek1Day3 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day3);
        chcBoxWeek1Day4 = (CheckBox) view.findViewById(R.id.chkBoxWeek1Day4);

        //Week 2
        txtDay1WeightWeek2 = (TextView) view.findViewById(R.id.txtDay1Weight2);
        txtDay2WeightWeek2 = (TextView) view.findViewById(R.id.txtDay2Weight2);
        txtDay3WeightWeek2 = (TextView) view.findViewById(R.id.txtDay3Weight2);
        txtDay4WeightWeek2 = (TextView) view.findViewById(R.id.txtDay4Weight2);
        chcBoxWeek2Day1 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day1);
        chcBoxWeek2Day2 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day2);
        chcBoxWeek2Day3 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day3);
        chcBoxWeek2Day4 = (CheckBox) view.findViewById(R.id.chkBoxWeek2Day4);

        //Week 3
        txtDay1WeightWeek3 = (TextView) view.findViewById(R.id.txtDay1Weight3);
        txtDay2WeightWeek3 = (TextView) view.findViewById(R.id.txtDay2Weight3);
        txtDay3WeightWeek3 = (TextView) view.findViewById(R.id.txtDay3Weight3);
        txtDay4WeightWeek3 = (TextView) view.findViewById(R.id.txtDay4Weight3);
        chcBoxWeek3Day1 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day1);
        chcBoxWeek3Day2 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day2);
        chcBoxWeek3Day3 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day3);
        chcBoxWeek3Day4 = (CheckBox) view.findViewById(R.id.chkBoxWeek3Day4);

        return view;
    }

    private void saveProgressOfWorkout(View view) {
        if (((CheckBox) view).isChecked()) {
            Toast.makeText(getActivity(), "View id: " + view.getId(), Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "View id: " + chcBoxWeek1Day1, Toast.LENGTH_LONG).show();
        }
    }

}
