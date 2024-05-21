package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import jakarta.persistence.*;

@Entity
@Table(name = "strength_exercise_type")
public class StrengthExerciseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "muscle_group")
    private MuscleGroup muscleGroup;

    public StrengthExerciseType() { }

    public StrengthExerciseType(String name, MuscleGroup muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup;
    }

    @Override
    public String toString() {
        return "StrengthExerciseType{name='%s', muscleGroup=%s}".formatted(name, muscleGroup);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
