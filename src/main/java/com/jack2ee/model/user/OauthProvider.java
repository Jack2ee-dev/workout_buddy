package com.jack2ee.model.user;

import lombok.Getter;

@Getter
public enum OauthProvider {
    GOOGLE("google"),
    APPLE("apple");

    private String provider;

    OauthProvider(String provider) {
        this.provider = provider;
    }
}
