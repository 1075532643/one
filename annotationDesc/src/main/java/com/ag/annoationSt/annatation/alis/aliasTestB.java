package com.ag.annoationSt.annatation.alis;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Component
public @interface aliasTestB {

    @AliasFor(annotation = aliasTestA.class,value = "a2")
    String value() default "";
}
