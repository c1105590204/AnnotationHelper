package com.chenyao.annotationutils;

import android.animation.ObjectAnimator;
import android.view.View;

import com.chenyao.annotationutil.EditView;
import com.chenyao.annotationutil.SetImage;
import com.chenyao.annotationutil.SetText;

/**
 * Created by <B>ChenYao</B> on <B>2016/9/21</B>.
 * <br/>
 */
public class Bean {
    public Bean(String name, String image, String infos, int color) {
        this.name = name;
        this.image = image;
        this.infos = infos;
        this.color = color;
    }

    @SetText(R.id.name)
    public String name;
    private String image;
    private String infos;

    @SetText(value = R.id.info, before = "信息：", afterStr = "。")
    public String getInfos() {
        return infos;
    }

    @SetImage(R.id.image)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    private int color;

    @EditView(R.id.view)
    public void editView(View view) {
        if (view != null) {
            ObjectAnimator.ofFloat(view, "translationX", 200).setDuration(5000).start();
            view.setBackgroundColor(color);
        }
    }
}
