package com.example.week9blog.repository;

import com.example.week9blog.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUsernameAndPassword(String username, String password);
    Optional<UserInfo> findByUsernameOrEmail(String username, String email);
    UserInfo findByUsername(String userName);
    UserInfo findUserInfosById (Long id);

}
