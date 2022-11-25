package com.ag.one;

import lombok.Data;

@Data
public class TreeNode {

    private int value;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private TreeNode parent;

    public TreeNode(int value){
        this.value = value;
    }

}
