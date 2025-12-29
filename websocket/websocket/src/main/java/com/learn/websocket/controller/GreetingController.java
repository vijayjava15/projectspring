package com.learn.websocket.controller;

import com.learn.websocket.model.HelloMsg;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class GreetingController {

    private final SimpMessagingTemplate messagingTemplate;

    public GreetingController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Send Message to Room
    @MessageMapping("/chat/{room}/sendMessage")
    public void sendMessage(@DestinationVariable String room,
                            @Payload HelloMsg chatMessage) {
        System.out.println("***************************************");
        messagingTemplate.convertAndSend("/topic/" + room, chatMessage);
    }

    // Add User to Room
    @MessageMapping("/chat/{room}/addUser")
    public void addUser(@DestinationVariable String room,
                        @Payload HelloMsg chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/" + room, chatMessage);
    }
}
