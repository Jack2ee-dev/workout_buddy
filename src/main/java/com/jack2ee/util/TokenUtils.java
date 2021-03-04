package com.jack2ee.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class TokenUtils {

    /* TODO
    ** 1) externalize token key, issuer!
    ** 2) implement logger
    */
    public static String create(String name, String email, String oauthProvider)
        throws JWTCreationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256("testtokenkey");
            return JWT.create().withIssuer("auth0").sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("jwt 토큰을 생성할 수 없습니다.", exception);
        }
    }
}
