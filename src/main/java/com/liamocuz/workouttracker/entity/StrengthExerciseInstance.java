package com.liamocuz.workouttracker.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "strength_exercise_instance")
public class StrengthExerciseInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant startTime;

    @Embedded
    private WeightInfo weightInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id")
    private StrengthExercise strengthExercise;

    public StrengthExerciseInstance() { }

    public StrengthExerciseInstance(Instant startTime, WeightInfo weightInfo, StrengthExercise strengthExercise) {
        this.startTime = startTime;
        this.weightInfo = weightInfo;
        this.strengthExercise = strengthExercise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
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
