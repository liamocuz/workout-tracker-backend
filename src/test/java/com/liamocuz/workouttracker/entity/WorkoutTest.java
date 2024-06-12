package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutTest {

    @Test
    void testObjectCreation() {
        Workout workout = new Workout(1L, "Monday Workout", "Chest day");
        workout.setId(1L);
        Instant current = Instant.now();
        workout.setCreatedAt(current);
        workout.setUpdatedAt(current);

        assertEquals(workout.getId(), 1L);
        assertEquals(workout.getUserId(), 1L);
        assertEquals(workout.getName(), "Monday Workout");
        assertEquals(workout.getDescription(), "Chest day");
        assertEquals(workout.getCreatedAt(), current);
        assertEquals(workout.getUpdatedAt(), current);
        assertFalse(workout.isArchived());
        assertNotNull(workout.getStrengthExerciseValues());
        assertEquals(workout.getStrengthExerciseValues().size(), 0);

        // Test setter changes
        workout.setId(2L);
        assertEquals(workout.getId(), 2L);
        workout.setUserId(2L);
        assertEquals(workout.getUserId(), 2L);
        workout.setName("Tuesday Workout");
        assertEquals(workout.getName(), "Tuesday Workout");
        workout.setDescription("Back day");
        assertEquals(workout.getDescription(), "Back day");
        workout.setArchived(true);
        assertTrue(workout.isArchived());
    }

    @Test
    void testEqualsAndHashCode() {
        Workout workout1 = new Workout(1L, "Monday", "Chest Day");
        assertEquals(workout1, workout1);
        assertNotEquals(workout1, null);
        assertNotEquals(workout1, new Object());

        Workout workout2 = new Workout(2L, "Tuesday", "Back Day");
        assertNotEquals(workout1, workout2);

        // Test equals and hashCode
        workout2.setUserId(1L);
        assertNotEquals(workout1.hashCode(), workout2.hashCode());
        assertNotEquals(workout1, workout2);
        workout2.setName("Monday");
        assertEquals(workout1, workout2);
        assertEquals(workout1.hashCode(), workout2.hashCode());
    }

    @Test
    void testToString() {
        Workout workout = new Workout();
        workout.setId(1L);
        workout.setName("Monday");
        workout.setDescription("Chest Day");
        workout.setUserId(1L);
        Instant current = Instant.now();
        workout.setCreatedAt(current);
        workout.setUpdatedAt(current);
        workout.setArchived(false);

        String expected = "Workout{id=1, userId=1, name='Monday', description='Chest Day', createdAt=" + current + ", updatedAt=" + current + ", isArchived=false, strengthExerciseValues=[]}";
        assertEquals(workout.toString(), expected);
    }

    @Test
    void testStrengthExerciseValues() {
        Workout workout = new Workout(1L, "Monday", "Chest Day");

        StrengthExercise pushUps = new StrengthExercise(1L, "Push Ups", "An upper body exercise", new WeightInfo(100, 10, 1), MuscleGroup.CHEST);
        StrengthExercise benchPress = new StrengthExercise(1L, "Bench Press", "A chest exercise", new WeightInfo(200, 20, 2), MuscleGroup.CHEST);

        StrengthExerciseValue pushUpsValue = new StrengthExerciseValue(new WeightInfo(100, 10, 1), pushUps);
        StrengthExerciseValue benchPressValue = new StrengthExerciseValue(new WeightInfo(200, 20, 2), benchPress);

        assertEquals(workout.getStrengthExerciseValues().size(), 0);

        Set<StrengthExerciseValue> values = new HashSet<>();
        values.add(pushUpsValue);
        values.add(benchPressValue);
        workout.setStrengthExerciseValues(values);

        assertEquals(workout.getStrengthExerciseValues().size(), 2);

        workout.removeStrengthExerciseValue(pushUpsValue);
        assertEquals(workout.getStrengthExerciseValues().size(), 1);

        workout.addStrengthExerciseValue(pushUpsValue);
        assertEquals(workout.getStrengthExerciseValues().size(), 2);
    }
}
