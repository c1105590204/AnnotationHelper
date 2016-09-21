package com.chenyao.annotationutil.baseadapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用adapters
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas;
    protected int mItemLayoutId;

    public CommonAdapter(Context context, List<T> datas, int itemLayoutId) {
        super();
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mItemLayoutId = itemLayoutId;
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = 0;
        if (null != mDatas && mDatas.size() > 0) {
            count = mDatas.size();
        }
        return count;
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<T> list) {
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        this.position = position;
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    /**
     * Ϊitem����ֵ
     *
     * @param helper
     * @param item
     */
    public abstract void convert(ViewHolder helper, T item, int position);

    protected int position;

    /**
     * ��ȡviewholder
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

}
