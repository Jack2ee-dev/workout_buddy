package com.jack2ee.service.user;

import com.jack2ee.model.user.OauthProvider;

public interface OauthService {
    void authenticate(String accessToken, OauthProvider provider);
}
