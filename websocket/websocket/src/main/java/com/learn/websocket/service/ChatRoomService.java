package com.learn.websocket.service;

import java.util.List;

public interface ChatRoomService {
    String save(String roomName);

    List<String> getAllRooms();
}
