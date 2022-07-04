package com.ag.annoationSt.annatation.annotationStudy.inter;

import lombok.Data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface annatationTest {

    public enum Status{NOT_COMPLETE,COMPLETE}
    public enum Info{AL_PAY,NOT_PAY};
     String value() default "默认值";
    String Desc() default "说明";
    Status status() default Status.COMPLETE;
    Info info() default Info.NOT_PAY;
}
