package com.example.week9blog.service.userserviceimpl;

import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.UserRepository;
import com.example.week9blog.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserInfo> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        UserInfo newUser = userRepository.findByUsernameOrEmail(userInfo.getUsername(), userInfo.getEmail()).orElse(null);
        if (newUser != null) {
            System.out.println("This username is taken");
            return null;
        }
        userRepository.save(userInfo);
        System.out.println("User of id: " + userInfo.getId() + " has been saved.");
        return userInfo;
    }

    @Override
    public UserInfo authenticate(String userName, String password){
        return userRepository.findByUsernameAndPassword(userName, password).orElse(null);
    }

    @Override
    public UserInfo findUserById(Long id){
        return userRepository.findUserInfosById(id);
    }



    /*if (userRepository.findByEmail(userInfo.getEmail()).isPresent()) {
        System.out.println("This mail is in use");
        return null;
    }
        return userRepository.save(userInfo);*/
}
