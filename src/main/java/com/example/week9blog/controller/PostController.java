package com.example.week9blog.controller;

import com.example.week9blog.dto.PostDto;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.PostServices;
import com.example.week9blog.service.UserServices;
import com.example.week9blog.service.userserviceimpl.PostServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/post")
@Slf4j
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

    @PutMapping("/editPost/{userId}/{postId}")
    public String editPost(@RequestBody PostDto postDto, @PathVariable Long userId,
                           @PathVariable("postId")Long postId) {
        Post post1 = postServices.findPostById(postId);
        System.out.println("+++++++++ PID " + postId);
        UserInfo userInfo1 = userServices.findUserById(userId);
        System.out.println("++++++++= userId " + userId);
        log.info(String.valueOf(post1));
        log.info(String.valueOf(userInfo1));
        if (post1 != null & userInfo1 != null ) {
            postServices.editPost(postId, postDto.getTitle(), postDto.getContent());
            return "Post Edited";
        } else {
            return "Invalid Credentials";
        }
    }





}
