package cn.sz.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener {

    @RabbitListener(queues = "queue_boot3")
    public void getMessage(Message message) {
        if (message != null) {
            System.out.println(message.toString());
        }
    }
}