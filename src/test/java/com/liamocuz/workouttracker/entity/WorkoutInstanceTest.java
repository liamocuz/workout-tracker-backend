package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import com.liamocuz.workouttracker.model.WorkoutFeeling;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutInstanceTest {

    @Test
    void testObjectCreation() {
        Workout workout = new Workout(1L, "Monday", "Chest Day");
        workout.setId(1L);

        StrengthExercise pushUps = new StrengthExercise(1L, "Push Ups", "An upper body exercise", new WeightInfo(100, 10, 1), MuscleGroup.CHEST);
        StrengthExercise benchPress = new StrengthExercise(1L, "Bench Press", "A chest exercise", new WeightInfo(200, 20, 2), MuscleGroup.CHEST);

        StrengthExerciseValue pushUpsValue = new StrengthExerciseValue(new WeightInfo(100, 10, 1), pushUps);
        StrengthExerciseValue benchPressValue = new StrengthExerciseValue(new WeightInfo(200, 20, 2), benchPress);
        Set<StrengthExerciseValue> values = new HashSet<>();
        values.add(pushUpsValue);
        values.add(benchPressValue);
        workout.setStrengthExerciseValues(values);

        Instant startTime = Instant.now();
        Instant endTime = startTime.plusSeconds(60);
        WorkoutInstance workoutInstance = new WorkoutInstance(1L, workout, startTime, endTime, WorkoutFeeling.GREAT, "Tough one today");
        workoutInstance.setId(1L);

        assertEquals(workoutInstance.getId(), 1L);
        assertEquals(workoutInstance.getUserId(), 1L);
        assertEquals(workoutInstance.getWorkout(), workout);
        assertEquals(workoutInstance.getStartTime(), startTime);
        assertEquals(workoutInstance.getEndTime(), endTime);
        assertEquals(workoutInstance.getFeeling(), WorkoutFeeling.GREAT);
        assertEquals(workoutInstance.getNotes(), "Tough one today");
        assertEquals(workoutInstance.getStrengthExerciseInstances().size(), 0);

        // Change values with setters
        workoutInstance.setId(2L);
        workoutInstance.setUserId(2L);
        Instant newStartTime = Instant.now();
        Instant newEndTime = newStartTime.plusSeconds(60);
        workoutInstance.setStartTime(newStartTime);
        workoutInstance.setEndTime(newEndTime);
        workoutInstance.setFeeling(WorkoutFeeling.OKAY);
        workoutInstance.setNotes("Not too bad today");
        workoutInstance.setWorkout(null);

        assertEquals(workoutInstance.getId(), 2L);
        assertEquals(workoutInstance.getUserId(), 2L);
        assertEquals(workoutInstance.getStartTime(), newStartTime);
        assertEquals(workoutInstance.getEndTime(), newEndTime);
        assertEquals(workoutInstance.getFeeling(), WorkoutFeeling.OKAY);
        assertEquals(workoutInstance.getNotes(), "Not too bad today");
        assertNull(workoutInstance.getWorkout());
    }

    @Test
    void testEqualsAndHashCode() {
        WorkoutInstance instance1 = new WorkoutInstance();
        assertEquals(instance1, instance1);
        assertNotEquals(instance1, null);
        assertNotEquals(instance1, new Object());

        WorkoutInstance instance2 = new WorkoutInstance();
        assertNotEquals(instance1, instance2);
        assertEquals(instance1.hashCode(), instance2.hashCode());

        instance1.setId(1L);
        instance2.setId(2L);
        assertNotEquals(instance1, instance2);
        assertEquals(instance1.hashCode(), instance2.hashCode());

        instance2.setId(1L);
        assertEquals(instance1, instance2);
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    void testToString() {
        Workout workout = new Workout(1L, "Monday", "Chest Day");
        workout.setId(1L);

        StrengthExercise pushUps = new StrengthExercise(1L, "Push Ups", "An upper body exercise", new WeightInfo(100, 10, 1), MuscleGroup.CHEST);
        StrengthExercise benchPress = new StrengthExercise(1L, "Bench Press", "A chest exercise", new WeightInfo(200, 20, 2), MuscleGroup.CHEST);

        StrengthExerciseValue pushUpsValue = new StrengthExerciseValue(new WeightInfo(100, 10, 1), pushUps);
        StrengthExerciseValue benchPressValue = new StrengthExerciseValue(new WeightInfo(200, 20, 2), benchPress);
        Set<StrengthExerciseValue> values = new HashSet<>();
        values.add(pushUpsValue);
        values.add(benchPressValue);
        workout.setStrengthExerciseValues(values);

        Instant startTime = Instant.now();
        Instant endTime = startTime.plusSeconds(60);
        WorkoutInstance workoutInstance = new WorkoutInstance(1L, workout, startTime, endTime, WorkoutFeeling.GREAT, "Tough one today");
        workoutInstance.setId(1L);

        String expected = "WorkoutInstance{id=1, userId=1, startTime=%s, endTime=%s, feeling=GREAT, notes='Tough one today', workout='Monday', strengthExerciseInstances=[]}"
                .formatted(startTime, endTime);
        assertEquals(workoutInstance.toString(), expected);
    }

    @Test
    void testStrengthExerciseInstances() {
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

        Instant startTime = Instant.now();
        Instant endTime = startTime.plusSeconds(60);
        WorkoutInstance workoutInstance = new WorkoutInstance();
        workoutInstance.setId(1L);
        workoutInstance.setUserId(1L);
        workoutInstance.setWorkout(workout);
        workoutInstance.setStartTime(startTime);
        workoutInstance.setEndTime(endTime);
        workoutInstance.setFeeling(WorkoutFeeling.GOOD);
        workoutInstance.setNotes("Meh");
        workoutInstance.setStrengthExerciseInstances(new HashSet<>());

        // Create Strength Exercise Instances
        StrengthExerciseInstance instance1 = new StrengthExerciseInstance();
        instance1.setId(1L);
        StrengthExerciseInstance instance2 = new StrengthExerciseInstance();
        instance1.setId(2L);

        Set<StrengthExerciseInstance> instances = new HashSet<>();
        instances.add(instance1);
        instances.add(instance2);

        workoutInstance.setStrengthExerciseInstances(instances);
        assertEquals(workoutInstance.getStrengthExerciseInstances().size(), 2);

        workoutInstance.removeStrengthExerciseInstance(instance1);
        assertEquals(workoutInstance.getStrengthExerciseInstances().size(), 1);

        workoutInstance.addStrengthExerciseInstance(instance1);
        assertEquals(workoutInstance.getStrengthExerciseInstances().size(), 2);
    }
}
