package com.learn.websocket.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {



    @Autowired
    ErrorService errorService;

    @PostMapping("/save")
    public Object saveError(@RequestBody Error error) throws JsonProcessingException {

       return  errorService.save(error);

    }

    @PostMapping("/get")
    public Object getErrors(){

        return  errorService.getErrors();

    }
}
