package com.liamocuz.workouttracker.service;

import com.liamocuz.workouttracker.entity.Exercise;
import com.liamocuz.workouttracker.model.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {
    Exercise createExercise(Long userId, ExerciseDTO exerciseDTO);
    Exercise getExercise(Long userId, Long exerciseId);
    List<Exercise> listExercises(Long userId);
    Exercise updateExercise(Long userId, Long exerciseId, ExerciseDTO exerciseDTO);
    boolean deleteExercise(Long userId, Long exerciseId);
}
