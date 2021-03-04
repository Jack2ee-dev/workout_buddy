package com.jack2ee.service.user;

import com.jack2ee.exception.OauthException;
import com.jack2ee.model.user.OauthProvider;

public class OauthServiceImpl implements OauthService{

    /**
     * externalize provider authentication server url
     * @param accessToken
     * @param provider
     */
    @Override
    public void authenticate(String accessToken, OauthProvider provider) throws OauthException {
        if (provider.getProvider().equals(OauthProvider.GOOGLE)) {
            try {

            } catch (RuntimeException e) {
                throw new OauthException(OauthProvider.GOOGLE.getProvider());
            }
        }
        try {

        } catch (RuntimeException e) {
            throw new OauthException(OauthProvider.APPLE.getProvider());
        }
    }
}
