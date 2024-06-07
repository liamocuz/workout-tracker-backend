package com.liamocuz.workouttracker.repository;

import com.liamocuz.workouttracker.entity.UserInfo;
import org.junit.jupiter.api.*;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserInfoRepositoryTest {

    @LocalServerPort
    private Integer serverPort;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    @Order(1)
    public void createUser() {
        UserInfo bob = new UserInfo("bob@me.com", "Bob", "Martinez");
        userInfoRepository.save(bob);

        Optional<UserInfo> repoBob = userInfoRepository.findByEmail("bob@me.com");
        assertTrue(repoBob.isPresent());
        assertEquals(bob, repoBob.get());
    }

    @Test
    @Order(2)
    public void testUserUniqueEmail() {
        UserInfo duplicateBob = new UserInfo("bob@me.com", "EvilBob", "Martinez");
        assertThrows(DataIntegrityViolationException.class, () -> {
            userInfoRepository.save(duplicateBob);
        }, "Should throw a DataIntegrityViolationException due to violation the UNIQUE constraint on email");
    }
}
