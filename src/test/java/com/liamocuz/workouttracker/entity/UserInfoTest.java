package com.liamocuz.workouttracker.entity;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    void testObjectCreation() {
        UserInfo userInfo = new UserInfo("test@test.com", "Test", "User");
        userInfo.setId(1L);
        assertEquals(1L, userInfo.getId());

        // Test arg constructor
        assertEquals("test@test.com", userInfo.getEmail());
        assertEquals("Test", userInfo.getFirstName());
        assertEquals("User", userInfo.getLastName());

        // Test changing arg constructor values
        userInfo.setEmail("test1@test.com");
        userInfo.setFirstName("Test1");
        userInfo.setLastName("User1");
        assertEquals("test1@test.com", userInfo.getEmail());
        assertEquals("Test1", userInfo.getFirstName());
        assertEquals("User1", userInfo.getLastName());

        // Test default and change
        assertFalse(userInfo.isVerified());
        userInfo.setVerified(true);
        assertTrue(userInfo.isVerified());

        // Test default and change
        assertFalse(userInfo.usesMetricUnits());
        userInfo.setMetricUnits(true);
        assertTrue(userInfo.usesMetricUnits());

        // Test times
        Instant current = Instant.now();
        userInfo.setCreatedAt(current);
        userInfo.setUpdatedAt(current);
        assertEquals(current, userInfo.getCreatedAt());
        assertEquals(current, userInfo.getUpdatedAt());
        userInfo.setLastWorkout(current);
        assertEquals(current, userInfo.getLastWorkout());

        // Test default and change
        assertEquals(0, userInfo.getWorkoutStreak());
        userInfo.setWorkoutStreak(5);
        assertEquals(5, userInfo.getWorkoutStreak());
    }

    @Test
    void testEqualsAndHashCode() {
        UserInfo userInfo1 = new UserInfo("test1@test.com", "Test1", "User1");
        userInfo1.setId(1L);

        assertEquals(userInfo1, userInfo1);
        assertNotEquals(userInfo1, null);
        assertNotEquals(userInfo1, new Object());

        UserInfo userInfo2 = new UserInfo("test2@test.com", "Test2", "User2");
        userInfo2.setId(2L);

        // Test equals method
        assertNotEquals(userInfo1, userInfo2);

        userInfo2.setId(1L);
        assertNotEquals(userInfo1, userInfo2);

        userInfo2.setEmail("test1@test.com");
        assertEquals(userInfo1, userInfo2);

        // Test hashCode method
        userInfo2.setId(2L);
        userInfo2.setEmail("test2@test.com");
        assertNotEquals(userInfo1.hashCode(), userInfo2.hashCode());

        userInfo2.setId(1L);
        assertNotEquals(userInfo1.hashCode(), userInfo2.hashCode());

        userInfo2.setEmail("test1@test.com");
        assertEquals(userInfo1.hashCode(), userInfo2.hashCode());

        // Both emails are null
        assertEquals(new UserInfo(), new UserInfo());
        assertEquals(new UserInfo().hashCode(), new UserInfo().hashCode());
    }

    @Test
    void testToString() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("test@test.com");
        userInfo.setFirstName("Test");
        userInfo.setLastName("User");
        userInfo.setId(1L);
        userInfo.setVerified(true);
        userInfo.setCreatedAt(Instant.now());
        userInfo.setLastWorkout(Instant.now());
        userInfo.setWorkoutStreak(5);
        userInfo.setMetricUnits(true);

        String expectedString = "UserInfo{id=1, email='test@test.com', firstName='Test', lastName='User', isVerified=true, createdAt=" + userInfo.getCreatedAt() + ", lastWorkout=" + userInfo.getLastWorkout() + ", workoutStreak=5, metricUnits=true}";
        assertEquals(expectedString, userInfo.toString());
    }
}