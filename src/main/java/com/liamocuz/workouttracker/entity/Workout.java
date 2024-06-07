package com.liamocuz.workouttracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp(source = SourceType.VM)
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;

    @Column(name = "is_archived")
    private boolean isArchived = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workout_join_strength_exercise_value",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "strength_exercise_value_id")
    )
    private Set<WorkoutStrengthExerciseValue> strengthExerciseValues;

    public Workout() { }

    public Workout(Long userId, String name, String description) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.strengthExerciseValues = new HashSet<>();
    }

    @Override
    public String toString() {
        return "WorkoutTemplate{id=%d, name='%s', description='%s', createdAt=%s, updatedAt=%s, isArchived=%s, strengthExerciseValues=%s}".formatted(id, name, description, createdAt, updatedAt, isArchived, strengthExerciseValues);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public Set<WorkoutStrengthExerciseValue> getStrengthExerciseValues() {
        return strengthExerciseValues;
    }

    public void setStrengthExerciseValues(Set<WorkoutStrengthExerciseValue> strengthExerciseValues) {
        this.strengthExerciseValues = strengthExerciseValues;
    }

    public void addStrengthExerciseValue(WorkoutStrengthExerciseValue exerciseValue) {
        this.strengthExerciseValues.add(exerciseValue);
    }

    public void removeStrengthExerciseValue(WorkoutStrengthExerciseValue exerciseValue) {
        this.strengthExerciseValues.remove(exerciseValue);
    }
}