package com.ag.rocket;

import com.ag.rocket.personT.Person;
import com.ag.rocket.stream.sink.MQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

@SpringBootApplication
@EnableBinding(value = MQ.class)
@EnableConfigurationProperties({Person.class})
public class startapp {
    public static void main(String[] args) {
        startapp startapp = new startapp();
        try {
            Enumeration<URL> resource = startapp.getClass().getClassLoader().getResources("META-INF/spring.factories");
            while (resource.hasMoreElements()){
                URL url = resource.nextElement();
                System.out.println("url:"+url);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(startapp.class,args);
    }
}
