package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.WeightInfo;
import jakarta.persistence.*;

/**
 * Represents the saved values for a Strength Exercise in a Workout Template
 */
@Entity
@Table(name = "workout_strength_exercise_value")
public class WorkoutStrengthExerciseValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private WeightInfo weightInfo;

    @ManyToOne
    @JoinColumn(name = "strength_exercise_id")
    private StrengthExercise strengthExercise;

    public WorkoutStrengthExerciseValue() { }

    public WorkoutStrengthExerciseValue(WeightInfo weightInfo, StrengthExercise strengthExercise) {
        this.weightInfo = weightInfo;
        this.strengthExercise = strengthExercise;
    }

    @Override
    public String toString() {
        return "WorkoutTemplateStrengthExerciseValue{id=%d, weightInfo=%s, strengthExercise=%s}".formatted(id, weightInfo, strengthExercise);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeightInfo getWeightInfo() {
        return weightInfo;
    }

    public void setWeightInfo(WeightInfo weightInfo) {
        this.weightInfo = weightInfo;
    }

    public StrengthExercise getStrengthExercise() {
        return strengthExercise;
    }

    public void setStrengthExercise(StrengthExercise strengthExercise) {
        this.strengthExercise = strengthExercise;
    }
}
