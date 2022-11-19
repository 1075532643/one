package com.hmdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan(basePackages = {"com.hmdp.mapper"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class redisCacheStart {
    public static void main(String[] args) {
        SpringApplication.run(redisCacheStart.class,args);
    }
}
