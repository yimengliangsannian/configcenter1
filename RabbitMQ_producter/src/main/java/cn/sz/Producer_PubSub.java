package cn.sz;

import com.rabbitmq.client.*;

public class Producer_PubSub {
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
        String exchange = "text_fanout";
        channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT,true,false,null);
        String queue1 = "queue_fanout1";
        String queue2 = "queue_fanout2";

        channel.queueDeclare(queue1,true,false,false,null);
        channel.queueDeclare(queue2,true,false,false,null);
        channel.queueBind(queue1,exchange,"");
        channel.queueBind(queue2,exchange,"");

        String body = "张三调用了findAll方法~~打印日志";
        channel.basicPublish(exchange,"",null,body.getBytes());
        channel.close();
        connection.close();
    }
}
