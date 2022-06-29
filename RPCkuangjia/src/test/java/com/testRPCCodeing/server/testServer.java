package com.testRPCCodeing.server;

import com.testRPCCodeing.IDL.helloService;

public class testServer {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer(); // 真正的rpc server
        helloService helloService1 = new HelloServiceImpl(); // 包含需要处理的方法的对象
        rpcServer.register(helloService1); // 向rpc server注册对象里面的所有方法
//        PingService pingService = new PingServiceImpl();
//        rpcServer.register(pingService);
        rpcServer.server(9000);
    }
}
