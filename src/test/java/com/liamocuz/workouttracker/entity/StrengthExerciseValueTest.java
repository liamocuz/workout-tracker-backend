package com.liamocuz.workouttracker.entity;

import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class StrengthExerciseValueTest {

    @Test
    void testObjectCreation() {
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        StrengthExercise exercise = new StrengthExercise(1L, "Push Ups", "", weightInfo, MuscleGroup.CHEST);
        exercise.setId(1L);

        WeightInfo weightInfoValue = new WeightInfo(200, 20, 2);
        StrengthExerciseValue value = new StrengthExerciseValue(weightInfoValue, exercise);
        value.setId(1L);

        assertEquals(1L, value.getId());
        assertEquals(weightInfoValue, value.getWeightInfo());
        assertEquals(exercise, value.getStrengthExercise());

        // Test setter change
        value.setId(2L);
        assertEquals(value.getId(), 2L);
        WeightInfo weightInfo1 = new WeightInfo(300, 30, 3);
        value.setWeightInfo(weightInfo1);
        assertEquals(value.getWeightInfo(), weightInfo1);
        StrengthExercise exercise1 = new StrengthExercise(2L, "Crunches", "", weightInfo, MuscleGroup.CORE);
        value.setStrengthExercise(exercise1);
        assertEquals(value.getStrengthExercise(), exercise1);
    }

    @Test
    void testEqualsAndHashCode() {
        WeightInfo weightInfo1 = new WeightInfo(100, 10, 1);
        StrengthExercise exercise1 = new StrengthExercise(1L, "Push Ups", "An upper body exercise", weightInfo1, MuscleGroup.CHEST);
        exercise1.setId(1L);

        WeightInfo weightInfoOverride1 = new WeightInfo(200, 20, 2);
        StrengthExerciseValue value1 = new StrengthExerciseValue(weightInfoOverride1, exercise1);
        value1.setId(1L);

        assertEquals(value1, value1);
        assertNotEquals(value1, null);
        assertNotEquals(value1, new Object());
        assertNotEquals(value1, new StrengthExerciseValue());
        assertNotEquals(new StrengthExerciseValue(), new StrengthExerciseValue());

        WeightInfo weightInfo2 = new WeightInfo(200, 20, 2);
        StrengthExercise exercise2 = new StrengthExercise(2L, "Push Ups", "An upper body exercise", weightInfo2, MuscleGroup.CHEST);
        exercise2.setId(2L);
        WeightInfo weightInfoOverride2 = new WeightInfo(200, 20, 2);
        StrengthExerciseValue value2 = new StrengthExerciseValue(weightInfoOverride2, exercise2);
        assertNotEquals(value1, value2);
        value2.setId(2L);

        assertNotEquals(value1, value2);
        value2.setId(1L);
        assertEquals(value1, value2);

        assertEquals(new StrengthExerciseValue().hashCode(), new StrengthExerciseValue().hashCode());
        assertEquals(value1.hashCode(), new StrengthExerciseValue().hashCode());
    }

    @Test
    void testToString() {
        WeightInfo weightInfo = new WeightInfo(100, 10, 1);
        StrengthExercise exercise = new StrengthExercise();
        exercise.setId(1L);
        exercise.setUserId(1L);
        exercise.setName("Push Ups");
        exercise.setDescription("Upper body exercise");
        exercise.setWeightInfo(weightInfo);
        exercise.setMuscleGroup(MuscleGroup.CHEST);
        Instant instant = Instant.now();
        exercise.setCreatedAt(instant);
        exercise.setUpdatedAt(instant);

        WeightInfo override = new WeightInfo(200, 20, 2);
        StrengthExerciseValue value = new StrengthExerciseValue(override, exercise);
        value.setId(2L);

        String expected = "StrengthExerciseValue{id=2, weightInfo=WeightInfo[weight=200.0, sets=20, reps=2], strengthExercise=%s}".formatted(exercise.getName());
        assertEquals(value.toString(), expected);
    }

}
