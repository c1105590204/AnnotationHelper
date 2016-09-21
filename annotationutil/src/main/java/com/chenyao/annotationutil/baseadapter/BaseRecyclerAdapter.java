package com.chenyao.annotationutil.baseadapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenyao.annotationutil.AdapterViewHolder;

import java.util.List;

/**
 * Created by ChenYao on 2016/4/26.
 * <br/>
 * Introduction:基本recycler适配器
 */
public abstract class BaseRecyclerAdapter<E> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {
    private List<E> list;
    private int layout;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private RecyclerItemClickListener<E> itemClickListener;

    public void setItemClickListener(RecyclerItemClickListener<E> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BaseRecyclerAdapter(Activity activity, List<E> list, int layout) {
        this.list = list;
        this.activity = activity;
        layoutInflater = activity.getLayoutInflater();
        this.layout = layout;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(layout, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(holder.itemView, list.get(position), position);
                }
            });
        }
        convert(holder, list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public abstract void convert(BaseViewHolder viewHolder, E item, int position);

    public interface RecyclerItemClickListener<E> {
        void onItemClick(View view, E item, int position);
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder implements AdapterViewHolder {

        public View itemView;
        private SparseArray<View> views;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            views = new SparseArray<>();
        }

        @Override
        public <V extends View> V getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return (V) view;
        }

        @Override
        public View getMainView() {
            return itemView;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public BaseViewHolder setText(int viewId, CharSequence text) {
            TextView view = getView(viewId);
            if (null != text) {
                view.setText(text);
            } else {
                view.setText("");
            }
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param drawableId
         * @return
         */
        public BaseViewHolder setImageResource(int viewId, int drawableId) {
            ImageView view = getView(viewId);
            view.setImageResource(drawableId);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param bm
         * @return
         */
        public BaseViewHolder setImageBitmap(int viewId, Bitmap bm) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
            return this;
        }

        /**
         * 设置控件是否显示
         *
         * @param viewId
         * @param visibility
         * @return
         */
        public BaseViewHolder setViewVisible(int viewId, int visibility) {
            View v = getView(viewId);
            v.setVisibility(visibility);
            return this;
        }
    }
}