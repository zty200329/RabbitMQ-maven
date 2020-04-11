package com.rabbitmq.topic;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zty
 * @date 2020/4/11 下午3:46
 * @description:
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics","topic");

        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,"topics","user.#");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者3: " + new String(body));
            }
        });
    }
}
