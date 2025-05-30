package com.learn.websocket.service.impl;

import com.learn.websocket.entity.ChatRoom;
import com.learn.websocket.repository.ChatRoomRepository;
import com.learn.websocket.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Override
    public String save(String chatRoomName) {
        ChatRoom chatRoom = new ChatRoom(chatRoomName);
        chatRoomRepository.save(chatRoom);

        return "saved sucessfully";
    }


    @Override
    public List<String> getAllRooms() {
        return chatRoomRepository.findAll()
                .stream().map(room -> room.getRoomName()).toList();
    }
}
