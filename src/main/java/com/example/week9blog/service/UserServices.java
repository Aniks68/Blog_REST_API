package com.example.week9blog.service;

import com.example.week9blog.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServices {
   // UserInfo getUserByEmail(String email);

    List<UserInfo> findAllUser();

    UserInfo saveUser(UserInfo userInfo);

    UserInfo authenticate(String userName, String password);

    UserInfo findUserById(Long id);
}
