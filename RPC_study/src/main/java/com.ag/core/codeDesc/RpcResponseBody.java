package com.ag.core.codeDesc;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

//调用编码
@Data
@Builder
public class RpcResponseBody implements Serializable {

private Object retObject;
}
