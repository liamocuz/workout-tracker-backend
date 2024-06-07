package com.liamocuz.workouttracker.repository;

import com.liamocuz.workouttracker.entity.WorkoutInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutInstanceRepository extends JpaRepository<WorkoutInstance, Long> {
}
