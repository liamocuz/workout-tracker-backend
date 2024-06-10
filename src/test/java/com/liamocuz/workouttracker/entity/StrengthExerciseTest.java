package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class StrengthExerciseTest {

    @Test
    public void testObjectCreation() {
        // Initialize all fields
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        StrengthExercise exercise = new StrengthExercise(1L, "Push Ups", "An upper body exercise", weightInfo, MuscleGroup.CHEST);
        exercise.setId(1L);
        Instant current = Instant.now();
        exercise.setCreatedAt(current);
        exercise.setUpdatedAt(current);

        // Test primary initialization
        assertEquals(1L, exercise.getId());
        assertEquals(1L, exercise.getUserId());
        assertEquals("Push Ups", exercise.getName());
        assertEquals("An upper body exercise", exercise.getDescription());
        assertEquals(current, exercise.getCreatedAt());
        assertEquals(current, exercise.getUpdatedAt());
        assertFalse(exercise.isArchived());
        assertEquals(weightInfo, exercise.getWeightInfo());
        assertEquals(MuscleGroup.CHEST, exercise.getMuscleGroup());

        // Test constructor setter changes
        exercise.setUserId(2L);
        assertEquals(2L, exercise.getUserId());
        exercise.setName("Crunches");
        assertEquals("Crunches", exercise.getName());
        exercise.setDescription("A core exercise");
        assertEquals("A core exercise", exercise.getDescription());
        exercise.setArchived(true);
        assertTrue(exercise.isArchived());

        WeightInfo weightInfo1 = new WeightInfo(200, 20, 2);
        exercise.setWeightInfo(weightInfo1);
        assertEquals(weightInfo1, exercise.getWeightInfo());

        exercise.setMuscleGroup(MuscleGroup.CORE);
        assertEquals(MuscleGroup.CORE, exercise.getMuscleGroup());
    }

    @Test
    public void testEqualsAndHashcode() {
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        StrengthExercise exercise1 = new StrengthExercise(1L, "Push Ups", "An upper body exercise", weightInfo, MuscleGroup.CHEST);
        exercise1.setId(1L);

        // Test equals method
        assertEquals(exercise1, exercise1);
        assertNotEquals(exercise1, null);
        assertNotEquals(exercise1, new Object());

        StrengthExercise exercise2 = new StrengthExercise(2L, "Pull Ups", "", weightInfo, MuscleGroup.CHEST);
        exercise2.setId(2L);
        assertNotEquals(exercise1, exercise2);
        exercise2.setId(1L);
        assertNotEquals(exercise1, exercise2);
        exercise2.setUserId(1L);
        assertNotEquals(exercise1, exercise2);
        exercise2.setName("Push Ups");
        assertEquals(exercise1, exercise2);

        // Test hashcode
        assertEquals(exercise1.hashCode(), exercise2.hashCode());
        exercise2.setId(2L);
        assertNotEquals(exercise1.hashCode(), exercise2.hashCode());
        exercise2.setId(exercise1.getId());
        exercise2.setUserId(2L);
        assertNotEquals(exercise1.hashCode(), exercise2.hashCode());
        exercise2.setUserId(exercise1.getUserId());
        exercise2.setName("Test Name");
        assertNotEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test
    public void testToString() {
        WeightInfo weightInfo = new WeightInfo(100, 10, 10);
        StrengthExercise exercise = new StrengthExercise();
        exercise.setId(2L);
        exercise.setUserId(1L);
        exercise.setName("Push Ups");
        exercise.setDescription("Upper body exercise");
        exercise.setWeightInfo(weightInfo);
        exercise.setMuscleGroup(MuscleGroup.CHEST);
        Instant instant = Instant.now();
        exercise.setCreatedAt(instant);
        exercise.setUpdatedAt(instant);

        String expectedString = "StrengthExercise{weightInfo=WeightInfo[weight=100.0, sets=10, reps=10], muscleGroup=CHEST} Exercise{id=2, userId=1, name='Push Ups', description='Upper body exercise', createdAt=" + instant + ", updatedAt=" + instant + ", isArchived=false}";
        assertEquals(expectedString, exercise.toString());
    }
}