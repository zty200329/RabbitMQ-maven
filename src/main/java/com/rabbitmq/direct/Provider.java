package com.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zty
 * @date 2020/4/10 下午6:52
 * @description:
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接通道对象
        Channel channel = connection.createChannel();

        //交换机名字
        String exchangeName = "logs_direct";
        //通过通道声明交换机 参数1:交换机名称 参数2:direct 路由模式
        channel.exchangeDeclare(exchangeName,"direct");
        //发送消息
        String routingKey = "error";
        channel.basicPublish(exchangeName,routingKey,null,("这是direct发布的基于route_key:["+routingKey+"] 发送的消息").getBytes());

        //关闭资源
        RabbitMQUtils.closeConnectionAndChanl(channel,connection);
    }
}
