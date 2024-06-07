package com.liamocuz.workouttracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "strength_exercise")
public class StrengthExercise extends Exercise {
    @Embedded
    private WeightInfo weightInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_type_id")
    private StrengthExerciseType exerciseType;

    public StrengthExercise() { }

    public StrengthExercise(Long userId, String name, String description, WeightInfo weightInfo, StrengthExerciseType exerciseType) {
        super(userId, name, description);
        this.weightInfo = weightInfo;
        this.exerciseType = exerciseType;
    }

    @Override
    public String toString() {
        return "StrengthExercise{weightInfo=%s, exerciseType=%s} %s".formatted(weightInfo, exerciseType, super.toString());
    }

    public WeightInfo getWeightInfo() {
        return weightInfo;
    }

    public void setWeightInfo(WeightInfo weightInfo) {
        this.weightInfo = weightInfo;
    }

    public StrengthExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(StrengthExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }
}
