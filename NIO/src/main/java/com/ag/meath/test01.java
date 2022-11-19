package com.ag.meath;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Slf4j
public class test01 {
    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println("-------------");
        String name = "itcyg";
        allocate.put(name.getBytes());
        System.out.println(allocate.position());//5
        System.out.println(allocate.limit());
        System.out.println("-------------");

        allocate.flip();
        System.out.println(allocate.capacity());
        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println("----"

        );
;

        System.out.println((char) allocate.get());
    }
}
