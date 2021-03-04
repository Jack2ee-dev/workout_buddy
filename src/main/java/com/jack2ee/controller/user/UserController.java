package com.jack2ee.controller.user;

import com.jack2ee.dto.user.UserDto;
import com.jack2ee.service.user.OauthService;
import com.jack2ee.service.user.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/user")
public class UserController {
    private OauthService oauthService;
    private UserService userService;

}
