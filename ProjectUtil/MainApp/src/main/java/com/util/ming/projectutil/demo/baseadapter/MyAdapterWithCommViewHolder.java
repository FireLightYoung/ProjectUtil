package com.util.ming.projectutil.demo.baseadapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.demo.baseadapter.utils.ViewHolder;

import java.util.List;

/**
 * 类注释
 *
 * @author Yang
 * @date 2015-8-5 下午10:03:45
 */
public class MyAdapterWithCommViewHolder extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Bean> mDatas;
    private Context mContext;
    private int mLayoutId;

    public MyAdapterWithCommViewHolder(Context context, List<Bean> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId, position);
        Bean bean = mDatas.get(position);

        ((TextView) holder.getView(R.id.tv_title)).setText(bean.getTitle());
        ((TextView) holder.getView(R.id.tv_desc)).setText(bean.getDesc());
        ((TextView) holder.getView(R.id.tv_time)).setText(bean.getTime());
        ((TextView) holder.getView(R.id.tv_phone)).setText(bean.getPhone());

        return holder.getConvertView();
    }


    /**
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }
}
