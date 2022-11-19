package com.hmdp;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hmdp.entity.Shop;
import com.hmdp.service.impl.ShopServiceImpl;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RedisIdWorker;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.*;

@SpringBootTest
class redisTemplateTesst {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private ShopServiceImpl shopService;
    @Autowired
    private RedisIdWorker redisIdWorker;


    private ExecutorService executorService = Executors.newFixedThreadPool(500);

    private static  final DefaultRedisScript<Long> unlock_script;

    static  {
        unlock_script = new DefaultRedisScript<>();
        unlock_script.setLocation(new ClassPathResource("unlock.lua"));
        unlock_script.setResultType(Long.class);

    }

    @Test
    void testString() throws InterruptedException {
        //写入redis
        redisTemplate.opsForValue().set("name", "陈永光");
        redisTemplate.opsForValue().set("name", "", 10l, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("name"));
        TimeUnit.SECONDS.sleep(3);

    }
    @Test
    void testWork() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(300);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            executorService.submit(()->{
                for (int j = 0; j < 100; j++) {
                   Long id=   redisIdWorker.nextId("order");
                    System.out.println("id=:"+id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("花费时间为："+(begin-end));


    }

    public Shop queryWithMutes(Long id) {

        String key = RedisConstants.CACHE_SHOP_KEY + id;
        String shopJson = (String) redisTemplate.opsForValue().get(key);
        Shop shop = null;
        if (StringUtils.isNotBlank(shopJson)) {
            return JSONUtil.toBean(shopJson, Shop.class);
        }
        if (shopJson != null) {
            return null;
        }
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;

        //获取互斥锁
        try {
            if (!tryLock(lockKey)) {
                //失败-->休眠10秒重新获取
                try {
                    TimeUnit.SECONDS.sleep(10);
                    queryWithMutes(id);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //shop = shopService.getById(id);
            if (shop == null) {
                //如果数据不存在
                redisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.SECONDS);
                return null;
            } else {
                //更新缓存
                redisTemplate.opsForValue().setIfAbsent(key, JSONUtil.toJsonStr(shop), RedisConstants.CACHE_SHOP_TTL, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unlock(lockKey);
        }
        return shop;

    }

    private boolean tryLock(String key) {
        //如果包装类是null就会报空指针
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, "1", 10l, TimeUnit.SECONDS);
        //不建议直接返回上面的值，会自动拆箱，可能会出现空指针
        return BooleanUtils.isTrue(aBoolean);
    }

    private void unlock(String key) {
        redisTemplate.execute(unlock_script, Collections.singletonList("key"),"value");

       // redisTemplate.delete(key);
    }
}
