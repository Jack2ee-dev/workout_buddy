package com.jack2ee.dto.user;

import lombok.Getter;

@Getter
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String oauthProvider;

    private String token;

    private int role;

    public UserDto(Long id, String name, String email, String oauthProvider, String token,
        int role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.token = token;
        this.role = role;
    }
}
