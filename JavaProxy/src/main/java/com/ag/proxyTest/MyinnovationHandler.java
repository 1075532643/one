package com.ag.proxyTest;

import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
@Data
public class MyinnovationHandler implements InvocationHandler {


    private Object object;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;
    }
}
