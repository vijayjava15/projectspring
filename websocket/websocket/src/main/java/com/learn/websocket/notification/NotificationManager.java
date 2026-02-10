package com.learn.websocket.notification;

import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.websocket.user.User;

@Component
public class NotificationManager {

    private final List<NotificationHandler> handlers;

    public NotificationManager(List<NotificationHandler> handlers) {
        this.handlers = handlers;
    }

    public void notifyAll(User user , String message) {
        for (NotificationHandler handler : handlers) {
            handler.process(user, message);

        }
    }

}
