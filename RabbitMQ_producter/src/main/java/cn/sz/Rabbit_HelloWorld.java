package cn.sz;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Rabbit_HelloWorld {
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

        String body = "hello,RabbitMQ~~";
        channel.basicPublish("","hello_world",null,body.getBytes());
        channel.close();
        connection.close();
    }
}
