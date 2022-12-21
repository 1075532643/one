package com.ag.one;

/**
 * 1）哈希函数可以把数据按照种类均匀分流
 * 2）布隆过滤器用于集合的建立与查询，并可以节省大量空间
 * 3）一致性哈希解决数据服务器的负载管理问题
 * 4）利用并查集结构做岛问题的并行计算
 * 5) 位图解决某一范围上数字的出现情况，并可以节省大量空间
 * 6）利用分段统计思想、并进一步节省大量空间
 * 7） 利用堆、外排序来做多个处理单元的结果合并
 * 之前的课己经介绍过前4个内容，本节内容为介绍解决大数据题目的后3个技巧
 */
public class code7 {


    public static int sign(int n) {
        return flip((n >> 32) & 1);

    }

    public static int flip(int n) {
        return n ^ 1;
    }

    public static int getMax2(int a, int b) {

        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb; //a和b的符号不一样为1；一样为0
        int sameSab = flip(difSab);//a和b的符号一样，为1；不一样为0
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    /**
     * 判断一个32位正数是不是2的幂、4的幂
     * 2的幂次方有一个条件：  二进制中，只有一个数字是1
     * 方法：取出最有侧的一个1，然后和数字比较，相同则是，在code0中有如何取出最右侧的方法
     * 4的幂次方：4的0次方  1在第0个位置
     * 4的1次方  1在第二个位置
     * 4的2次方  1在第四个位置
     * 4的3次方  1在第8个位置
     * 方法：和01010101.....0101 &
     */
    public static boolean is4Power(int n) {
        //01010101....010101
        return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }

    public static boolean is2Power(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 给定两个有符号32位整数a和b，不能使用算术运算符，分别实现a和b的加、减、乘、除运
     * 算
     * 【要求】
     * D
     * N
     * 如果给定a、b执行加减乘除的运算结果就会导致数据的溢出，那么你实现的函数不必对此
     * 负责，除此之外请保证计算过程不发生溢出
     */
    //a 和 b 相加不能溢出
    public static int add(int a, int b) {
        int sum = a;
        while (a != b) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    //取反
    public static int negNUm(int n) {
        return add(~n, 1);
    }

    public static int minus(int a, int b) {
        return add(a, negNUm(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (0 != b) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    /**
     * 暴力递归
     *
     * @param N    1～N这么多位置
     * @param E    最终目标是E
     * @param rest 还剩reset步要走
     * @param cur  当前在cur位置
     * @return 返回方法数
     */
    /*public static int f(int N, int E, int rest, int cur) {
        if (rest == 0) {
            return cur == E ? 1 : 0;
        }

    }*/

    public static int min1(int[] arr, int aim) {
        return f(arr, 0, 0, aim);
    }

    /**
     * @param arr   硬币在其中 固定参数
     * @param index 如果自由选择arr[index...] 这些硬币，但是之前的硬币一斤让你拥有了这么多钱
     * @param pre
     * @param aim   最终要达成的谬表
     * @return
     */
    public static int f(int[] arr, int index, int pre, int aim) {
        if (index == arr.length) { //来到终止位置，没有硬币可以选
            return pre == aim ? 1 : 0;
        }
        return f(arr, index + 1, pre, aim) + f(arr, index + 1, pre + arr[index], aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (rest == 0) {
            return 0;
        }
        if (index == arr.length) {
            return -1;
        }
        //rest > 0 并且有硬币
        //1.不选择当前硬币，之前往前
        //2.选择当前硬币
        int p1 = process(arr, index + 1, rest);
        int p2Next = process(arr, index + 1, rest - arr[index]);
        if (p1 == -1 && p2Next == -1) {
            return -1;
        } else {
            //加一是因为使用了当前的银币
            if (p1 == -1) {
                return p2Next + 1;
            }
            if (p2Next == -1) {
                return p1;
            }
            //如果两个都有效，返回娇小的那个
            return Math.min(p1, p2Next + 1);

        }
    }

    public static int minCoins2(int[] arr, int aim) {
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = -2;
            }
        }
        return process1(arr, 0, aim, dp);
    }

    /**
     * @param arr
     * @param index
     * @param rest
     * @param dp
     * @return
     */
    public static int process1(int[] arr, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return -1;
        }
        if (dp[index][rest] != -2) {
            return dp[index][rest];
        }
        if (rest == 0) {
            dp[index][rest] = 0;
        } else if (index == arr.length) {
            return dp[index][rest] = -1;
        } else {
            int p1 = process1(arr, index + 1, rest, dp);
            int p2Next = process1(arr, index + 1, rest - arr[index], dp);
            if (p1 == -1 && p2Next == -1) {
                return dp[index][rest] = -1;
            } else {
                //加一是因为使用了当前的银币
                if (p1 == -1) {
                    dp[index][rest] = p2Next + 1;
                } else if (p2Next == -1) {
                    dp[index][rest] = p1;
                } else {
                    dp[index][rest] = Math.min(p1, p2Next + 1);
                }
            }
        }
        return dp[index][rest];
    }

    /**
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static int first(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    //后手函数
    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }

        return Math.min(first(arr, i + 1, j), first(arr, i, j - 1));
    }

    /**
     * 象棋  从（0,0）位置出发，每次走日字  马走日
     *
     * @param x    目标x轴
     * @param y    目标y轴
     * @param step 必须几步
     * @return
     */
    public static int process3(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        if (step == 0) {
            return (x == 0 && y == 0) ? 1 : 0;
        }
        //到达的位置不越界，也有步数可以跳
        return process3(x - 1, y + 2, step - 1)
                + process3(x + 2, y - 1, step - 1)
                + process3(x + 1, y + 2, step - 1)
                + process3(x + 2, y + 1, step - 1)
                + process3(x + 1, y - 2, step - 1)
                + process3(x - 1, y - 2, step - 1)
                + process3(x - 2, y - 1, step - 1)
                + process3(x - 2, y + 1, step - 1);
    }


    public static int dpWays(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        int[][][] dp = new int[9][10][step + 1];
        dp[0][0][0] = 1;

        for (int h = 0; h <= step; h++) {//层
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 10; c++) {
                    dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c + 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c + 1, h - 1);

                }

            }

        }
        return dp[x][y][step];
    }

    public static int getValue(int[][][] dp, int row, int col, int step) {
        if (row < 0 || row > 8 || col < 0 || col > 9) {
            return 0;
        }
        return dp[row][col][step];
    }

    //arr里都是正数，没有重复值，每一个值代表一种货币，每一种都可以用无限张
    //最终要找零钱数是aim，
    //找零方法数返回
    public static int way1(int[] arr, int aim) {
        return process2(arr,0, aim);
    }

    // 可以自由使用arr[index...]所有的面值
    // 需要搞定的钱数是rest
    // /！返回找零的方法数
    public static int process2(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // arr[index]
        // 0张 1张...不要超过rest的钱数
        int ways = 0;
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
            ways += process2(arr, index + 1, rest - arr[index] * zhang);
        }
        return ways;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int len = (int) (Math.random() * 3);
        int[] arr = new int[(int)(Math.random()*len +1)];
    }
}
