package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class StrengthExerciseInstanceTest {

    @Test
    void testObjectCreation() {
        // Object setup
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        Instant startTime = Instant.now();
        StrengthExercise exercise = new StrengthExercise(1L, "Push Ups", "A chest workout", weightInfo, MuscleGroup.CHEST);
        assertNull(exercise.getId());
        exercise.setId(1L);
        StrengthExerciseInstance instance = new StrengthExerciseInstance(1L, startTime, weightInfo, exercise);
        assertNull(instance.getId());
        instance.setId(1L);

        // Test initial values
        assertEquals(instance.getId(), 1L);
        assertEquals(instance.getUserId(), 1L);
        assertEquals(instance.getWeightInfo(), weightInfo);
        assertEquals(instance.getStartTime(), startTime);
        assertEquals(instance.getStrengthExercise(), exercise);

        // Change fields with setters
        WeightInfo newWeightInfo = new WeightInfo(200, 20, 2);
        Instant newStartTime = Instant.now();
        instance.setId(2L);
        instance.setUserId(2L);
        instance.setStartTime(newStartTime);
        instance.setWeightInfo(newWeightInfo);
        instance.setStrengthExercise(null);

        // Test changed values
        assertEquals(instance.getId(), 2L);
        assertEquals(instance.getUserId(), 2L);
        assertEquals(instance.getStartTime(), newStartTime);
        assertEquals(instance.getWeightInfo(), newWeightInfo);
        assertNull(instance.getStrengthExercise());
    }

    @Test
    void testEqualsAndHashCode() {
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        Instant startTime = Instant.now();
        StrengthExercise exercise = new StrengthExercise(1L, "Push Ups", "A chest workout", weightInfo, MuscleGroup.CHEST);
        exercise.setId(1L);
        StrengthExerciseInstance instance1 = new StrengthExerciseInstance(1L, startTime, weightInfo, exercise);
        instance1.setId(1L);

        assertEquals(instance1, instance1);
        assertNotEquals(instance1, null);
        assertNotEquals(instance1, new Object());
        assertNotEquals(instance1, new StrengthExerciseInstance());

        assertNotEquals(new StrengthExerciseInstance(), new StrengthExerciseInstance());

        StrengthExerciseInstance instance2 = new StrengthExerciseInstance(2L, startTime, weightInfo, exercise);
        assertNotEquals(instance1, instance2);
        instance2.setId(2L);

        assertNotEquals(instance1, instance2);
        instance2.setId(1L);
        assertEquals(instance1, instance2);

        assertEquals(instance1.hashCode(), new StrengthExerciseInstance().hashCode());
        assertEquals(new StrengthExerciseInstance().hashCode(), new StrengthExerciseInstance().hashCode());
    }

    @Test
    void testToString() {
        Instant startTime = Instant.now();
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        StrengthExercise exercise = new StrengthExercise(1L, "Push Ups", "A chest workout", weightInfo, MuscleGroup.CHEST);
        exercise.setId(1L);
        StrengthExerciseInstance instance = new StrengthExerciseInstance();
        instance.setId(1L);
        instance.setUserId(1L);
        instance.setStartTime(startTime);
        instance.setWeightInfo(weightInfo);
        instance.setStrengthExercise(exercise);

        String expected = "StrengthExerciseInstance{id=1, userId=1, startTime=%s, weightInfo=WeightInfo[weight=100.0, sets=10, reps=1], strengthExercise='Push Ups'}"
                .formatted(startTime);
        assertEquals(instance.toString(), expected);
    }
}
