package com.liamocuz.workouttracker.service;

import com.liamocuz.workouttracker.entity.Exercise;
import com.liamocuz.workouttracker.model.dto.ExerciseDTO;
import com.liamocuz.workouttracker.repository.StrengthExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StrengthExerciseService implements ExerciseService {

    private final StrengthExerciseRepository exerciseRepository;

    @Autowired
    public StrengthExerciseService(StrengthExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise createExercise(Long userId, ExerciseDTO exerciseDTO) {
        return null;
    }

    @Override
    public Exercise getExercise(Long userId, Long exerciseId) {
        return null;
    }

    @Override
    public List<Exercise> listExercises(Long userId) {
        return List.of();
    }

    @Override
    public Exercise updateExercise(Long userId, Long exerciseId, ExerciseDTO exerciseDTO) {
        return null;
    }

    @Override
    public boolean deleteExercise(Long userId, Long exerciseId) {
        return false;
    }
}
