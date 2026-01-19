package com.learn.websocket.entity;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EntityTracker {

    @PrePersist
    public void logPersist(Object entity) {
        logStateChange(entity, "PERSISTENT");
    }

    @PostPersist
    public void logPostPersist(Object entity) {
        logStateChange(entity, "PERSISTED TO DB");
    }

    @PreUpdate
    public void logUpdate(Object entity) {
        logStateChange(entity, "UPDATED (DIRTY)" );
    }

    @PreRemove
    public void logRemove(Object entity) {
        logStateChange(entity, "REMOVED");
    }

    public void logDetach(Object entity) {
        logStateChange(entity, "DETACHED");
    }

    private void logStateChange(Object entity, String state) {
        System.out.println("[" + LocalDateTime.now() + "] " + entity.getClass().getSimpleName() +
                " changed state to: " + state);
    }
}
