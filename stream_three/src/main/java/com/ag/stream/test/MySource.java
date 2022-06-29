package com.ag.stream.test;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    @Output("producer")
    MessageChannel msgOutput();


}
