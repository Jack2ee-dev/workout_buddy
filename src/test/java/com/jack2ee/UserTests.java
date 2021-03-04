package com.jack2ee;

import com.jack2ee.model.user.User;
import com.jack2ee.model.user.UserRole;
import com.jack2ee.repository.user.UserRepository;
import com.jack2ee.service.user.UserService;
import com.jack2ee.util.TokenUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {
    private static final String TEST_NAME = "허재혁";
    private static final String TEST_EMAIL = "test@gmail.com";
    private static final String TEST_OAUTH_PROVIDER = "google";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void 일반유저저장_불러오기() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        User user = userService.addIfNotExist(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER, UserRole.CUSTOMER);

        // then
        assertEquals(user.getEmail(), TEST_EMAIL);
        assertEquals(user.getName(), TEST_NAME);
        assertEquals(user.getOauthProvider(), TEST_OAUTH_PROVIDER);
        assertEquals(user.getToken(), TokenUtils.create(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER));
        assertEquals(user.getRole(), UserRole.CUSTOMER.getIntValue());
        assertTrue(user.getCreatedAt().isAfter(now));
        assertTrue(user.getUpdatedAt().isAfter(now));
    }

    @Test
    void 일반유저_관리자로_역할바꾸기() {
        // given
        User user = userService.addIfNotExist(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER, UserRole.CUSTOMER);

        // when
        userService.changeRole(user.getToken(), UserRole.ADMIN);

        // then
        assertEquals(userService.findById(user.getId()).getRole(), UserRole.ADMIN.getIntValue());
    }

    @Test
    void 일반유저_트레이너로_역할바꾸기() {
        // given
        User user = userService.addIfNotExist(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER, UserRole.CUSTOMER);

        // when
        userService.changeRole(user.getToken(), UserRole.TRAINER);

        // then
        assertEquals(userService.findById(user.getId()).getRole(), UserRole.TRAINER.getIntValue());
    }

    @Test
    void 트레이너_일반유저로_역할바꾸기() {
        // given
        User user = userService.addIfNotExist(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER, UserRole.TRAINER);

        // when
        userService.changeRole(user.getToken(), UserRole.CUSTOMER);

        // then
        assertEquals(userService.findById(user.getId()).getRole(), UserRole.CUSTOMER.getIntValue());
    }

    @Test
    void 트레이너_관리자로_역할바꾸기() {
        // given
        User user = userService.addIfNotExist(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER, UserRole.TRAINER);

        // when
        userService.changeRole(user.getToken(), UserRole.ADMIN);

        // then
        assertEquals(userService.findById(user.getId()).getRole(), UserRole.ADMIN.getIntValue());
    }

    @Test
    void 관리자_일반유저로_역할바꾸기() {
        // given
        User user = userService.addIfNotExist(TEST_NAME, TEST_EMAIL, TEST_OAUTH_PROVIDER, UserRole.ADMIN);

        // when
        userService.changeRole(user.getToken(), UserRole.CUSTOMER);

        // then
        assertEquals(userService.findById(user.getId()).getRole(), UserRole.CUSTOMER.getIntValue());
    }
}
