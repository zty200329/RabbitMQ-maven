## RabbitMQ的api细节
```java
        //通道绑定对应消息队列
        //参数1:队列名称
        //参数2:定义队列特性是否要持久化,true 持久化队列,false 不持久化,下次重启时就会被删除
        //参数3:exclusive 是否独占队列,如果为false,则我们声明的队列可以被其他链接所使用
        //参数4:autoDelete
        //参数5:额外附加参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1:交换机名称 参数2:队列名称 参数3:传递消息的额外设置 参数4:消息的具体内容
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());


如果要持久化队列
channel.queueDeclare("hello",true,false,false,null);
如果要持久化消息
channel.basicPublish("","hello",MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());
```