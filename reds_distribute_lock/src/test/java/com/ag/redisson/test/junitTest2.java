package com.ag.redisson.test;

import org.redisson.api.RedissonClient;

import javax.annotation.Resource;


public class junitTest2 {

    @Resource
    RedissonClient redissonClient;

    public static void main(String[] args) {
        junitTest2 junitTest2 = new junitTest2();
        junitTest2.print();
    }

   public void print(){
       System.out.println(redissonClient);
   }

}
