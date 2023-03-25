package com.cgb.chatgpt.service.impl;

import com.cgb.chatgpt.config.property.ChatGptProperties;
import com.cgb.chatgpt.service.ChatGptService;
import com.cgb.chatgpt.service.entity.ChatGptDto;
import com.cgb.chatgpt.service.entity.ChatGptVo;

public class InitChatGptService implements ChatGptService {

    private final ChatGptProperties chatGptProperties;

    public InitChatGptService(ChatGptProperties chatGptProperties) {
        this.chatGptProperties = chatGptProperties;
    }

    @Override
    public String sendMessage(String message) {
        Integer maxTokens = chatGptProperties.getMaxTokens();
        if (maxTokens == null) {
            maxTokens = chatGptProperties.getModel().getMaxToken();
        }
        ChatGptVo chatGptVo = sendRequest(chatGptProperties.getApiKey(), chatGptProperties.getProxy(), new ChatGptDto(chatGptProperties.getModel().getModelName(), maxTokens, chatGptProperties.getTemperature(), new ChatGptDto.Content("user", message)));
        return parse(chatGptVo);
    }

    private String parse(ChatGptVo chatGptVo) {
        return chatGptVo.getChoices().stream().map(choice -> choice.getMessage().getContent()).reduce("", (x, y) -> x + "\n" + y);
    }
}
