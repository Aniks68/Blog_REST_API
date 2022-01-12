package com.example.week9blog.repository;

import com.example.week9blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentById(Long id);
    List<Comment> findAllById(Long id);
}
