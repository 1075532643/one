package com.testRPCCodeing.core.rpc;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class RpcResponse implements Serializable {
    //协议头部分
    private String header;
    //协议体
    private byte[] body;
}
