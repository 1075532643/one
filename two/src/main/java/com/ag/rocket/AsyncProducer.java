package com.ag.rocket;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class AsyncProducer {
    public static void main(String[] args) throws InterruptedException, MQClientException, MQBrokerException, RemotingException {
        /*1.创建消息生产者对象
        2。指定nameserver地址
        3。启动producer
        4。创建消息对象，指定主题topic，tag和消息体
            参数一：消息主题topic
            参数二：消息tag
            参数三：消息内容
        *
        * */

        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for(int i =0;i<10;i++){
            Message message = new Message("base1", "tag2", ("hello world，这是base1,tag2第 " + i + "条消息").getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("成功，发送结果" + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败,"+throwable);
                }
            });

            TimeUnit.SECONDS.sleep(1);//线程睡一秒


        }
        //关闭生产者
        producer.shutdown();

    }
}
