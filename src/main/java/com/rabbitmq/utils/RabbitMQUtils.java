package com.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zty
 * @date 2020/4/9 下午7:35
 * @description:
 */

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;

    static {
        //重量级资源 类加载时只执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");
    }
    //定义提供连接对象的方法
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChanl(Channel channel,Connection coon){
        try {
            if(channel!=null){
                channel.close();
            }
            if(coon!=null){
                coon.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
