package com.example.week9blog.service.userserviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.week9blog.model.Comment;
import com.example.week9blog.model.Post;
import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.CommentRepository;
import com.example.week9blog.repository.PostRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommentImpl.class})
@ExtendWith(SpringExtension.class)
class CommentImplTest {
    @Autowired
    private CommentImpl commentImpl;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private PostRepository postRepository;

    @Test
    void testSaveComment() {
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
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);

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

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setEmail("jane.doe@example.org");
        userInfo2.setId(123L);
        userInfo2.setPassword("iloveyou");
        userInfo2.setUserName("janedoe");

        Comment comment = new Comment();
        comment.setContent("Not all who wander are lost");
        comment.setCreatedOn(null);
        comment.setId(123L);
        comment.setPost(post1);
        comment.setUpdatedOn(null);
        comment.setUserInfo(userInfo2);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        UserInfo userInfo3 = new UserInfo();
        userInfo3.setEmail("jane.doe@example.org");
        userInfo3.setId(123L);
        userInfo3.setPassword("iloveyou");
        userInfo3.setUserName("janedoe");

        Post post2 = new Post();
        post2.setContent("Not all who wander are lost");
        post2.setCreatedOn(null);
        post2.setId(123L);
        post2.setTitle("Dr");
        post2.setUpdatedOn(null);
        post2.setUserInfo(userInfo3);
        post2.setUsername("janedoe");

        UserInfo userInfo4 = new UserInfo();
        userInfo4.setEmail("jane.doe@example.org");
        userInfo4.setId(123L);
        userInfo4.setPassword("iloveyou");
        userInfo4.setUserName("janedoe");

        Comment comment1 = new Comment();
        comment1.setContent("Not all who wander are lost");
        comment1.setCreatedOn(null);
        comment1.setId(123L);
        comment1.setPost(post2);
        comment1.setUpdatedOn(null);
        comment1.setUserInfo(userInfo4);
        Comment actualSaveCommentResult = this.commentImpl.saveComment(123L, comment1);
        assertSame(comment1, actualSaveCommentResult);
        assertEquals(post2, actualSaveCommentResult.getPost());
        verify(this.postRepository).findById((Long) any());
        verify(this.commentRepository).save((Comment) any());
    }
}

