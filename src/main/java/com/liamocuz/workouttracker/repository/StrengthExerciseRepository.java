package com.liamocuz.workouttracker.repository;

import com.liamocuz.workouttracker.entity.StrengthExercise;
import com.liamocuz.workouttracker.entity.StrengthExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StrengthExerciseRepository extends JpaRepository<StrengthExercise, Long> {
    List<StrengthExercise> findByUserId(Long userId);
    List<StrengthExercise> findByUserIdAndExerciseType(Long userId, StrengthExerciseType exerciseType);
    Optional<StrengthExercise> findByIdAndUserId(Long id, Long userId);
}
