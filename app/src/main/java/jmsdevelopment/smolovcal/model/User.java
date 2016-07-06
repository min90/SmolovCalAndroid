package jmsdevelopment.smolovcal.model;

import com.orm.SugarRecord;

/**
 * Created by Jesper on 04/07/2016.
 */

public class User extends SugarRecord {
    private String name;
    private int age;
    private Workout cycles;

    public User(String name, Workout cycles, int age) {
        this.name = name;
        this.cycles = cycles;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Workout getCycles() {
        return cycles;
    }

    public void setCycles(Workout cycles) {
        this.cycles = cycles;
    }
}
