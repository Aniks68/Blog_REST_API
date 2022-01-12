package com.example.week9blog.controller;

import com.example.week9blog.dto.PostDto;
import com.example.week9blog.model.Comment;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.CommentService;
import com.example.week9blog.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/comment")
public class CommentController {

    CommentService commentService;
    PostServices postServices;

    @Autowired
    public CommentController(CommentService commentService, PostServices postServices) {
        this.commentService = commentService;
        this.postServices = postServices;
    }

    @PostMapping(path = "/saveComment/{postId}")
    public String saveComment(@RequestBody Comment comment, @PathVariable Long postId) {
        Post post1 = postServices.findPostById(postId);
        if (post1 != null) {
            comment.setCreatedOn(Instant.now());
            commentService.saveComment(postId, comment);
            return "Comment " + comment + "created";
        }
        return null;
    }





}
