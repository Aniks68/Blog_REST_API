package com.example.week9blog.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.week9blog.dto.PostDto;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.service.PostServices;
import com.example.week9blog.service.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PostController.class})
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    @Autowired
    private PostController postController;

    @MockBean
    private PostServices postServices;

    @MockBean
    private UserServices userServices;

    @Test
    void testSavePost() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUsername("janedoe");
        when(this.userServices.findUserById((Long) any())).thenReturn(userInfo);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("jane.doe@example.org");
        userInfo1.setId(123L);
        userInfo1.setPassword("iloveyou");
        userInfo1.setUsername("janedoe");

        Post post = new Post();
        post.setContent("Not all who wander are lost");
        post.setCreatedOn(null);
        post.setId(123L);
        post.setTitle("Dr");
        post.setUpdatedOn(null);
        post.setUserInfo(userInfo1);
        post.setUsername("janedoe");
        when(this.postServices.savePost((Long) any(), (Post) any())).thenReturn(post);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setEmail("jane.doe@example.org");
        userInfo2.setId(123L);
        userInfo2.setPassword("iloveyou");
        userInfo2.setUsername("janedoe");

        Post post1 = new Post();
        post1.setContent("Not all who wander are lost");
        post1.setCreatedOn(null);
        post1.setId(123L);
        post1.setTitle("Dr");
        post1.setUpdatedOn(null);
        post1.setUserInfo(userInfo2);
        post1.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(post1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/savePost/{userid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));
    }

    @Test
    void testEditPost() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUsername("janedoe");
        when(this.userServices.findUserById((Long) any())).thenReturn(userInfo);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("jane.doe@example.org");
        userInfo1.setId(123L);
        userInfo1.setPassword("iloveyou");
        userInfo1.setUsername("janedoe");

        Post post = new Post();
        post.setContent("Not all who wander are lost");
        post.setCreatedOn(null);
        post.setId(123L);
        post.setTitle("Dr");
        post.setUpdatedOn(null);
        post.setUserInfo(userInfo1);
        post.setUsername("janedoe");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setEmail("jane.doe@example.org");
        userInfo2.setId(123L);
        userInfo2.setPassword("iloveyou");
        userInfo2.setUsername("janedoe");

        Post post1 = new Post();
        post1.setContent("Not all who wander are lost");
        post1.setCreatedOn(null);
        post1.setId(123L);
        post1.setTitle("Dr");
        post1.setUpdatedOn(null);
        post1.setUserInfo(userInfo2);
        post1.setUsername("janedoe");
        when(this.postServices.editPost((Long) any(), (String) any(), (String) any())).thenReturn(post1);
        when(this.postServices.findPostById((Long) any())).thenReturn(post);

        PostDto postDto = new PostDto();
        postDto.setContent("Not all who wander are lost");
        postDto.setId(123L);
        postDto.setTitle("Dr");
        postDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(postDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/post/editPost/{userId}/{postId}", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post Edited"));
    }
}

