package com.ag.rocket.stream.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* 生产者的配置类
* */
@Configuration
public class COnsumerCOnfig {

    @Bean
    public ObjectMapper getJackObjectMapper(){
        return new ObjectMapper();
    }
}
