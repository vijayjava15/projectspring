package com.learn.websocket.error;

public enum ErrorType {

    BACKEND("BACKEND"),
    FRONTEND("FRONTEND"),
    DB("DB"),

    CLOUD("CLOUD");

    private final String value;

    ErrorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
