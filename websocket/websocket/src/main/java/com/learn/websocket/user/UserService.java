package com.learn.websocket.user;

public interface UserService {
    Object registerUser(User user);

    Object login(User user);

    boolean verifyToken(String token);

    Object getAllUsers();
}
