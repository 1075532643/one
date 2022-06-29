package com.testRPCCodeing.server;

import com.testRPCCodeing.IDL.helloREquest;
import com.testRPCCodeing.IDL.helloResponse;
import com.testRPCCodeing.IDL.helloService;

import java.sql.SQLOutput;

public class HelloServiceImpl implements helloService {
    @Override
    public helloResponse hello(helloREquest helloRequest) {

        helloResponse resulr = new helloResponse("进入测试hello");
        System.out.println(resulr);
        return resulr;
    }

    @Override
    public helloResponse hi(helloREquest helloRequest) {
        helloResponse hiresule = new helloResponse("进入hi测试");
        return hiresule;
    }
}
