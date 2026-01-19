package com.learn.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.learn")

public class WebsocketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}


}
