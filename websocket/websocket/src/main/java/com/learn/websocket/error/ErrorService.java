package com.learn.websocket.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {


    @Autowired
    ErrorRepo errorRepo;

    public Object save(Error error) {
       return  errorRepo.save(error);
    }

    public Object getErrors() {
        return errorRepo.findAll();
    }
}
