package com.example.week9blog.service;

import com.example.week9blog.model.Comment;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment saveComment(Long postId, Comment comment);
//    Comment findById(Long id);
}
