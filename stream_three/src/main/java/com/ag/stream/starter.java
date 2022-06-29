package com.ag.stream;

import com.ag.stream.test.MyMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MyMq.class)
public class starter {
    public static void main(String[] args) {
        SpringApplication.run(starter.class,args);
    }
}
