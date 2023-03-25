package com.cgb.chatgpt.config.auto;

import com.cgb.chatgpt.config.exception.ChatGptException;
import com.cgb.chatgpt.config.property.ChatGptProperties;
import com.cgb.chatgpt.service.ChatGptService;
import com.cgb.chatgpt.service.impl.InitChatGptService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@EnableConfigurationProperties(ChatGptProperties.class)
@ConditionalOnClass(ChatGptService.class)
public class ChatGptAutoConfiguration {

    @Resource
    private ChatGptProperties chatGptProperties;

    @Bean
    @ConditionalOnMissingBean(ChatGptService.class)
    public ChatGptService chatGptService() {
        if (chatGptProperties.getModel() == null) {
            throw new ChatGptException("chatgpt.model is not empty.");
        }
        if (!StringUtils.hasText(chatGptProperties.getApiKey())) {
            throw new ChatGptException("chatgpt.apiKey is not empty.");
        }
        /*if (!StringUtils.hasText(chatGptProperties.getProxy())) {
            throw new ChatGptException("chatgpt.proxy is not empty.");
        }*/
        return new InitChatGptService(chatGptProperties);
    }
}
