package com.testRPCCodeing.client;

import com.testRPCCodeing.core.codeDesc.RpcRequestBody;
import com.testRPCCodeing.core.codeDesc.RpcResponseBody;
import com.testRPCCodeing.core.rpc.RpcRequest;
import com.testRPCCodeing.core.rpc.RpcResponse;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//rpc框架代理
public class rpcClientProxy implements InvocationHandler {

    public <T> T getService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader()
                , new Class<?>[]{clazz},
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IOException, ClassNotFoundException {
        //将调用所需的信息编码成bytes[],也就是有了调用编码【codes】曾
        RpcRequestBody requestBody = RpcRequestBody.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .parameters(args)
                .build();
        System.out.println("进入poxyClient方法");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(requestBody);
        byte[] bytes = out.toByteArray();


        //创建好rpc协议，将body，header内容设置好（body中存放编码）
        RpcRequest rpcRequest = RpcRequest.builder()
                .body(bytes)
                .header("version=1.0")
                .build();
        //3.发送rpcrequest，获得rpcresponse
        rpcClientTransfer rpcClient = new rpcClientTransfer();
        RpcResponse rpcResponse = rpcClient.sendRequest(rpcRequest);


        //4.解析rpcresponse
        String header = rpcResponse.getHeader();
        byte[] body = rpcResponse.getBody();
        if (header.equals("version=1.0")) {
            ByteArrayInputStream bais = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(bais);
            RpcResponseBody rpcResponseBody = (RpcResponseBody) ois.readObject();
            Object retObject = rpcResponseBody.getRetObject();
            return retObject;
        }
        return null;

    }
}
