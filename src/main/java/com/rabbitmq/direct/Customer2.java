package com.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zty
 * @date 2020/4/10 下午8:22
 * @description:
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        //声明交换机
        channel.exchangeDeclare(exchangeName,"direct");

        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        //临时队列和交换机绑定
        channel.queueBind(queue,exchangeName,"info");
        channel.queueBind(queue,exchangeName,"error");
        channel.queueBind(queue,exchangeName,"warning");

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: "+ new String(body));
            }
        });
    }
}
