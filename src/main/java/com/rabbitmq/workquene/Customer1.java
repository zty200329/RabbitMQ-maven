package com.rabbitmq.workquene;

import com.rabbitmq.client.*;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zty
 * @date 2020/4/10 下午3:22
 * @description:
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        //每一次只能消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);

        //不会自动确认消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1: " + new String(body));
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //手动确认  参数1:确认队列中的哪个具体消息  参数2:是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
