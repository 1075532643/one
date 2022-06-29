package com.ag.rocket.stream.sink;


import org.apache.rocketmq.common.message.Message;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface MQ {

    String MQ_CONSUME = "mq_consume";

    String MQ_OUTPUT="mq_producer";

    @Input(MQ.MQ_CONSUME)
    MessageChannel input();

    @Output(MQ_OUTPUT)
    MessageChannel output();
}
