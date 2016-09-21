package com.chenyao.annotationutil;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by <B>ChenYao</B> on <B>2016/9/18</B>.
 * <br/>通过注解为控件设置初始化值
 */
public class AdapterInitDataUtils {

    /**
     * init view
     *
     * @param context           Context,Activity,Fragment(V4),Fragment
     * @param e                 Set the annotation object
     * @param adapterViewHolder Get view holder
     * @param <E>
     */
    public static <E> void init(Object context, E e, AdapterViewHolder adapterViewHolder) {
        if (!(context instanceof Activity) && !(context instanceof Fragment) && !(context instanceof android.app.Fragment) && !(context instanceof Context)) {
            throw new IllegalArgumentException("context只能是Context、Activity、Fragment(V4)、Fragment中的一个");
        }
        for (Field field : e.getClass().getFields()) {
            try {
                if (field.isAnnotationPresent(SetText.class)) {
                    SetText setText = field.getAnnotation(SetText.class);
                    setText(setText, field.get(e), adapterViewHolder);
//                    String after = setText.afterStr();
//                    String before = setText.before();
//                    int id = setText.value();
//                    adapterViewHolder.<TextView>getView(id).setText(String.valueOf(before + field.get(e) + after));
                } else if (field.isAnnotationPresent(SetImage.class)) {
                    SetImage setImage = field.getAnnotation(SetImage.class);
                    Object object = field.get(e);
                    glideShow(context, setImage, object, adapterViewHolder);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        for (Method method : e.getClass().getMethods()) {
            try {
                if (method.getName().startsWith("get")) {
                    if (method.isAnnotationPresent(SetText.class)) {
                        SetText setText = method.getAnnotation(SetText.class);
                        setText(setText, method.invoke(e), adapterViewHolder);
//                        int id = setText.value();
//                        String after = setText.afterStr();
//                        String before = setText.before();
//                        adapterViewHolder.<TextView>getView(id).setText(String.valueOf(before + method.invoke(e) + after));
                    } else if (method.isAnnotationPresent(SetImage.class)) {
                        SetImage setImage = method.getAnnotation(SetImage.class);
                        Object object = method.invoke(e);
                        glideShow(context, setImage, object, adapterViewHolder);
                    }
                } else if (method.getName().startsWith("edit")) {
                    if (method.isAnnotationPresent(EditView.class)) {
                        EditView editView = method.getAnnotation(EditView.class);
                        int id = editView.value();
                        if (id == -1) {
                            method.invoke(e, adapterViewHolder.getMainView());
                        } else if (id == -2) {
                            method.invoke(e, adapterViewHolder);
                        } else {
                            method.invoke(e, adapterViewHolder.getView(id));
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void setText(SetText setText, Object object, AdapterViewHolder adapterViewHolder) {
        String after = setText.afterStr();
        String before = setText.before();
        int id = setText.value();
        adapterViewHolder.<TextView>getView(id).setText(String.valueOf(before + object + after));
    }

    private static void glideShow(Object context, SetImage setImage, Object res, AdapterViewHolder adapterViewHolder) {
        ResType resType = setImage.resType();
        String baseUrl = setImage.baseUrl();
        int id = setImage.value();
        int errorImage = setImage.errorImage();
        int placeholderImage = setImage.placeholderImage();
        DrawableTypeRequest drawableTypeRequest = null;
        switch (resType) {
            case Http:
                drawableTypeRequest = getGlideRm(context).load(baseUrl + res);
                break;
            case Res:
                drawableTypeRequest = getGlideRm(context).load((Integer) res);
                break;
            case File:
                drawableTypeRequest = getGlideRm(context).load((File) res);
                break;
            case Uri:
                drawableTypeRequest = getGlideRm(context).load((Uri) res);
                break;
        }
        GenericRequestBuilder genericRequestBuilder = setGlideRm(drawableTypeRequest, setImage);
        if (genericRequestBuilder == null) {
            return;
        }
        if (errorImage != -1) {
            genericRequestBuilder.error(errorImage);
        }
        if (placeholderImage != -1) {
            genericRequestBuilder.placeholder(placeholderImage);
        }
        genericRequestBuilder.into(adapterViewHolder.<ImageView>getView(id));
    }

    private static GenericRequestBuilder setGlideRm(DrawableTypeRequest drawableTypeRequest, SetImage setImage) {
        switch (setImage.getImageClass()) {
            case bitmap:
                switch (setImage.getShowType()) {
                    case centerCrop:
                        return drawableTypeRequest.asBitmap().centerCrop();
                    case fitCenter:
                        return drawableTypeRequest.asBitmap().fitCenter();
                    case notSet:
                        return drawableTypeRequest.asBitmap();
                }
                break;
            case gif:
                switch (setImage.getShowType()) {
                    case centerCrop:
                        return drawableTypeRequest.asGif().centerCrop();
                    case fitCenter:
                        return drawableTypeRequest.asGif().fitCenter();
                    case notSet:
                        return drawableTypeRequest.asGif();
                }
                break;
        }
        return null;
    }

    private static RequestManager getGlideRm(Object context) {
        if (context instanceof Fragment) {
            return Glide.with((Fragment) context);
        } else if (context instanceof android.app.Fragment) {
            return Glide.with((android.app.Fragment) context);
        } else if (context instanceof FragmentActivity) {
            return Glide.with((FragmentActivity) context);
        } else if (context instanceof Activity) {
            return Glide.with((Activity) context);
        } else { // 默认Context
            return Glide.with((Context) context);
        }
    }
}
