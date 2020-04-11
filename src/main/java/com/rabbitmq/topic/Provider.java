package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zty
 * @date 2020/4/10 下午10:26
 * @description:
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型 topic
        channel.exchangeDeclare("topics","topic");

        //发送消息
        String routeKey = "user.save.findAll";

        channel.basicPublish("topics",routeKey,null,("这里是topic动态路由模型,routeKey: ["+routeKey+"]").getBytes());

        RabbitMQUtils.closeConnectionAndChanl(channel,connection);
    }
}
