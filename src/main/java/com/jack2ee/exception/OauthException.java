package com.jack2ee.exception;

public class OauthException extends RuntimeException {

    public OauthException(String provider) {
        super(provider + "에서 고객님의 정보를 가지고 올 수 없습니다.");
    }
}
