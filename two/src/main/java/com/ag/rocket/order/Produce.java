package com.ag.rocket.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class Produce {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer= new DefaultMQProducer("group1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        List<OrderStep> orderSteps = OrderStep.buildOrders();
        for(int i = 0 ;i<orderSteps.size();i++){
            String body = orderSteps.get(i) + "";
            Message message  = new Message("orderTopic","orderTag1","i"+i,body.getBytes());

            //参数一：消息对象
            //参数二：消息队列的选择
            //参数三：选择的业务标识（订单id）
            SendResult send = producer.send(message, new MessageQueueSelector() {

                /*
                 * 参数一：队列集合
                 * 参数二：消息对象
                 * 参数三：业务标识的参数
                 *
                 * */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message msg, Object o) {
                    long orderId = (long) o;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, orderSteps.get(i).getOrderId());//这是第三个参数！！
            System.out.println("发送结果 ：" + send );
        }
        producer.shutdown();
    }
}
