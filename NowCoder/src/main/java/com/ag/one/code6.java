package com.ag.one;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class code6 {


    /**
     * 岛问题
     * 2。4
     *
     * @param m
     * @return
     */
    public static int countIsland(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int N = m.length; //行
        int M = m[0].length; //列
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) {
                    res++;
                    infect(m, i, j, N, M);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] m, int i, int j, int N, int M) {
        //判断是否越界
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m, i + 1, j, N, M);
        infect(m, i - 1, j, N, M);
        infect(m, i, j + 1, N, M);
        infect(m, i, j - 1, N, M);
    }

    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    //并查集问题
    // 2。4
    public static class UnionFindSet<V> {

        public HashMap<V, Element<V>> elementMap;
        //key：元素  value：父元素
        public HashMap<Element<V>, Element<V>> fatherMap;
        //key 某个元素的代表集合，value 该集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public Element<V> findHead(Element<V> element) {
            Stack<Element<V>> stack = new Stack<>();
            while (fatherMap.get(element) != element) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            //将路径上所有点的父元素设置为element
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), element);
            }
            //放回当前父元素
            return element;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) > sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);

                }
            }
        }

    }

    /**
     * kmp
     * 经典暴力算法 时间复杂度很高，👇极端字符串时间复杂度最高
     * str1：111111112
     * str2：1112
     * 时间复杂度 O(n)
     */

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < 1) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        //O(n)
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {//str2中比对的位置已经不能往前跳了
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        //i1越界或者i2越界了
        return i2 == str2.length ? i1 - i2 : -1;
    }

    /**
     * 获取KMP算法  next数组
     *
     * @param str
     * @return
     */
    public static int[] getNextArray(char[] str) {

        if (str.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; //next 数组
        int cn = 0;
        while (i < next.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) { //当前跳到cn位置的自负，和i-1的字符配不上
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;

    }


    /**
     * Manacher 算法
     * 字符串中最长回文字串的问题
     * abc12320del     其中232是回文串  -->正着念翻着念一样
     * 对称轴左右是逆序
     * 回文半径，对称轴一边向上取整
     */
    public static char[] getManacher(String s) {

        char[] charArr = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = getManacher(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i != str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
                if (i + pArr[i] > R) {
                    R = i + pArr[i];
                    C = i;
                }
            }
            max = Math.max(max, pArr[i]);

        }
        return max - 1;
    }

    /**
     * 派对的最大快乐值
     * 员工信息的定义如下：
     * class Employee
     * public int happy;// 这名员工可以带来的快乐值
     * List<Employee> subordinates;/ 这名员工有哪些直接下级
     * 一
     * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、没有环的
     * 多叉树。树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。叶节点是没有
     * 任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下规则。
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2.派对的整体快乐值是所有到场员工快乐值的累加
     * 3.你的目标是让派对的整体快乐值尽量大
     * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
     * 上牛享
     * <p>
     * 节点来时候的最大快乐值和不来时候的最大快乐值比较
     * <p>
     * x + a不来的时候整棵树的最大值 + b不来的时候整棵树的最大快乐值
     */
    @Data
    @AllArgsConstructor
    public static class Info {
        public int laiMaxHappy;
        public int buMaxHappy;
    }

    public static class Employee {
        public int happy;
        public List<Employee> next;
    }

    public static int maxHappy(Employee boss) {
        Info process = process(boss);
        return Math.max(process.buMaxHappy, process.laiMaxHappy);
    }

    public static Info process(Employee x) {
        if (x.next.isEmpty()) {//说明x是最基层员工
            return new Info(x.happy, 0);
        }
        int lai = x.happy; //x来的情况
        int bu = 0; //x不来的情况
        for (Employee employee : x.next) {
            Info nextInfo = process(employee);
            //当x来的时候，底层员工只能不来
            lai += nextInfo.buMaxHappy;
            //x不来的时候， 需要注意此时 x的下级可能收到请柬了，但是没有来，这时候要选择利益最大化
            bu += Math.max(nextInfo.buMaxHappy, nextInfo.laiMaxHappy);
        }
        return new Info(lai, bu);
    }

    /**
     * Morris遍历细节
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1）如果cur没有 左孩子，cur向右移动(cur = cur.right)
     * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
     * a.如果mostRight的右指针指向空，让其指向cur，
     * 然后cur向左移动(cur = cur.left)
     * b.如果mostRight的右指针指向cur，让其指向null，
     * 然后cur向右移动(cur = cur.right)
     * 3) cur为空时遍历停止
     */
    //单调栈问题
    public static void morris(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode cur = node;
        TreeNode mostRight = null;

        while (cur != null) {
            mostRight = cur.getLeftChild();
            if (mostRight != null) {
                while (mostRight.getRightChild() != null && mostRight.getRightChild() != cur) {
                    mostRight = mostRight.getRightChild();
                }
                //mostRight 变成cur左子树上，最右边节点
                if (mostRight.getRightChild() == null) {//第一次来到cur
                    mostRight.setRightChild(cur);
                    cur = cur.getLeftChild();
                    continue;
                } else { //mostRight.right == cur;
                    mostRight.setRightChild(null);
                }

                //改为先序遍历
                //}else{
                //  System.out.println(cur.getValue());
                //}


                //改为中序遍历
                //System.out.println("");
                cur = cur.getRightChild();
            }
        }

    }
    //后序遍历改进要点
    //1。第二次遇到的节点，逆序打印左树右边届
    //2。逆序打印头节点的整个右边界


    /**
     * 判断一棵树是不是搜索二叉树 BST  morrist方法
     * 非中序遍历方法
     */
    public static boolean isBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        TreeNode cur = node;
        TreeNode mostRight = null;
        int preValue = Integer.MIN_VALUE;
        while (cur != null) {
            mostRight = cur.getLeftChild();
            if (mostRight != null) {
                while (mostRight.getRightChild() != null && mostRight.getRightChild() != cur) {
                    mostRight = mostRight.getRightChild();
                }
                //mostRight 变成cur左子树上，最右边节点
                if (mostRight.getRightChild() == null) {//第一次来到cur
                    mostRight.setRightChild(cur);
                    cur = cur.getLeftChild();
                    continue;
                } else { //mostRight.right == cur;
                    mostRight.setRightChild(null);
                }
                if (cur.getValue() <= preValue) {
                    return false;
                }
                preValue = cur.getValue();
                cur = cur.getRightChild();
            }
        }
        return true;
    }
}
