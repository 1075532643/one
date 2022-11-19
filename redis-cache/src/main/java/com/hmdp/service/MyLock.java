package com.hmdp.service;

public interface MyLock {
    boolean tryLock(Long time);

    void unlock();
}
