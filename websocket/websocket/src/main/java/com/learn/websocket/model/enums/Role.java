package com.learn.websocket.model.enums;

public enum Role {

    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE"),
    INTERN("INTERN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
