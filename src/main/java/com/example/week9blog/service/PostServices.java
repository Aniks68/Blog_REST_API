package com.example.week9blog.service;

import com.example.week9blog.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostServices {
    Post savePost(Long id, Post post);
    Post editPost(Long postId, String title, String content);
    Post findPostById(Long postId);


}
