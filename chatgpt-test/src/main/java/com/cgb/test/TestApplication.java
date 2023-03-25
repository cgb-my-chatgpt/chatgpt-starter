package com.cgb.test;

import com.cgb.chatgpt.config.auto.EnableChatGpt;
import com.cgb.chatgpt.service.ChatGptService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@EnableChatGpt
@SpringBootApplication
@RestController
public class TestApplication {


    @Resource
    private ChatGptService chatGptService;

    @GetMapping("sendMessage")
    public String sendMessage(String message) {
        return chatGptService.sendMessage(message);
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
