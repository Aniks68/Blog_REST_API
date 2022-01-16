package com.example.week9blog.controller;

import com.example.week9blog.dto.CommentDto;
import com.example.week9blog.dto.PostDto;
import com.example.week9blog.model.Comment;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.CommentService;
import com.example.week9blog.service.PostServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
@Slf4j
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



   @PutMapping("/editComment/{postId}/{commentId}")
    public String editComment(String content, @PathVariable Long postId,
                              @PathVariable("commentId")Long commentId) {
       System.out.println(content);
        Comment comment1 = commentService.findCommentById(commentId);
        Post post1 = postServices.findPostById(postId);
        System.out.println("++++++++= userId " + commentId);
        if (post1 != null & comment1 != null ) {
            commentService.editComment(commentId, content);
            return "Comment Edited: " + comment1;
        } else {
            return "Invalid Credentials";
        }
    }



}
