package ru.ithub.jucr.testpractice.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Message message() {
        return new Message("Hello, Spring!");
    }

    @Bean
    public Message1 message1() {
        return new Message1("Hello, Spring111!");
    }
}