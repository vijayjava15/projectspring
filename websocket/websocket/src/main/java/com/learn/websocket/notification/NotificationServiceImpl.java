package com.learn.websocket.notification;

import com.learn.websocket.exception.ResponseUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Object create(Notification notification) {
        if (notification == null) {
            return ResponseUtility.BADREQUEST(null, "notification payload is required");
        }
        if (StringUtils.isBlank(notification.getMessage())) {
            return ResponseUtility.BADREQUEST(null, "message should not be blank");
        }
        if (notification.getRetryCount() != null && notification.getRetryCount() < 0) {
            return ResponseUtility.BADREQUEST(null, "retry count cannot be negative");
        }
        if (notification.getStatus() == null) {
            notification.setStatus(NotificationStatus.PENDING);
        }
        if (notification.getRetryCount() == null) {
            notification.setRetryCount(0);
        }

        Notification saved = notificationRepository.save(notification);
        return ResponseUtility.OK(saved, "notification saved successfully");
    }

    @Override
    public Object update(Long id, Notification notification) {
        if (id == null) {
            return ResponseUtility.BADREQUEST(null, "id is required");
        }
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "notification not found");
        }
        if (notification == null) {
            return ResponseUtility.BADREQUEST(null, "notification payload is required");
        }

        Notification existing = optionalNotification.get();

        if (StringUtils.isNotBlank(notification.getMessage())) {
            existing.setMessage(notification.getMessage());
        }
        if (notification.getStatus() != null) {
            existing.setStatus(notification.getStatus());
        }
        if (notification.getRetryCount() != null) {
            if (notification.getRetryCount() < 0) {
                return ResponseUtility.BADREQUEST(null, "retry count cannot be negative");
            }
            existing.setRetryCount(notification.getRetryCount());
        }
        if (notification.getRecipientUserId() != null) {
            existing.setRecipientUserId(notification.getRecipientUserId());
        }
        if (StringUtils.isNotBlank(notification.getChannel())) {
            existing.setChannel(notification.getChannel());
        }
        if (notification.getDeliveredAt() != null) {
            existing.setDeliveredAt(notification.getDeliveredAt());
        }
        if (notification.getReadAt() != null) {
            existing.setReadAt(notification.getReadAt());
        }

        Notification saved = notificationRepository.save(existing);
        return ResponseUtility.OK(saved, "notification updated successfully");
    }

    @Override
    public Object getById(Long id) {
        if (id == null) {
            return ResponseUtility.BADREQUEST(null, "id is required");
        }
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "notification not found");
        }
        return ResponseUtility.OK(optionalNotification.get(), "success");
    }

    @Override
    public Object getAll() {
        return ResponseUtility.OK(notificationRepository.findAll(), "success");
    }

    @Override
    public Object delete(Long id) {
        if (id == null) {
            return ResponseUtility.BADREQUEST(null, "id is required");
        }
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty()) {
            return ResponseUtility.BADREQUEST(null, "notification not found");
        }
        notificationRepository.deleteById(id);
        return ResponseUtility.OK(null, "notification deleted successfully");
    }
}
