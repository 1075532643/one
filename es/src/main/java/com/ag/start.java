package com.ag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class start {
    public static void main(String[] args) {
        SpringApplication.run(start.class,args);
        System.out.println("启动成功");
    }
}
