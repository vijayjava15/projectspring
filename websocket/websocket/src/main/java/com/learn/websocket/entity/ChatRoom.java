package com.learn.websocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;


@Entity
@Table(name = "chatroom")
@EntityListeners(EntityTracker.class)
public class ChatRoom extends BaseEntity {

    private String roomName;


    public String getRoomName() {
        return roomName;
    }

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "roomName='" + roomName + '\'' +
                '}';
    }

    public ChatRoom() {

    }
}
