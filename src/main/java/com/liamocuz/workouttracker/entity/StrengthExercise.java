package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import jakarta.persistence.*;

@Entity
@Table(name = "strength_exercise")
public class StrengthExercise extends Exercise {
    @Embedded
    private WeightInfo weightInfo;

    @Column(name = "muscle_group")
    @Enumerated(value = EnumType.STRING)
    private MuscleGroup muscleGroup;

    public StrengthExercise() { }

    public StrengthExercise(Long userId, String name, String description, WeightInfo weightInfo, MuscleGroup muscleGroup) {
        super(userId, name, description);
        this.weightInfo = weightInfo;
        this.muscleGroup = muscleGroup;
    }

    @Override
    public String toString() {
        return "StrengthExercise{" +
                "weightInfo=" + weightInfo +
                ", muscleGroup=" + muscleGroup +
                "} " + super.toString();
    }

    public WeightInfo getWeightInfo() {
        return weightInfo;
    }

    public void setWeightInfo(WeightInfo weightInfo) {
        this.weightInfo = weightInfo;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
