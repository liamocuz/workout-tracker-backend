package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.WeightInfo;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents the saved values for a Strength Exercise in a Workout Template
 */
@Entity
@Table(name = "workout_strength_exercise_value")
public class StrengthExerciseValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private WeightInfo weightInfo;

    @ManyToOne
    @JoinColumn(name = "strength_exercise_id")
    private StrengthExercise strengthExercise;

    public StrengthExerciseValue() { }

    public StrengthExerciseValue(WeightInfo weightInfo, StrengthExercise strengthExercise) {
        this.weightInfo = weightInfo;
        this.strengthExercise = strengthExercise;
    }

    @Override
    public String toString() {
        return "StrengthExerciseValue{" +
                "id=" + id +
                ", weightInfo=" + weightInfo +
                ", strengthExercise=" + strengthExercise.getName() +
                '}';
    }

    /**
     * Uses Vlad Mihalcea's idea for equals
     * When id is null, we can ONLY guarantee equality for the same
     * object references. Otherwise, no transient object is equal to any
     * other transient or persisted object.
     * @param o other object
     * @return if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StrengthExerciseValue value = (StrengthExerciseValue) o;
        return id != null && Objects.equals(getId(), value.getId());
    }

    /**
     * Uses Vlad Mihalcea's idea for hashCode
     * All get put in same bucket, but do not need to rely on id
     * Fine for performance since we will never fetch a large amount of these
     * for the performance to be an impact
     * @return the hashcode value
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
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
