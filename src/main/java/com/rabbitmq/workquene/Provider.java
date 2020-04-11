package com.rabbitmq.workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zty
 * @date 2020/4/10 下午3:09
 * @description:
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();

        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        for (int i = 0; i < 20; i++) {
            //生产消息
            channel.basicPublish("","work",null,(i + "hello work quene").getBytes());
        }

        //关闭资源
        RabbitMQUtils.closeConnectionAndChanl(channel,connection);
    }
}
