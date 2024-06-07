package com.liamocuz.workouttracker.repository;

import com.liamocuz.workouttracker.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByFirstNameAndLastName(String firstName, String lastName);
}
