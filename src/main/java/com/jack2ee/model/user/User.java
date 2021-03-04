package com.jack2ee.model.user;

import com.jack2ee.model.baseTime.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String oauthProvider;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private int role;

    @Builder
    public User(String name, String email, String oauthProvider, String token, int roleValue) {
        this.name = name;
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.token = token;
        this.role = roleValue;
    }

    public void changeRole(UserRole role) {
        this.role = role.getIntValue();
    }
}
