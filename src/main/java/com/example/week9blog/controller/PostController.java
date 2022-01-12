package com.example.week9blog.controller;

import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.PostServices;
import com.example.week9blog.service.UserServices;
import com.example.week9blog.service.userserviceimpl.PostServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/post")
public class PostController {

    UserInfo userInfo;
    PostServices postServices;
    UserServices userServices;

    @Autowired
    public PostController(PostServices postServices, UserServices userServices) {
        this.postServices = postServices;
        this.userServices = userServices;
    }

    @PostMapping(path = "/savePost/{userid}")
    public String savePost(@RequestBody Post post, @PathVariable Long userid) {
        UserInfo userInfo = userServices.findUserById(userid);
        if (userInfo != null) {
            post.setCreatedOn(Instant.now());
            postServices.savePost(userid, post);
            return "Post " + post + "created";
        }
        return null;
    }




}
