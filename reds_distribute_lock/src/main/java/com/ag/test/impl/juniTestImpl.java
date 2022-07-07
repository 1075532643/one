package com.ag.test.impl;

import com.ag.test.junitTest1;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class juniTestImpl  implements junitTest1 {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void test() {

        System.out.println(redissonClient);
        RLock lock_key = redissonClient.getLock("CYG_LOCK_KEY");
      //  lock_key.lock();
        long waitTime=500L;
        long leaseTime=15000L;
        boolean isLock;
        try {
            isLock = lock_key.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
            if (isLock) {
                // do something ...
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock_key.unlock();
        }
    }
}
