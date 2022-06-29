package com.ag.Jsata;

import java.util.ArrayList;
/*
* -Xms60m -Xmx60m -XX:SurvivorRatio=8
* */
public class GCTest {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        for(int i = 0;i<10000;i++){
            byte[] arr = new byte[1024 * 100];
            list.add(arr);
            try {
                Thread.sleep(120);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }
}
