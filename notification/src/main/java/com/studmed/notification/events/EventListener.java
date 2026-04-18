package com.studmed.notification.events;

import com.studmed.notification.shared.RabbitConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @RabbitListener(queues = RabbitConfiguration.QUEUE)
    public void handleUserCreated(String message) {
        System.out.println("Usuario creado: " + message);
    }
}
