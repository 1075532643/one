package com.ag.annoationSt.annatation.alis;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Component
public @interface aliasTestA {

    @AliasFor(attribute = "a2")
    String a1() default "";

    @AliasFor(attribute = "a1")
    String a2() default "";

}
