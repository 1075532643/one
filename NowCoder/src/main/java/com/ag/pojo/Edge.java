package com.ag.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Edge {
    public   int weight;
    public  Node from;
    public  Node to;

}
