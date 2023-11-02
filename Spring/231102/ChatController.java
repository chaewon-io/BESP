package com.woori.myapp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greeting")
	public Message send_message(Message msg) throws InterruptedException {
		//잠깐 cup를 릴리즈한다
		Thread.sleep(1000); //스레드가 1초동안 대기를 타도록 한다.
		return msg;
	}

}
