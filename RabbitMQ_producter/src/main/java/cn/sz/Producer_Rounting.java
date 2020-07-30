package cn.sz;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer_Rounting {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/cnsz");
        factory.setUsername("cnsz");
        factory.setPassword("cnsz");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /*public AMQP.Exchange.DeclareOk exchangeDeclare(String exchange, String type,
        boolean durable, boolean autoDelete, Map<String, Object> arguments)*/
        String exchange = "text_direct";
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT,true,false,null);
        String queue1 = "queue_direct_1";
        String queue2 = "queue_direct_2";

        channel.queueDeclare(queue1,true,false,false,null);
        channel.queueDeclare(queue2,true,false,false,null);
        channel.queueBind(queue1,exchange,"error");
        channel.queueBind(queue2,exchange,"info");
        channel.queueBind(queue2,exchange,"error");
        channel.queueBind(queue2,exchange,"warning");

        String body = "张三调用了findAll方法~~打印日志";
        channel.basicPublish(exchange,"info",null,body.getBytes());
        channel.close();
        connection.close();
    }
}
