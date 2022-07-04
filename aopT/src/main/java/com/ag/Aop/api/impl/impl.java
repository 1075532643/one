package com.ag.Aop.api.impl;


import ch.qos.logback.core.status.OnErrorConsoleStatusListener;
import com.ag.Aop.annotationT.annatationTest;
import com.ag.Aop.api.annotationUseApi;
import com.ag.Aop.pojo.Persopn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class impl implements annotationUseApi {

    @Override
    @annatationTest(status= annatationTest.Status.COMPLETE,Desc = "personAop方法")
    public void testAop(Persopn  persopn) {
        System.out.println("----进入接口方法实现----");
       // personAop(persopn);
    }

    //该方法加上@annatationTest时，aop不生效。原因未知-->18行
    public void personAop(Persopn p){
        System.out.println("进入方法");
    }

}
