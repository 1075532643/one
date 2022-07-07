package com.ag.test;

import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public interface junitTest1 {
   @RequestMapping(value = "/testy",method = RequestMethod.GET)
    public void test();

}
