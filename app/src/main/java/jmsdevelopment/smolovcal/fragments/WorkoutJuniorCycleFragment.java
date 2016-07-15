package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jmsdevelopment.smolovcal.R;

/**
 * Created by Jesper on 13/07/2016.
 */

public class WorkoutJuniorCycleFragment extends Fragment {
    private TextView txtDay1WeightWeek1, txtDay2WeightWeek1, txtDay3WeightWeek1, txtDay4WeightWeek1;
    private TextView txtDay1WeightWeek2, txtDay2WeightWeek2, txtDay3WeightWeek2, txtDay4WeightWeek2;
    private TextView txtDay1WeightWeek3, txtDay2WeightWeek3, txtDay3WeightWeek3, txtDay4WeightWeek3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_junior_cycle, container, false);

        //Week 1
        txtDay1WeightWeek1 = (TextView) view.findViewById(R.id.txtDay1Weight1);
        txtDay2WeightWeek1 = (TextView) view.findViewById(R.id.txtDay2Weight1);
        txtDay3WeightWeek1 = (TextView) view.findViewById(R.id.txtDay3Weight1);
        txtDay4WeightWeek1 = (TextView) view.findViewById(R.id.txtDay4Weight1);

        //Week 2
        txtDay1WeightWeek2 = (TextView) view.findViewById(R.id.txtDay1Weight2);
        txtDay2WeightWeek2 = (TextView) view.findViewById(R.id.txtDay2Weight2);
        txtDay3WeightWeek2 = (TextView) view.findViewById(R.id.txtDay3Weight2);
        txtDay4WeightWeek2 = (TextView) view.findViewById(R.id.txtDay4Weight2);

        //Week 3
        txtDay1WeightWeek3 = (TextView) view.findViewById(R.id.txtDay1Weight3);
        txtDay2WeightWeek3 = (TextView) view.findViewById(R.id.txtDay2Weight3);
        txtDay3WeightWeek3 = (TextView) view.findViewById(R.id.txtDay3Weight3);
        txtDay4WeightWeek3 = (TextView) view.findViewById(R.id.txtDay4Weight3);

        return view;
    }
}
