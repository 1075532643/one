package com.ag.JUC;

import lombok.extern.slf4j.Slf4j;
import org.redisson.misc.Hash;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class countdownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        String[] all = new String[10];
        Random r = new Random();
        for (int j = 0; j < 10; j++) {
            int k =j;
            service.submit(()->{
                for (int i = 1; i <= 100; i++) {
                    try {
                        Thread.sleep(r.nextInt(100));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    all[k] =i+"%";
                    System.out.print("\r" + Arrays.toString(all));
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("\r"+"加载完成...");
        service.shutdown();



    }
}
