package com.ag.rocket.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("orderTopic","orderTag1");
        //设定消费模式：负载均衡和广播模式----如果不设置，默认是负载均衡模式
        //consumer.setMessageModel(MessageModel.BROADCASTING);


        //该包表明按顺序消费消息队列
        consumer.registerMessageListener(new MessageListenerOrderly() {
             int i=0;
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                list.forEach(e->{
                    System.out.println("线程名："+Thread.currentThread().getName()+"消费第"+i +"消息" + new String(e.getBody()));
                    i++;
                });
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        //5.启动消费者
        consumer.start();
        System.out.println("消费者启动");
    }
}
