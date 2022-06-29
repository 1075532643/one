package com.ag.rocket;

import com.ag.rocket.stream.sink.MQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = MQ.class)
public class startapp {
    public static void main(String[] args) {
        SpringApplication.run(startapp.class,args);
    }
}
