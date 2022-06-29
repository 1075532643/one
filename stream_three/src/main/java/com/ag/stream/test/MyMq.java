package com.ag.stream.test;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface MyMq {

    String MQ_CONSUME = "mq_consume";

    String MQ_PRODUCER="mq_producer";

    @Input(MyMq.MQ_CONSUME)
    SubscribableChannel input();

    @Output(MyMq.MQ_PRODUCER)
    MessageChannel output();
}
