package com.ag.one;

public class code1 {

    public static void process(int[] arr,int l,int r){
        if(l==r){
            return;
        }
        int mid = l +((r-l)>>1);
        process(arr,l,mid);
        process(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    //归并排序主过程
    public static void merge(int[] arr,int l,int m,int r){
        int[] help = new int[r-l+1];
        int i =0;
        int p1 = l;
        int p2 = m+1;
        while (p1<=m && p2<=r){
            help[i++] = arr[p1] <= arr[p2] ?arr[p1++]:arr[p2++];
        }
        while (p1<= m){
            help[i++] = arr[p1++];
        }
        while (p2<=r){
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l+j] = help[i];
        }
    };


    //求小和
    public static int smallSum(int[] arr,int l ,int r){

        int mid = 1 + ((r-l)>>1);
        return smallSum(arr,1,mid)
                + smallSum(arr,mid,r)
                + smallSumMerge(arr,l,mid,r);


    }


    public static int smallSumMerge(int[] arr,int l, int m, int r){
        int[] help = new int[r-l+1];
        int i = 0;
        int p1 = l;
        int p2 = m+1;
        int res = 0;
        while (p1<=m && p2<=r){
            res += arr[p1] < arr[p2] ? (r-m) * arr[p1]:0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] :arr[p2++];
        }
        while (p1 <= m){
            help[i++] = arr[p1++];
        }
        while (p2<= r){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l+j] = help[j];
        }
        return res;
    }

    //荷兰国旗问题，arr[] 是一个无序数组
    //左边全是小于num，中间全是等于num，右边全是大于num
    public static int[] heLan(int[] arr,int l, int r,int num){
        int p1= l-1;
        int i = l;
        int p2 = r;
        while(i < p2 ){
           // res[k++] = arr[p1] < num
            if(arr[l] < num){
                code0.swap(arr,i++,++p1);
            }
            if(arr[i]== num){
                i++;
            }
            if(arr[i] > num){
                code0.swap(arr,i,--p2);
            }

        }
        code0.swap(arr,p2,r);
        return  new int[]{p1+1,p2};
    }

    //快排3.0版本
    public static void quickSort(int[] arr,int l, int r) {

        if (l < r) {
            code0.swap(arr,(int)(Math.random() * (r - l )+1),r);
            int[] p = heLan(arr,l,r,arr[(int)Math.random()]);
            quickSort(arr,l,p[0]-1);
            quickSort(arr,p[1]+1,r);
        }
    }


}
