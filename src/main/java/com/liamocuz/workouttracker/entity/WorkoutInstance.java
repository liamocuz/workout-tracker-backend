package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.WorkoutFeeling;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout_instance")
public class WorkoutInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant endTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "feeling", nullable = false)
    private WorkoutFeeling feeling;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(optional = true)
    @JoinColumn(name = "template_id")
    private Workout workout;

    @ManyToMany
    @JoinTable(
            name = "workout_instance_join_strength_exercise_instance",
            joinColumns = @JoinColumn(name = "workout_instance_id"),
            inverseJoinColumns = @JoinColumn(name = "strength_exercise_instance_id")
    )
    private Set<StrengthExerciseInstance> strengthExerciseInstances;

    public WorkoutInstance() { }

    public WorkoutInstance(Long userId, Instant startTime, Instant endTime, WorkoutFeeling feeling, String notes, Workout workout) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.feeling = feeling;
        this.notes = notes;
        this.workout = workout;
        this.strengthExerciseInstances = new HashSet<>();
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

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public WorkoutFeeling getFeeling() {
        return feeling;
    }

    public void setFeeling(WorkoutFeeling feeling) {
        this.feeling = feeling;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Workout getWorkoutTemplate() {
        return workout;
    }

    public void setWorkoutTemplate(Workout workout) {
        this.workout = workout;
    }

    public Set<StrengthExerciseInstance> getStrengthExerciseInstances() {
        return strengthExerciseInstances;
    }

    public void setStrengthExerciseInstances(Set<StrengthExerciseInstance> strengthExerciseInstances) {
        this.strengthExerciseInstances = strengthExerciseInstances;
    }

    public void addStrengthExerciseInstance(StrengthExerciseInstance exerciseInstance) {
        this.strengthExerciseInstances.add(exerciseInstance);
    }

    public void removeStrengthExerciseInstance(StrengthExerciseInstance exerciseInstance) {
        this.strengthExerciseInstances.remove(exerciseInstance);
    }
}
