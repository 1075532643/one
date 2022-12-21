package com.ag.IDL;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class helloREquest implements Serializable {
    private String name;

}
