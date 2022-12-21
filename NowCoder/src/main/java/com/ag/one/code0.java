package com.ag.one;

import java.util.Collections;

public class code0 {
    public static void main(String[] args) {

    }

    public static void selectSort(int[] arr) {
        if (arr != null && arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);

        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {//
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i - 1; j > 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }

        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //不同为1相同为0  异或运算   还可以理解为无进制相加
    //使用该方法的前提是 i != j ，两个需要交换的数字在内存中不能是同一个内存空间
    public static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void printOddTimes(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor = eor ^ num;
        }
        //eor= a^b
        //eor != 0
        //eor 必然有一个位置是1

        /*
         * eor =1010111100
         * ~eor=0101000011
         * ~eor +1= 0101000100
         * eor & (~eor +1)提取出最右边一个1  -->   0000000100
         * */
        int rightOne = eor & (~eor + 1);//提取出最右边的1 ~eor 代表去反

        int onlyOne = 0;
        for (int cur : arr) {
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + "" + (eor ^ onlyOne));
    }

    //生成随机数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];//长度随机

        //math.random()-> [0,1) 所有的小树，等概率返回一个
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
