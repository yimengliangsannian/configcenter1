package cn.sz;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitConsume {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/cnsz");
        factory.setUsername("cnsz");
        factory.setPassword("cnsz");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello_world",true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("Exchange:"+envelope.getExchange());
                System.out.println("RoutingKey:"+envelope.getRoutingKey());
                System.out.println("properties:"+properties);
                System.out.println("body:"+new String(body));
            }
        };

        channel.basicConsume("hello_world",consumer);


    }
}
