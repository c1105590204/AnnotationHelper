package com.chenyao.annotationutil;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by <B>ChenYao</B> on <B>2016/9/21</B>.
 * <br/>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EditView {
    /**
     * 要编辑的View的Id，如果id等于-1，传入 parent View，如果id等于-2，传入AdapterViewHolder
     */
    int value() default -1;
}
