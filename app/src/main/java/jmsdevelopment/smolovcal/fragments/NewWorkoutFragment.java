package jmsdevelopment.smolovcal.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import jmsdevelopment.smolovcal.R;

/**
 * Created by Jesper on 06/07/2016.
 */

public class NewWorkoutFragment extends Fragment implements View.OnClickListener {
    private static final String X_TAG = "x";
    private static final String Y_TAG = "y";

    public static NewWorkoutFragment newInstance(float x, float y) {

        Bundle args = new Bundle();
        args.putFloat(X_TAG, x);
        args.putFloat(Y_TAG, y);
        NewWorkoutFragment fragment = new NewWorkoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_junior, container, false);

        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int radius = (int) Math.hypot(right, bottom);

                Bundle args = getArguments();
                float cx = args.getFloat(X_TAG);
                float cy = args.getFloat(Y_TAG);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator reveal = ViewAnimationUtils.createCircularReveal(v, (int) cx, (int) cy, 0, radius);
                    reveal.setInterpolator(new DecelerateInterpolator());
                    reveal.setDuration(600);
                    reveal.start();
                }

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
