package com.learn.websocket.user;

public enum Role {

    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
