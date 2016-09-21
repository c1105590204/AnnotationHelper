package com.chenyao.annotationutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by <B>ChenYao</B> on <B>2016/9/18</B>.
 * <br/>使用注解设置文本
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SetText {
    /**
     * TextView's id
     */
    int value();

    /**
     * Display before text
     */
    String before() default "";

    /**
     * Display after text
     */
    String afterStr() default "";
}
