package com.ag.annoationSt.annatation.annotationStudy;

import com.ag.annoationSt.annatation.MyTest;
import com.ag.annoationSt.annatation.annotationStudy.inter.afterTest;
import com.ag.annoationSt.annatation.annotationStudy.inter.beforeTest;

public class classTest {

    @beforeTest
    public void init(){
        System.out.println("测试钱");
    }

    @afterTest
    public  void destory(){
        System.out.println("测试完");
    }

    @MyTest
    public void testng(){
        System.out.println("测试中");
    }
}
