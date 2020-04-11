package com.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.utils.RabbitMQUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zty
 * @date 2020/4/9 下午4:51
 * @description:
 */
public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        //创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //Connection connection1 = RabbitMQUtils.getConnection();
        //通过连接中的通道
        Channel channel = connection.createChannel();

        //通道绑定对应消息队列
        //参数1:队列名称
        //参数2:定义队列特性是否要持久化,true 持久化队列,false 不持久化,下次重启时就会被删除
        //参数3:exclusive 是否独占队列,如果为false,则我们声明的队列可以被其他链接所使用
        //参数4:autoDelete:是否在消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5:额外附加参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1:交换机名称 参数2:队列名称 参数3:传递消息的额外设置 参数4:消息的具体内容
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());

        channel.close();
        connection.close();

        //调用工具类
        //RabbitMQUtils.closeConnectionAndChanl(channel,connection);
    }
}
