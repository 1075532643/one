package com.ag.one;

import com.ag.pojo.Node;
import lombok.AllArgsConstructor;

import java.util.HashMap;

public class code8 {

    /**
     * 整数输入，整数输入，打表规律法
     * <p>
     * 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个
     * 每袋的包装包装不可拆分。可是小虎现在只想购买恰好n个苹果，小虎想购买尽
     * 量少的袋数方便携带。如果不能购买恰好n个苹果，小虎将不会购买。输入一个
     * 整数！，表示小虎想购买的个苹果，返回最小使用多少袋子。如果无论如何都不
     * 能正好装下，返回-1。
     */
    public static int appleA(int apple, int m, int n) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) { //24 是 6和8的最小公倍数
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    private static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) {//奇数返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 :
                    (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    /**
     * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可
     * 以选择任意一个正方形然后用这两种颜色的任意一种进行染色，这个正方形的颜色将
     * 会被覆盖。牛牛的目标是在完成染色之后，每个红色R都比每个绿色G距离最左侧近。
     * 牛牛想知道他最少需要涂染几个正方形。
     * 如样例所示：S= RGRGR
     * 我们涂染之后变成RRRGG满足要求了，涂染的个数为2，没有比这个更好的涂染方案。
     */
    public static int minPaintTest(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        for (int i = 0; i <= N; i++) { //枚举左侧是i，右侧大小为N-i
            if (i == 0) {
                //统计arr[0...n-1] 有多少个G，全部染成R
            } else if (i == N) {
                //统计arr[0...n-1] 有多少个R，全部染成G

            } else {
                //统计arr[0..i] 多少个R，全部染成G   统计arr[i+1...N]多少个G，全部染成R

            }

        }
        return -1;
    }

    /**
     * SB 树
     *
     * @param m
     * @param right
     * @param down
     */
    public static void setBoarderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }

        }

        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;

            }

        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;

                }
            }
        }
    }

    public static int maxAllONeBorder(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                //规定左上角的所有可能性

                //枚举变长
                for (int border = 1; border <= Math.min(N - row, M - col); border++) {
                    //左上角点（row，col） 边长是border

                }

            }
        }
        return -1;
    }

    /**
     * 给定一个西数f，可以1～5的数字等概率返回一个，请加工出1～7的数字等概率
     * 返回一个的函数g。
     * 给定一个西数f，可以a～b的数字等概率返回一个。请加工出c～d的数字等概率
     * 返回一个的函数g。
     * 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的
     * 函数g
     * <p>
     * 给 1-65个数，等概率返回1-7 的数字
     *
     * @return
     */
    public static int r01() {
        int res = 0;
        do {
            res = f();
        } while (res == 3);
        return res < 3 ? 0 : 1;

    }

    private static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int g() {
        int res = 0;
        do {
            res = (r01() << 2) + (r01() << 1) + r01();
        } while (res == 7);
        return res + 1;
    }

    public static int process4(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        //index及其后序还有数字字符的
        //0
        if (str[index] == '0') {
            return 0;
        }
        //不以0开头的
        int res = process4(str, index + 1);
        if (index == str.length - 1) {
            return res;
        }

        if (((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
            res += process4(str, index + 2);

        }
        return res;
    }

    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPath(TreeNode node) {
        p(node, 0);
        return maxSum;
    }

    /**
     * @param node
     * @param pre  从跟节点到当前节点的上一个节点间的路径和
     */
    public static void p(TreeNode node, int pre) {
        if (node.getLeftChild() == null & node.getRightChild() == null) {
            Math.max(maxSum, pre + node.getValue());
        }
        if (node.getLeftChild() != null) {
            p(node.getLeftChild(), pre + node.getValue());
        }
        if (node.getRightChild() != null) {
            p(node.getRightChild(), pre + node.getValue());
        }

    }

    /**
     * 顺时针打印矩阵
     *
     * @param m 矩阵
     * @param a 左上角元素行
     * @param b 左上角元素列
     * @param c 右下角 行
     * @param d 右下角列
     */
    public static void printEdge(int[][] m, int a, int b, int c, int d) {
        if (a == c) {
            for (int i = b; i < d; i++) {
                System.out.println(m[a][i] + " ");
            }

        } else if (b == d) {
            for (int i = a; i <= c; i++) {
                System.out.println(m[i][b] + "");

            }

        } else {
            int curC = b;
            int curR = a;
            while (curC != d) {
                System.out.println(m[a][curC] + "");
                curC++;
            }
            while (curR != c) {
                System.out.println(m[curR][d] + "");
                curR++;
            }
            while (curC != b) {
                System.out.println(m[c][curC] + "");
                curC--;
            }
            while (curR != a) {
                System.out.println(m[curR][b] + "");
                curR--;
            }
        }
    }

    public static void printZigZag(int[][] matrix) {
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;
        int endR = matrix.length;
        int endC = matrix[0].length;
        boolean fromUp = false;
        while (ar != endR + 1) {

            printLevel(matrix, ar, ac, br, bc, fromUp);
            ar = ac == endC ? ar + 1 : ar;
            ac = ac == endC ? ac : ac + 1;
            bc = br == endR ? bc + 1 : bc;
            br = br == endR ? br : br + 1;
            //取反
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        if (f) {
            while (tR != dR + 1) {
                System.out.println(m[tR++][tC--] + "");
            }
        } else {
            while (dR != tR - 1) {
                System.out.println(m[dR--][dC++] + "");
            }
        }
    }

    /**
     * 给定一个字符串类型的数组arr，求其中出现次数最多的前K个
     * <p>
     * 解决方法：1。小根堆维持2个的大小，每个进来的元素和堆顶比较大小
     * 2。大根堆，取出前某个
     */

    @AllArgsConstructor
    public static class Node {
        //字符
        public String str;
        //出现的次数
        public int times;
    }

    public static class topRecord {
        private HashMap<String, Node> strNodeMap;
        private Node[] heap;
        private int heapSize;
        //有记录并且大于-1，表示在堆上
        private HashMap<Node, Integer> nodeIndexMap;

        public topRecord(int size) {
            heap = new Node[size];
            heapSize = 0;
            //词频表
            strNodeMap = new HashMap<String, Node>();
            //堆上出现的位置
            nodeIndexMap = new HashMap<Node, Integer>();
        }

        public void add(String str) {
            Node curNode = null;
            //代表当前str对象是否在堆上
            int preIndex = -1;
            //是否第一次出现
            if (!strNodeMap.containsKey(str)) {
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            //词频增加之后仍然不在堆上
            if (preIndex == -1) {//两种情况：1堆满了2不满足门槛
                if (heapSize == heap.length) {
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;

                       //堆调整
                        // code2.heapFy(0, index);
                    }
                } else {
                    //堆没满
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    //堆插入
                    // code2.heapInsert(, index++);
                }

            }else{
                //节点原来就在堆上，直接调整
               // code2.heapFy();
            }
        }

    }

    /**
     * 如果一个字符串为str，把字符串str前面任意的部分挪到后面形成的字符串叫
     * 作str的旋转词。比如str="12345"，str的旋转词有"12345"、“23451"、
     * "34512"、"45123"和"51234"。给定两个字符串a和b，请判断a和b是否互为旋转
     * 词。
     * 比如：
     * a="cdab"， b="abcd"，返回true。
     * a="1ab2"，b="ab12"，返回false。
     * a="2ab1", b="ab12"，返回true。
     *
     * 解决方法：将A复制一份添加到后面，查看是否包含B，包含B返回true
     */

}
