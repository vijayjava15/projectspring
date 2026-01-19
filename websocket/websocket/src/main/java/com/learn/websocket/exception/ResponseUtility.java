package com.learn.websocket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtility {

    public static Map<String , Object> buildResponse(String msg, HttpStatus code, Object data){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message",msg);
        responseMap.put("statusCode",code);
        responseMap.put("data",data);
        return responseMap;
    }

    public static Map<String , Object> OK (Object data, String message){
        return  buildResponse(message, HttpStatus.OK,data);
    }

    public static Map<String , Object> BADREQUEST (Object data, String message){
        return buildResponse(message, HttpStatus.BAD_REQUEST,data);
    }
}
