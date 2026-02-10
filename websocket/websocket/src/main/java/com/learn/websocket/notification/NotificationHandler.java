package com.learn.websocket.notification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.websocket.user.User;


public  interface NotificationHandler {

   public void process(User user , String message );

}
