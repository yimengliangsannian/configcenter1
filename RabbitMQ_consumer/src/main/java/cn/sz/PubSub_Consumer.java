package cn.sz;

import com.rabbitmq.client.*;

import java.io.IOException;

public class PubSub_Consumer {
    public static void main(String[] args) throws Exception{


        String queue1 = "queue_fanout1";
        String queue2 = "queue_fanout2";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/cnsz");
        factory.setUsername("cnsz");
        factory.setPassword("cnsz");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello_world", true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body:" + new String(body)+"打印到控制台");
            }
        };

        channel.basicConsume(queue1,consumer);


    }
}