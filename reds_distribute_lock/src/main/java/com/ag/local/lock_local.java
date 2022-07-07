package com.ag.local;

import org.springframework.boot.autoconfigure.elasticsearch.jest.JestProperties;
import redis.clients.jedis.Jedis;

import java.util.Dictionary;
import java.util.UUID;

/*单机环境下
* 本地环境下的分布式锁
* */
public class lock_local {
    private static final int setNxSuccess=1;

    private String getLock(String lock,int noLockTimeOut,int lockTimeOut){
        Jedis jedis = new Jedis();
        long endTimeOut = System.currentTimeMillis() + noLockTimeOut;
        while (System.currentTimeMillis() < endTimeOut){
            String lockValue = UUID.randomUUID().toString();
            //可能存在死锁风险--->；当下面的一行代码设置成功的时，这时突然程序崩溃，那么就设置不了过期时间
            //会发生死锁
            if(jedis.setnx(lock,lockValue) == setNxSuccess){
                jedis.expire(lock,lockTimeOut/1000);
                return lockValue;
            }
        }
        try {
            if(jedis != null){
                jedis.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 释放锁 其实就是将该key删除
     *
     * @return
     */
    public Boolean unLock(String lockKey, String lockValue) {
        Jedis jedis = new Jedis();
        // 确定是对应的锁 ，才删除
        if (lockValue.equals(jedis.get(lockKey))) {
            return jedis.del(lockKey) > 0 ? true : false;
        }
        return false;
    }
}
