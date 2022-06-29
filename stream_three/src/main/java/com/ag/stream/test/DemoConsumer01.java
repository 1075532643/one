package com.ag.stream.test;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Slf4j
public class DemoConsumer01 {
    //private Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(MyMq.MQ_CONSUME)
    public void onMessage(String message){
        log.info("消息是：" + message);
        System.out.println("消息是：" +message+",线程是："+Thread.currentThread().getName());
    }
}
