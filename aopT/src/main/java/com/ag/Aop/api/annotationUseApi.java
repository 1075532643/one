package com.ag.Aop.api;

import com.ag.Aop.pojo.Persopn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public interface annotationUseApi {


    @RequestMapping(value = "/testAop",method = RequestMethod.GET)
    void testAop(Persopn persopn);

    //  System.out.println("注解的值 "+ annotation.toString());


}

