package com.learn.websocket.user;

public enum AppType {
    HOTEL("HOTEL"),
    PROJECT("PROJECT");

    private final String value;

    AppType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
