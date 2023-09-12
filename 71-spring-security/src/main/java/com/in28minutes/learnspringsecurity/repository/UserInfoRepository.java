package com.in28minutes.learnspringsecurity.repository;

import com.in28minutes.learnspringsecurity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
        Optional<UserInfo> findByName(String username);
}
