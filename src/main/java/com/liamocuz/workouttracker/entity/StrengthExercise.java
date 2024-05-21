package com.liamocuz.workouttracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "strength_exercise")
public class StrengthExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Embedded
    private WeightInfo weightInfo;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant createdAt;

    @UpdateTimestamp(source = SourceType.VM)
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;

    @Column(name = "is_archived")
    private boolean isArchived;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_type_id")
    private StrengthExerciseType exerciseType;

    public StrengthExercise() { }

    public StrengthExercise(String name, String description, WeightInfo weightInfo, StrengthExerciseType exerciseType) {
        this.name = name;
        this.description = description;
        this.weightInfo = weightInfo;
        this.exerciseType = exerciseType;
    }

    @Override
    public String toString() {
        return "StrengthExerciseTemplate{id=%d, name='%s', description='%s', createdAt=%s, updatedAt=%s, weightInfo=%s, isArchived=%s, exerciseType=%s}".formatted(id, name, description, createdAt, updatedAt, weightInfo, isArchived, exerciseType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public WeightInfo getWeightInfo() {
        return weightInfo;
    }

    public void setWeightInfo(WeightInfo weightInfo) {
        this.weightInfo = weightInfo;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public StrengthExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(StrengthExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }
}
