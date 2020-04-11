package com.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zty
 * @date 2020/4/9 下午5:53
 * @description:
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");

        //创建连接对象
        Connection connection = connectionFactory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("hello",false,false,false,null);

        //消费消息
        //参数1:消费那个队列
        //参数2:开始消息的自动
        //参数3:消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){

            @Override//最后一个参数:消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = "+new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }
}
