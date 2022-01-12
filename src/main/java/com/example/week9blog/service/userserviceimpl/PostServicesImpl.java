package com.example.week9blog.service.userserviceimpl;

import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.PostRepository;
import com.example.week9blog.repository.UserRepository;
import com.example.week9blog.service.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            postRepository.save(post);
            return post;
        }
        return null;
    }
}
