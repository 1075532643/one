package com.testRPCCodeing.server;

import com.testRPCCodeing.core.rpc.RpcResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class rpcServerWoker implements Runnable{

    private Map<String,Object> registerService;

    private Socket socket;

    public rpcServerWoker( Map<String,Object> registerService,Socket socket){
        this.socket=socket;
        this.registerService = registerService;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());





        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}
