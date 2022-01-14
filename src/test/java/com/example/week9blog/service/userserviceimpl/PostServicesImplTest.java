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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServicesImpl.class})
@ExtendWith(SpringExtension.class)
class PostServicesImplTest {
    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServicesImpl postServicesImpl;

    @MockBean
    private UserRepository userRepository;

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

    @Test
    void testEditPost() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");

        Post post = new Post();
        post.setContent("Not all who wander are lost");
        post.setCreatedOn(null);
        post.setId(123L);
        post.setTitle("Dr");
        post.setUpdatedOn(null);
        post.setUserInfo(userInfo);
        post.setUsername("janedoe");

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("jane.doe@example.org");
        userInfo1.setId(123L);
        userInfo1.setPassword("iloveyou");
        userInfo1.setUserName("janedoe");

        Post post1 = new Post();
        post1.setContent("Not all who wander are lost");
        post1.setCreatedOn(null);
        post1.setId(123L);
        post1.setTitle("Dr");
        post1.setUpdatedOn(null);
        post1.setUserInfo(userInfo1);
        post1.setUsername("janedoe");
        when(this.postRepository.save((Post) any())).thenReturn(post1);
        when(this.postRepository.getById((Long) any())).thenReturn(post);
        Post actualEditPostResult = this.postServicesImpl.editPost(123L, "Dr", "Not all who wander are lost");
        assertSame(post, actualEditPostResult);
        assertEquals("Not all who wander are lost", actualEditPostResult.getContent());
        assertEquals("Dr", actualEditPostResult.getTitle());
        verify(this.postRepository).getById((Long) any());
        verify(this.postRepository).save((Post) any());
    }

    @Test
    void testFindPostById() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");

        Post post = new Post();
        post.setContent("Not all who wander are lost");
        post.setCreatedOn(null);
        post.setId(123L);
        post.setTitle("Dr");
        post.setUpdatedOn(null);
        post.setUserInfo(userInfo);
        post.setUsername("janedoe");
        when(this.postRepository.findPostById((Long) any())).thenReturn(post);
        assertSame(post, this.postServicesImpl.findPostById(123L));
        verify(this.postRepository).findPostById((Long) any());
    }
}

