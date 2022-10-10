package com.ag.JUC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

//cas+标记位（项目不可用）
@Slf4j
public class longAdder {

    private AtomicInteger state = new AtomicInteger(0);
    public void lock(){
        while (true){
            //当第二个线程进来时，此时不是0会进入死循环
            if(state.compareAndSet(0,1)){
                break;
            }
        }
    }

    public void unlock(){
        log.debug("unlock");
        state.set(0);
    }


}
