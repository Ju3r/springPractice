package ru.ithub.jucr.testpractice.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Получаем bean по id
        Message message1 = (Message) context.getBean("message");
        System.out.println(message1.getText());

        // Получаем bean по классу
        Message message2 = context.getBean(Message.class);
        System.out.println(message2.getText());

        // Получаем другой bean по id
        Message1 message3 = (Message1) context.getBean("message1");
        System.out.println(message3.getText());

        // Получаем другой bean по классу
        Message1 message4 = context.getBean(Message1.class);
        System.out.println(message4.getText());
    }
}