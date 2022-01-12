package com.example.week9blog.controller;

import com.example.week9blog.dto.LoginDto;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.UserServices;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public String signIn(@RequestBody UserInfo userInfo) {
        UserInfo signinStatus = userServices.authenticate(userInfo.getUserName(), userInfo.getPassword());
        System.out.println("Find it here " + signinStatus.toString());
        return (signinStatus != null) ? signinStatus + " signed in":"wrong credentials";
    }


}
