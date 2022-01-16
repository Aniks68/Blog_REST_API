package com.example.week9blog.service.userserviceimpl;

import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.PostRepository;
import com.example.week9blog.repository.UserRepository;
import com.example.week9blog.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class PostServicesImpl implements PostServices {

    UserRepository userRepository;
    PostRepository postRepository;

    @Autowired
    public PostServicesImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(Long id, Post post) {
        UserInfo userInfo1 = userRepository.findById(id).orElseThrow(NullPointerException::new);
        if(userInfo1 != null) {
            post.setUserInfo(userInfo1);
            post.setUsername(userInfo1.getUsername());
            postRepository.save(post);
            return post;
        }
        return null;
    }

    @Override
    @Transactional
    public Post editPost(Long postId, String title, String content) {
        Post post1 = postRepository.getById(postId);
            post1.setTitle(title);
            post1.setContent(content);
            post1.setUpdatedOn(Instant.now());
            postRepository.save(post1);
            return post1;
    }

    @Override
    public Post findPostById(Long postId){
        return postRepository.findPostById(postId);
    }
}
