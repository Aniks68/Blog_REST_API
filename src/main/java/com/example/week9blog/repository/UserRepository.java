package com.example.week9blog.repository;

import com.example.week9blog.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUserNameAndPassword(String userName, String password);
    Optional<UserInfo> findFirstByUserName(String userName);
    UserInfo findByUserName(String userName);
    UserInfo findUserInfosById (Long id);

}
