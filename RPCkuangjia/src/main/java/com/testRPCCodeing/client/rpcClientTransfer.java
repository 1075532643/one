package com.testRPCCodeing.client;

import com.testRPCCodeing.core.rpc.RpcRequest;
import com.testRPCCodeing.core.rpc.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class rpcClientTransfer {
    public RpcResponse sendRequest(RpcRequest rpcRequest){
        //连接服务器指定的ip+端口号
        try (  Socket socket = new Socket("127.0.0.1", 9000)){
            //发送【transfer】
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            RpcResponse rpcResponse = (RpcResponse) objectInputStream.readObject();
            return rpcResponse;

        } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
            return null;
        }



    }
}
