package jmsdevelopment.smolovcal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import jmsdevelopment.smolovcal.FragmentController;
import jmsdevelopment.smolovcal.R;
import jmsdevelopment.smolovcal.adapters.WorkoutsAdapter;
import jmsdevelopment.smolovcal.model.Workout;

/**
 * Created by Jesper on 30/06/2016.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private List<Workout> workouts = new ArrayList<>();
    private RecyclerView recyclerView;
    private WorkoutsAdapter workoutsAdapter;
    private FirebaseDatabase database;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabNewWorkout);
        floatingActionButton.setOnClickListener(this);
        workouts.add(new Workout("Bench Press", 10, true, false, 200));
        database = FirebaseDatabase.getInstance();
        setUpRecyclerView(view);
        return view;
    }

    private void setUpRecyclerView(View view){
        workoutsAdapter = new WorkoutsAdapter(workouts);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewWorkouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(workoutsAdapter);
    }

//    private List<Workout> retrieveAllWorkouts(){
//        List<Workout> workoutList = null;
//        DatabaseReference databaseReference = database.getReference()
//        return workoutList;
//    }


    @Override
    public void onClick(View v) {
        if(v.getId() == floatingActionButton.getId()) {
        }
    }
}
