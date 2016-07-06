package jmsdevelopment.smolovcal.model;

import com.orm.SugarRecord;

/**
 * Created by Jesper on 04/07/2016.
 */

public class Workout extends SugarRecord {
    private String exercise;
    private int progress;
    private boolean junior;
    private boolean senior;
    private int max;

    public Workout() {
    }

    public Workout(String exercise, int progress, boolean junior, boolean senior, int max) {
        this.exercise = exercise;
        this.progress = progress;
        this.junior = junior;
        this.senior = senior;
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

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
