package com.liamocuz.workouttracker.repository;

import com.liamocuz.workouttracker.PostgresTestContainer;
import com.liamocuz.workouttracker.entity.StrengthExercise;
import com.liamocuz.workouttracker.entity.UserInfo;
import com.liamocuz.workouttracker.model.MuscleGroup;
import com.liamocuz.workouttracker.model.WeightInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ImportTestcontainers(PostgresTestContainer.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StrengthExerciseRepositoryTest {

    @Autowired
    StrengthExerciseRepository exerciseRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    UserInfo testUser;

    @BeforeAll
    void beforeAll() {
        // Create a user to test with
        UserInfo bob = new UserInfo("bob2@me.com", "Bob", "Martinez");
        userInfoRepository.save(bob);
        testUser = bob;
    }

    @AfterAll
    void afterAll() {
        userInfoRepository.deleteAll();
        exerciseRepository.deleteAll();
    }

    @Test
    @Order(1)
    void createValidExercises() {
        List<StrengthExercise> bobStrengthExercises = new ArrayList<>();
        StrengthExercise pushUps = new StrengthExercise(testUser.getId(), "Push Ups", "", new WeightInfo(0, 5, 10), MuscleGroup.CHEST);
        StrengthExercise sitUps = new StrengthExercise(testUser.getId(), "Sit Ups", "", new WeightInfo(0, 5, 8), MuscleGroup.CORE);
        StrengthExercise pullUps = new StrengthExercise(testUser.getId(), "Pull Ups", "", new WeightInfo(0, 4, 5), MuscleGroup.BACK);

        bobStrengthExercises.add(pushUps);
        bobStrengthExercises.add(sitUps);
        bobStrengthExercises.add(pullUps);
        assertDoesNotThrow(() -> exerciseRepository.saveAll(bobStrengthExercises));
    }

    @Test
    void createExerciseInvalidWeightInfo() {
        StrengthExercise pushUps = new StrengthExercise(testUser.getId(), "Push Ups", "", new WeightInfo(-1, 5, 10), MuscleGroup.CHEST);
        StrengthExercise sitUps = new StrengthExercise(testUser.getId(), "Sit Ups", "", new WeightInfo(1, -1, 8), MuscleGroup.CORE);
        StrengthExercise pullUps = new StrengthExercise(testUser.getId(), "Pull Ups", "", new WeightInfo(0, 4, -2), MuscleGroup.BACK);

        assertThrows(DataIntegrityViolationException.class, () -> exerciseRepository.save(pullUps),
                "Should fail with invalid weight");
        assertThrows(DataIntegrityViolationException.class, () -> exerciseRepository.save(sitUps),
                "Should fail with invalid sets");
        assertThrows(DataIntegrityViolationException.class, () -> exerciseRepository.save(pullUps),
                "Should fail with invalid reps");
    }

    @Test
    void findSingleExercise() {
        Optional<StrengthExercise> exercise = exerciseRepository.findByIdAndUserId(1L, testUser.getId());
        assertTrue(exercise.isPresent(), "Should return a single exercise and be present");
        exercise.ifPresent(System.out::println);
    }

    @Test
    void findAllUserExercises() {
        List<StrengthExercise> exercises = exerciseRepository.findByUserId(testUser.getId());
        assertEquals(3, exercises.size(), "Should return a list of exercises of length 3");
        exercises.forEach(System.out::println);
    }

    @Test
    void findAllUserExercisesByMuscleGroup() {
        List<StrengthExercise> chestExercises = exerciseRepository.findByUserIdAndMuscleGroup(testUser.getId(), MuscleGroup.CHEST);
        List<StrengthExercise> legExercises = exerciseRepository.findByUserIdAndMuscleGroup(testUser.getId(), MuscleGroup.LEGS);

        assertEquals(1, chestExercises.size(), "Should return a single chest exercise");
        assertTrue(legExercises.isEmpty(), "There shouldn't be any leg exercises");
    }
}