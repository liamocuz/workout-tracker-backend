package com.liamocuz.workouttracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "is_verified")
    private boolean isVerified;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "last_workout")
    private Instant lastWorkout;

    @Column(name = "workout_streak")
    private int workoutStreak;      // in days

    @Column(name = "metric_units")
    private boolean metricUnits;    // true = uses metric

    public UserInfo() { }

    public UserInfo(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "UserInfo{id=%d, email='%s', firstName='%s', lastName='%s', isVerified=%s, createdAt=%s, lastWorkout=%s, workoutStreak=%d, metricUnits=%s}".formatted(id, email, firstName, lastName, isVerified, createdAt, lastWorkout, workoutStreak, metricUnits);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;
        return isVerified == userInfo.isVerified && workoutStreak == userInfo.workoutStreak && metricUnits == userInfo.metricUnits && Objects.equals(id, userInfo.id) && email.equals(userInfo.email) && firstName.equals(userInfo.firstName) && lastName.equals(userInfo.lastName) && Objects.equals(createdAt, userInfo.createdAt) && Objects.equals(lastWorkout, userInfo.lastWorkout);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastWorkout() {
        return lastWorkout;
    }

    public void setLastWorkout(Instant lastWorkout) {
        this.lastWorkout = lastWorkout;
    }

    public int getWorkoutStreak() {
        return workoutStreak;
    }

    public void setWorkoutStreak(int workoutStreak) {
        this.workoutStreak = workoutStreak;
    }

    public boolean isMetricUnits() {
        return metricUnits;
    }

    public void setMetricUnits(boolean metricUnits) {
        this.metricUnits = metricUnits;
    }
}
