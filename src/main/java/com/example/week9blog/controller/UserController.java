package com.example.week9blog.controller;

import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserServices userServices;

    @Autowired
    private UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/signup")
    public UserInfo signUp(@RequestBody UserInfo userInfo) {
        return userServices.saveUser(userInfo);
    }

    @GetMapping("/signin")
    public String signIn(@ModelAttribute UserInfo userInfo) {
        System.out.println("Login request: " + userInfo);
        UserInfo authenticated = userServices.authenticate(userInfo.getUsername(), userInfo.getPassword());
        System.out.println("Find it here " + authenticated);
        return (authenticated != null) ? authenticated + " signed in":"wrong credentials";
    }


}
