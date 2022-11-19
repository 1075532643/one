package com.hmdp.service.impl;

import com.hmdp.service.MyLock;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class LockImpl implements MyLock {
    private StringRedisTemplate stringRedisTemplate;
    private String name;
    private final String PRE_LOCK="lock:";

    @Override
    public boolean tryLock(Long time) {
        Thread thread = Thread.currentThread();
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(PRE_LOCK + name, thread.getId()+"", time, TimeUnit.SECONDS);

        return Boolean.TRUE.equals(aBoolean);
    }

    @Override
    public void unlock() {
      stringRedisTemplate.delete(PRE_LOCK + name);
    }
}
