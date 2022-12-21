package com.ag.one;

public class code9 {

    /**
     *格子存水问题
     * p24 50min
     *给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，
     * 请返回容器能装多少水
     * 比如，arr 三 (3，1，2，5，2，4)，根据值画出的直方图就是容器形状，该容
     * 器可以装下5格水
     * 再比如，arr = {4, 5，
     * ，3，2}，该容器可以装下2格水
     */

    /**
     *给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的
     * 作为右部分。但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，
     * 左部分最大值减去右部分最大值的绝对值。
     */

    /**
     * 给定一个数组arr，如果通过调整可以做到arr中任意两个相邻的数字相乘是4的倍数，
     * 返回true；如果不能返回false
     */

    /**
     *优化斐波那契数列
     */
    public static int fi(int n){
        if(n < 1){
            return 0;
        }
        if(n ==1 || n==2){
            return 1;
        }
        int[][] base = {{1,1},
                        {1,0 }};
        //求出斐波那契数列第N项
        int[][] res = matrixPower(base,n-2);
        return res[0][0] + res[1][0];
    }


    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        //设置对角线为1，单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, t);
            }
            t = muliMatrix(t, t);
        }
        return res;

    }

    //矩阵相乘  O（1）
    private static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }
}
