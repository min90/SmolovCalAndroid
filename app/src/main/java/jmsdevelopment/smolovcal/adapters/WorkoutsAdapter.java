package jmsdevelopment.smolovcal.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.model.Workout;

/**
 * Created by Jesper on 05/07/2016.
 */

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder> {
    private static final String TAG = WorkoutsAdapter.class.getSimpleName();
    private List<Workout> workoutList = new ArrayList<>();

    public WorkoutsAdapter() {
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_workout, parent, false);
        return new WorkoutViewHolder(view);
    }

    public void clear() {
        workoutList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Workout> workouts) {
        workoutList.addAll(workouts);
        Log.d(TAG, "Workouts: " + workouts.toString());
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.txtExercise.setText(workout.getExercise());
        holder.txtMax.setText(String.valueOf(workout.getMax()));
        holder.txtJrSr.setText(String.valueOf(workout.isFull()));
        holder.txtProgress.setText(String.valueOf(10));

    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtExercise, txtMax, txtJrSr, txtProgress;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            txtExercise = (TextView) itemView.findViewById(R.id.txtExercise);
            txtMax = (TextView) itemView.findViewById(R.id.txtMax);
            txtJrSr = (TextView) itemView.findViewById(R.id.txtJrSr);
            txtProgress = (TextView) itemView.findViewById(R.id.txtProgress);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
