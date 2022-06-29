package com.ag.rocket;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

public class syncProducer {
    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
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
            Message message = new Message("base1", "tag1", ("hello world，这是base1,tag1第 " + i + "条消息").getBytes());

            //发送消息,并接收发送状态
            SendResult sendResult = producer.send(message);
            SendStatus sendStatus = sendResult.getSendStatus();
            String msgId = sendResult.getMsgId();
            int queueId = sendResult.getMessageQueue().getQueueId();
            System.out.println("发送状态"+sendStatus +"消息ID"+msgId +"队列ID"+queueId);

            TimeUnit.SECONDS.sleep(1);//线程睡一秒


        }
        //关闭生产者
        producer.shutdown();

    }
}
