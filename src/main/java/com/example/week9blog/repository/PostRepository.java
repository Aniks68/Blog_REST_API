package com.example.week9blog.repository;

import com.example.week9blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
    List<Post> findAllById(Long id);
}
