package jmsdevelopment.smolovcal.model;

import com.orm.SugarRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jesper on 04/07/2016.
 */

public class Workout {
    private String exercise;
    private int progress;
    private boolean junior;
    private boolean full;
    private int increment;
    private int max;

    public Workout() {
    }

    public Workout(String exercise, int progress, boolean junior, boolean full, int max) {
        this.exercise = exercise;
        this.progress = progress;
        this.junior = junior;
        this.full = full;
        this.max = max;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isJunior() {
        return junior;
    }

    public void setJunior(boolean junior) {
        this.junior = junior;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean senior) {
        this.full = senior;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("exercise", exercise);
        result.put("increment", increment);
        result.put("one_rep_max", max);
        result.put("junior", junior);
        result.put("full", full);
        return result;
    }
}
