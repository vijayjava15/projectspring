package com.urlShortner.urlShortner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {


    public static ResponseEntity<Map<String,Object>> addResponse(String msg, String status, Object data, HttpStatus statusCode){
        Map<String, Object> responseMap = new HashMap<>();
         responseMap.put("msg", msg);
         responseMap.put("status",status);
         responseMap.put("data", data);
         return new ResponseEntity<>(responseMap,statusCode);
    }

    public  static ResponseEntity<Map<String,Object>> BADREQUEST(String msg){
       return addResponse(msg, "FAILURE", null, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Map<String,Object>> NOTFOUND(String msg){
        return addResponse(msg, "NOTFOUND", null, HttpStatus.NOT_FOUND);
    }


    public static ResponseEntity<Map<String,Object>> SUCESS(String msg, Object Data){
        return addResponse(msg, "SUCESS", null, HttpStatus.OK);
    }
}
