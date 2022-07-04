package com.ag.Aop.annotationT;


import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class innotationImpl {

    @Pointcut("@annotation(com.ag.Aop.annotationT.annatationTest)")
    public void annotationTest(){}

    @Around(value = "annotationTest()")
    public Object annotationTest(ProceedingJoinPoint joinPoint){
        log.debug("进入切面：around");
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        annatationTest annotation = method.getAnnotation(annatationTest.class);
        String name = signature.getName();
        Class declaringType = signature.getDeclaringType();
        System.out.println("name:"+name+"生命类型："+declaringType+"方法名："+method+"参数："+joinPoint.getArgs());

        return "结束";
    }

}
