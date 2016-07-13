package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import jmsdevelopment.smolovcal.FragmentController;
import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.adapters.WorkoutsAdapter;
import jmsdevelopment.smolovcal.model.Workout;

/**
 * Created by Jesper on 30/06/2016.
 */

public class MainFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = MainFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private WorkoutsAdapter workoutsAdapter;
    private DatabaseReference database;
    private FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout refreshWorkouts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabNewWorkout);
        refreshWorkouts = (SwipeRefreshLayout) view.findViewById(R.id.refreshWorkouts);
        refreshWorkouts.setOnRefreshListener(this);
        floatingActionButton.setOnClickListener(this);
        database = FirebaseDatabase.getInstance().getReference();
        setUpRecyclerView(view);
        return view;
    }

    private void setUpRecyclerView(View view) {
        workoutsAdapter = new WorkoutsAdapter();
        workouts();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewWorkouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(workoutsAdapter);
    }

    private List<Workout> workouts() {
        final List<Workout> workoutList = new ArrayList<>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Count: " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "Snapshot: " + dataSnapshot.toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Workout workout = snapshot.getValue(Workout.class);
                    workoutList.add(workout);
                    refreshWorkouts.setRefreshing(false);
                }
                workoutsAdapter.clear();
                workoutsAdapter.addAll(workoutList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                refreshWorkouts.setRefreshing(false);
                Log.e(TAG, "Read failed", databaseError.toException());
            }
        });
        return workoutList;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == floatingActionButton.getId()) {
            FragmentController.get().transactFragments(getActivity(),
                    NewWorkoutFragment.newInstance(floatingActionButton.getX(),
                            floatingActionButton.getY()), "fragment_workout_new");
        }
    }

    @Override
    public void onRefresh() {
        workouts();
    }
}
