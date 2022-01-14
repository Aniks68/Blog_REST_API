package com.example.week9blog.service.userserviceimpl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.week9blog.model.UserInfo;
import com.example.week9blog.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServicesImpl.class})
@ExtendWith(SpringExtension.class)
class UserServicesImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServicesImpl userServicesImpl;

    @Test
    void testFindAllUser() {
        ArrayList<UserInfo> userInfoList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userInfoList);
        List<UserInfo> actualFindAllUserResult = this.userServicesImpl.findAllUser();
        assertSame(userInfoList, actualFindAllUserResult);
        assertTrue(actualFindAllUserResult.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    void testSaveUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");
        Optional<UserInfo> ofResult = Optional.of(userInfo);
        when(this.userRepository.findFirstByUserName((String) any())).thenReturn(ofResult);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("jane.doe@example.org");
        userInfo1.setId(123L);
        userInfo1.setPassword("iloveyou");
        userInfo1.setUserName("janedoe");
        assertNull(this.userServicesImpl.saveUser(userInfo1));
        verify(this.userRepository).findFirstByUserName((String) any());
        assertTrue(this.userServicesImpl.findAllUser().isEmpty());
    }

    @Test
    void testSaveUser2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");
        when(this.userRepository.save((UserInfo) any())).thenReturn(userInfo);
        when(this.userRepository.findFirstByUserName((String) any())).thenReturn(Optional.empty());

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setEmail("jane.doe@example.org");
        userInfo1.setId(123L);
        userInfo1.setPassword("iloveyou");
        userInfo1.setUserName("janedoe");
        assertSame(userInfo1, this.userServicesImpl.saveUser(userInfo1));
        verify(this.userRepository).findFirstByUserName((String) any());
        verify(this.userRepository).save((UserInfo) any());
        assertTrue(this.userServicesImpl.findAllUser().isEmpty());
    }

    @Test
    void testAuthenticate() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");
        when(this.userRepository.findByUserNameAndPassword((String) any(), (String) any())).thenReturn(userInfo);
        assertSame(userInfo, this.userServicesImpl.authenticate("janedoe", "iloveyou"));
        verify(this.userRepository).findByUserNameAndPassword((String) any(), (String) any());
        assertTrue(this.userServicesImpl.findAllUser().isEmpty());
    }

    @Test
    void testFindUserById() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("jane.doe@example.org");
        userInfo.setId(123L);
        userInfo.setPassword("iloveyou");
        userInfo.setUserName("janedoe");
        when(this.userRepository.findUserInfosById((Long) any())).thenReturn(userInfo);
        assertSame(userInfo, this.userServicesImpl.findUserById(123L));
        verify(this.userRepository).findUserInfosById((Long) any());
        assertTrue(this.userServicesImpl.findAllUser().isEmpty());
    }
}

