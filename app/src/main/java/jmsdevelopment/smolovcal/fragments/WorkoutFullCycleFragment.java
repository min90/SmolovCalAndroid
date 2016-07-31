package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.model.Workout;

/**
 * Created by Jesper on 13/07/2016.
 */

public class WorkoutFullCycleFragment extends Fragment implements View.OnClickListener {
    private static final String WORKOUT_TAG = "workout";
    private static final String PROGRESS_TAG = "progress";

    private BottomSheetBehavior mBottomSheetBehaviour;

    public static WorkoutFullCycleFragment newInstance(Workout workout) {
        WorkoutFullCycleFragment fragment = new WorkoutFullCycleFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORKOUT_TAG, workout);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_full_cycle, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View bottomSheet = view.findViewById(R.id.bottom_sheet);

        mBottomSheetBehaviour = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehaviour.setPeekHeight(150);
        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onClick(View v) {

    }
}
