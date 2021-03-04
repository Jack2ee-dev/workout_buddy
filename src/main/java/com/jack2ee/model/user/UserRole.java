package com.jack2ee.model.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN(0), CUSTOMER(1), TRAINER(2);

    private final int intValue;

    UserRole(int intValue) {
        this.intValue = intValue;
    }
}
