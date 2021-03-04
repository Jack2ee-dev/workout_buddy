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
}
