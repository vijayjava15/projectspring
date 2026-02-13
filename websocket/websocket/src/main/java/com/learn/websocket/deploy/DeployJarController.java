package com.learn.websocket.deploy;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DeployJarController {


    @Autowired
    DeployJarService  deployJarService;

    @PostMapping("/webhook/deploy")
    public void postMethodName (@RequestBody String value) throws  InterruptedException, java.io.IOException{
        //TODO: process POST request
        System.out.println("deployment ");
         deployJarService.deploy();
    }
    

}
