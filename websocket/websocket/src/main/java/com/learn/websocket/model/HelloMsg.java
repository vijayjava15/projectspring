package com.learn.websocket.model;

import com.learn.websocket.model.enums.MessageType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloMsg {

    private MessageType type;
    private String content;
    private String sender;
}
