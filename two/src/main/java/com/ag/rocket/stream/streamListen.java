package com.ag.rocket.stream;

import com.ag.rocket.stream.sink.MQ;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(value = MQ.class)
public class streamListen {

    @StreamListener(MQ.MQ_CONSUME)
    public void testListen(String msg){
        log.info("接收的消息为"+msg.getBytes());
    }

}
