package com.liamocuz.workouttracker.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class WeightInfo {
    private float weight;
    private int sets;
    private int reps;

    public WeightInfo() { }

    public WeightInfo(float weight, int sets, int reps) {
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    @Override
    public String toString() {
        return "WeightInfo{weight=%s, sets=%d, reps=%d}".formatted(weight, sets, reps);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
