package cn.sz.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BindingExchangeAndQueue {

    public static final String EXCHANGE_NAME ="topic_exchange3";
    public static final String QUEUE_NAME ="queue_boot3";

    @Bean("topic_exchange3")
    public Exchange getExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    @Bean("queue_boot3")
    public Queue getQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    @Bean
    public Binding bindExchangeAndQueue(@Qualifier("queue_boot3") Queue queue ,@Qualifier("topic_exchange3") Exchange exchange){
        return  BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }


}
