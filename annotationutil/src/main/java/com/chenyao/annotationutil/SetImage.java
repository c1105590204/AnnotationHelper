package com.chenyao.annotationutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by <B>ChenYao</B> on <B>2016/9/19</B>.
 * <br/>使用注解设置图片
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SetImage {
    /**
     * ImageView's id
     */
    int value();

    /**
     * Get a Image of the source, default is http
     */
    ResType resType() default ResType.Http;

    /**
     * If {@link ResType} is Http, can set the base url
     */
    String baseUrl() default "";

    /**
     * Show resources image when load error
     */
    int errorImage() default -1;

    /**
     * Show resources image when being loaded
     */
    int placeholderImage() default -1;

    /**
     * Set image show type,{@link ImageShowType}
     */
    ImageShowType getShowType() default ImageShowType.notSet;

    /**
     * Set image type,{@link ImageClass}
     */
    ImageClass getImageClass() default ImageClass.bitmap;
}