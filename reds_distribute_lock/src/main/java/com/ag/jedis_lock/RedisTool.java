package com.ag.jedis_lock;

import org.redisson.Redisson;
import redis.clients.jedis.Jedis;

/*
* 集群环境下的分布式锁
* */
import java.util.Collections;

public class RedisTool {
    private static final String LOCK_SUCCESS="OK";
    private static final String SET_IF_NOT_EXIST="NX";
    private static final String SET_WITH_EXPIRE_TIME="PX";

    private static final Long RELEASE_SUCCESS = 1L;
    /*
    获取分布式锁-加锁
    * */
    public static boolean getDistributeLock(Jedis jedis,String lock,String
                                            requestId,int expireTime){
        String result = jedis.set(lock, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if(LOCK_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    /*删除锁，释放分布式锁
    lua脚本能够保证命令原子性执行
    保证释放锁的线程是加锁的线程
    * */
    public static boolean deleteDistributeLock(Jedis jedis,String lock,String requestId){
        String script="if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        Object eval = jedis.eval(script, Collections.singletonList(lock), Collections.singletonList(requestId));

        if(RELEASE_SUCCESS.equals(eval)){
            return true;
        }
        return false;
    }


}
