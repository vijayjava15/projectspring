package com.learn.websocket.notification;

public interface NotificationService {
    Object create(Notification notification);

    Object update(Long id, Notification notification);

    Object getById(Long id);

    Object getAll();

    Object delete(Long id);
}
