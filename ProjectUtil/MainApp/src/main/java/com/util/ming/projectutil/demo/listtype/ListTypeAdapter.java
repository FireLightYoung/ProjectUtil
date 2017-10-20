package com.util.ming.projectutil.demo.listtype;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/10/18.
 *
 * @author ming
 */

public class ListTypeAdapter extends BaseAdapter {

    /**
     * Item类型,int值.必须从0开始依次递增.
     */
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CONTENT = 1;

    /**
     * Item Type 的数量
     */
    private static final int TYPE_ITEM_COUNT = 2;

    /**
     * 数据
     */
    private List<Company> mData = new ArrayList<>();
    private Context context;


    public ListTypeAdapter(Context context, List<Company> mData) {
        this.context = context;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        /**
         * 不同类型的ViewHolder
         * */
        TitleViewHolder titleViewHolder = null;
        CompanyViewHolder contentViewHolder = null;
        /**
         * 对类型进行判断,分别inflate不同的布局.
         * */
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                titleViewHolder = new TitleViewHolder();
                if (convertView == null) {
                    Log.i("yang", "View.inflate -->R.layout.item_company_index");
                    convertView = View.inflate(context, R.layout.item_company_index, null);
                    titleViewHolder.title = (TextView) convertView.findViewById(R.id.tv_listadapter);
                    //setTag()
                    convertView.setTag(titleViewHolder);
                } else {
                    //getTag();
                    titleViewHolder = (TitleViewHolder) convertView.getTag();
                }
                titleViewHolder.setPosition(position);
                titleViewHolder.title.setOnClickListener(titleViewHolder);
                titleViewHolder.title.setText(mData.get(position).getName());
                break;
            case TYPE_CONTENT:
                contentViewHolder = new CompanyViewHolder();
                if (convertView == null) {
                    Log.i("yang", "View.inflate -->R.layout.item_company");
                    convertView = View.inflate(context, R.layout.item_company, null);
                    contentViewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
                    convertView.setTag(contentViewHolder);
                } else {
                    contentViewHolder = (CompanyViewHolder) convertView.getTag();
                }
                contentViewHolder.setPosition(position);
                contentViewHolder.content.setOnClickListener(contentViewHolder);
                contentViewHolder.content.setText(mData.get(position).getCode());
                break;
            default:
                contentViewHolder = new CompanyViewHolder();
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item_company, null);
                    contentViewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
                    convertView.setTag(contentViewHolder);
                } else {
                    contentViewHolder = (CompanyViewHolder) convertView.getTag();
                }
                contentViewHolder.content.setText(mData.get(position).getCode());
                break;
        }
        return convertView;
    }

    /**
     * 根据position获取Item的类型
     */
    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(mData.get(position).getCode())) {
            return TYPE_TITLE;
        } else {
            return TYPE_CONTENT;
        }
    }

    /**
     * 返回Item Type的总数量
     */
    @Override
    public int getViewTypeCount() {
        return TYPE_ITEM_COUNT;
    }

    class TitleViewHolder extends ViewHolder {
        TextView title;
    }

    class CompanyViewHolder extends ViewHolder {
        TextView content;
    }

    class ViewHolder implements View.OnClickListener {
        int position;

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return this.position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_listadapter:
                    Toast.makeText(context, "tv_listadapter==" + getPosition(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_content:
                    Toast.makeText(context, "tv_content==" + getPosition(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
