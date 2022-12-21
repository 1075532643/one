package com.ag.one;

import com.ag.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树
 */
public class code3 {


    /**
     * 前序遍历
     *
     * @param rootNode
     */
    public static void preOrderUnRecur(TreeNode rootNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (rootNode != null) {
            stack.push(rootNode);
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (pop.getLeftChild() != null) {
                    stack.push(pop.getLeftChild());
                }
                if (pop.getRightChild() != null) {
                    stack.push(pop.getRightChild());
                }
            }
        }

    }

    /**
     * 中序遍历
     */
    public static void inOrderUnRecur1(TreeNode rootNode) {

        if (rootNode != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || rootNode != null) {
                if (rootNode != null) {
                    stack.push(rootNode);
                    rootNode = rootNode.getLeftChild();
                } else {
                    TreeNode pop = stack.pop();
                    rootNode = pop.getRightChild();
                }
            }
        }

    }

    /**
     * 树的最大宽度
     */
    public static int w(TreeNode head) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
            }
            if (cur.getRightChild() != null) {
                levelMap.put(cur.getRightChild(), curLevel++);
                queue.add(cur.getRightChild());
            }
            if (cur.getLeftChild() != null) {
                levelMap.put(cur.getLeftChild(), curLevel++);
                queue.add(cur.getLeftChild());
            }
        }
        return max;
    }

    /**
     * 判断是否是平衡二叉树
     *
     * @param head
     * @return
     */
    public static boolean isBalanced(TreeNode head) {
        return process(head).isBalanced;
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int hei) {
            this.height = hei;
            this.isBalanced = isB;
        }
    }

    public static ReturnType process(TreeNode x) {
        if (x == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(x.getLeftChild());
        ReturnType rightData = process(x.getRightChild());
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

    /**
     * 判断一棵树是否是搜索二叉树
     *
     * @param head
     * @return
     */
    public static ReturnData processBST(TreeNode head) {
        if (head == null) {
            return null;
        }
        ReturnData leftData = processBST(head.getLeftChild());
        ReturnData rightData = processBST(head.getRightChild());
        int min = head.getValue();
        int max = head.getValue();
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isBST = true;
        if (leftData != null && !leftData.isBST || leftData.min > head.getValue()) {
            isBST = false;
        }
        if (rightData != null && !rightData.isBST || rightData.min > head.getValue()) {
            isBST = false;
        }

        return new ReturnData(min, max, isBST);
    }


    @AllArgsConstructor
    public static class ReturnData {
        public int min;
        public int max;
        public boolean isBST;
    }

    /**
     * 求解是否是满二叉树
     * 需要高度和个数
     */
    public static boolean isF(TreeNode head) {
        if (head == null) {
            return true;
        }
        Info data = processF(head);
        //是否符合公式 节点数= 2 的 高度次方 -1
        return data.nodes == (1 << data.height - 1);
    }

    @AllArgsConstructor
    public static class Info {
        public int height;
        public int nodes;
    }

    public static Info processF(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftData = processF(head.getLeftChild());
        Info rightData = processF(head.getRightChild());

        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;

        return new Info(height, nodes);


    }

    /**
     * 返回o1 o2 最低公共节点
     *
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public static TreeNode lca(TreeNode head, TreeNode o1, TreeNode o2) {
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        processLca(head, fatherMap);
        HashSet<TreeNode> set1 = new HashSet<>();
        TreeNode cur = o1;
        TreeNode fatherNode = o2;
        set1.add(cur);
        //从o1往上找
        while (cur != fatherMap.get(cur)) {
            cur = fatherMap.get(cur);
            set1.add(cur);
        }
        set1.add(head);
        while (!set1.contains(fatherMap.get(fatherNode))) {
            fatherNode = fatherMap.get(fatherNode);
        }
        return fatherMap.get(fatherNode);
    }

    public static void processLca(TreeNode head, HashMap<TreeNode, TreeNode> fatherMap) {
        if (head == null) {
            return;
        }
        fatherMap.put(head.getLeftChild(), head);
        fatherMap.put(head.getRightChild(), head);
        processLca(head.getLeftChild(), fatherMap);
        processLca(head.getRightChild(), fatherMap);
    }

    /**
     * 找到后继节点
     *
     * @param node
     * @return
     */
    public static TreeNode getSuccessorNode(TreeNode node) {
        if (node == null) {
            return node;
        }
        if (node.getRightChild() != null) {
            return getLeftNode(node);
        } else {
            TreeNode parent = node.getParent();
            while (parent != null && parent.getLeftChild() != node) {
                node = parent;
                parent = node.getParent();
            }
            return parent;
        }
    }

    public static TreeNode getLeftNode(TreeNode node) {
        if (node == null) {
            return node;
        }
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    /**
     * 打印折纸问题
     * 和中序遍历类似
     *
     * @param n
     */
    public static void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    public static void printProcess(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        printProcess(i + 1, n, true);
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, n, false);
    }


    /**
     * 树型dp    ----提升
     * 树形dp套路使用前提：
     * 如果题目求解目标是S规则，则求解流程可以定成以每一个节点为头节点的子树在S规则下
     * 的每一个答案，并且最终答案一定在其中
     * <p>
     * <p>
     * 1. 叉树节点间的最大距离问题
     * 从二叉树的节点a出发，可以向上或者向下走，但沿途的节点只能经过一次，到达节点b时路
     * 径上的节点个数叫作a到b的距离，那么二叉树任何两个节点之间都有距离，求整棵树上的最
     * 大距离。
     */

    @Data
    @AllArgsConstructor
    public static class InfoDates {
        public int maxDistance;
        public int height;

    }

    public static int maxDistance(TreeNode head) {
        return Process(head).maxDistance;
    }

    /**
     * 返回以node为头的整棵树信息
     *
     * @param node
     * @return
     */
    public static InfoDates Process(TreeNode node) {
        if (node == null) {
            return new InfoDates(0, 0);
        }
        InfoDates left = Process(node.getLeftChild());
        InfoDates right = Process(node.getRightChild());
        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        int p3 = left.height + right.height;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(left.height, right.height);
        return new InfoDates(maxDistance, height);


    }


}
