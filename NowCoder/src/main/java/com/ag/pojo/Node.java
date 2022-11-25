package com.ag.pojo;

import java.util.ArrayList;

public class Node {
    public int value;
    //入
    public int in;
    //出
    public int out;
    //由该点发散出去的相邻直接点
    public ArrayList<Node> next;
    //属于我的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
