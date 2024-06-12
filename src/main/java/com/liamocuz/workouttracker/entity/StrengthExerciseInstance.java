package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.WeightInfo;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "strength_exercise_instance")
public class StrengthExerciseInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant startTime;

    @Embedded
    private WeightInfo weightInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id")
    private StrengthExercise strengthExercise;

    public StrengthExerciseInstance() { }

    public StrengthExerciseInstance(Long userId, Instant startTime, WeightInfo weightInfo, StrengthExercise strengthExercise) {
        this.userId = userId;
        this.startTime = startTime;
        this.weightInfo = weightInfo;
        this.strengthExercise = strengthExercise;
    }

    @Override
    public String toString() {
        return "StrengthExerciseInstance{" +
                "id=" + id +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", weightInfo=" + weightInfo +
                ", strengthExercise='" + strengthExercise.getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StrengthExerciseInstance instance = (StrengthExerciseInstance) o;
        return id != null && Objects.equals(getId(), instance.getId());
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
