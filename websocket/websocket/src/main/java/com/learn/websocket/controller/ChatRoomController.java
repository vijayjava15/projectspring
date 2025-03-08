package com.learn.websocket.controller;

import com.learn.websocket.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatRoomController {



    @Autowired
    ChatRoomService chatRoomService;

    @PostMapping("chat/save")
    public String save(@RequestBody String roomName){
        return chatRoomService.save(roomName);
    }

    @GetMapping("chat/get")
    public List<String> getAllRooms(){
        return chatRoomService.getAllRooms();
    }

}
