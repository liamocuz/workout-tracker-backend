package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.WorkoutFeeling;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "workout_instance")
public class WorkoutInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
    private WorkoutTemplate workoutTemplate;

    @ManyToMany
    @JoinTable(
            name = "workout_instance_join_strength_exercise_instance",
            joinColumns = @JoinColumn(name = "workout_instance_id"),
            inverseJoinColumns = @JoinColumn(name = "strength_exercise_instance_id")
    )
    private Set<StrengthExerciseInstance> strengthExerciseInstanceSet;

    public WorkoutInstance() { }

    public WorkoutInstance(Instant startTime, Instant endTime, WorkoutFeeling feeling, String notes, WorkoutTemplate workoutTemplate) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.feeling = feeling;
        this.notes = notes;
        this.workoutTemplate = workoutTemplate;
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

    public WorkoutTemplate getWorkoutTemplate() {
        return workoutTemplate;
    }

    public void setWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        this.workoutTemplate = workoutTemplate;
    }

    public Set<StrengthExerciseInstance> getStrengthExerciseInstanceSet() {
        return strengthExerciseInstanceSet;
    }

    public void setStrengthExerciseInstanceSet(Set<StrengthExerciseInstance> strengthExerciseInstanceSet) {
        this.strengthExerciseInstanceSet = strengthExerciseInstanceSet;
    }

    public void addStrengthExerciseInstance(StrengthExerciseInstance exerciseInstance) {
        this.strengthExerciseInstanceSet.add(exerciseInstance);
    }

    public void removeStrengthExerciseInstance(StrengthExerciseInstance exerciseInstance) {
        this.strengthExerciseInstanceSet.remove(exerciseInstance);
    }
}
