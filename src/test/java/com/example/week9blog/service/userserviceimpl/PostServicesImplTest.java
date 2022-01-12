package com.example.week9blog.service.userserviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.PostRepository;
import com.example.week9blog.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class PostServicesImplTest {
    @Test
    void testSavePost() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(userInfo));

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("jane.doe@example.org");
        userInfo1.setId(123L);
        userInfo1.setPassword("iloveyou");
        userInfo1.setUserName("janedoe");

        Post post = new Post();
        post.setContent("Not all who wander are lost");
        post.setCreatedOn(null);
        post.setId(123L);
        post.setTitle("Dr");
        post.setUpdatedOn(null);
        post.setUserInfo(userInfo1);
        post.setUsername("janedoe");
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.save((Post) any())).thenReturn(post);
        PostServicesImpl postServicesImpl = new PostServicesImpl(userRepository, postRepository);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setEmail("jane.doe@example.org");
        userInfo2.setId(123L);
        userInfo2.setPassword("iloveyou");
        userInfo2.setUserName("janedoe");

        Post post1 = new Post();
        post1.setContent("Not all who wander are lost");
        post1.setCreatedOn(null);
        post1.setId(123L);
        post1.setTitle("Dr");
        post1.setUpdatedOn(null);
        post1.setUserInfo(userInfo2);
        post1.setUsername("janedoe");
        Post actualSavePostResult = postServicesImpl.savePost(123L, post1);
        assertSame(post1, actualSavePostResult);
        assertEquals(userInfo2, actualSavePostResult.getUserInfo());
        verify(userRepository).findById((Long) any());
        verify(postRepository).save((Post) any());
    }
}

