package com.chenyao.annotationutil;

import android.view.View;

/**
 * Created by <B>ChenYao</B> on <B>2016/9/19</B>.
 * <br/>用来获取View以及View上的控件的holder
 */
public interface AdapterViewHolder {
    /**
     * Get view by id
     *
     * @param resId Get id by {@link SetText} or {@link SetImage}
     * @param <E>   extends View
     * @return view
     */
    <E extends View> E getView(int resId);

    /**
     * Get parent view
     *
     * @return parent view
     */
    View getMainView();
}
