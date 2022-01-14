package com.example.week9blog.service.userserviceimpl;

import com.example.week9blog.model.Comment;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.CommentRepository;
import com.example.week9blog.repository.PostRepository;
import com.example.week9blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    @Autowired
    public CommentImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment saveComment(Long postId, Comment comment) {
        Post post1 = postRepository.findById(postId).orElseThrow(NullPointerException::new);
        if(post1 != null) {
            comment.setPost(post1);
            commentRepository.save(comment);
            return comment;
        }
        return null;
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findCommentById(commentId);
    }

    @Override
    @Transactional
    public Comment editComment(Long commentId, String content) {
        Comment comment1 = commentRepository.getById(commentId);
        comment1.setContent(content);
        commentRepository.save(comment1);
        return comment1;
    }

}
