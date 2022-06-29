package com.testRPCCodeing.IDL;

public interface helloService {
    helloResponse hello(helloREquest helloRequest);
    helloResponse hi(helloREquest helloRequest);
}
