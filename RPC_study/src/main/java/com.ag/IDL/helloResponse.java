package com.ag.IDL;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class helloResponse implements Serializable {
    private String msg;
}
