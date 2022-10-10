package com.testRPCCodeing.client;

import com.testRPCCodeing.IDL.helloREquest;
import com.testRPCCodeing.IDL.helloResponse;
import com.testRPCCodeing.IDL.helloService;
import com.testRPCCodeing.server.HelloServiceImpl;

import java.net.Proxy;

public class testClient {
    public static void main(String[] args) throws Exception {
        rpcClientProxy rpcClientProxy = new rpcClientProxy();
        helloService service = rpcClientProxy.getService(helloService.class);
        helloREquest cyg = new helloREquest("cyg");
        helloResponse helloResponse = service.hello(cyg);
        String msg = helloResponse.getMsg();
        System.out.println(msg);


/*
        com.testRPCCodeing.IDL.helloResponse hi = service.hi(cyg);
        System.out.println(hi.getMsg());*/
    }
}
