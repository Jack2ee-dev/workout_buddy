package com.jack2ee.service.user;

import com.jack2ee.model.user.User;
import com.jack2ee.model.user.UserRole;

import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    User addIfNotExist(String name, String email, String oauthProvider, UserRole userRole);
}
