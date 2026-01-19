package com.learn.websocket.user;

import com.learn.websocket.exception.ResponseUtility;

public interface UserService {
    Object registerUser(User user);

    Object login(User user);
}
