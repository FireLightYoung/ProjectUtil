package com.util.ming.projectutil.demo.recyclerdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/10/18.
 *
 * @author ming
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecViewHolder> {

    private Context mContext;
    private List<RecylcerBean> mList;

    public RecyclerAdapter(Context context, List<RecylcerBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recylcer_one, parent, false);

        RecViewHolder holder = new RecViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        holder.mText.setText(mList.get(position).getName());
        holder.mText.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class RecViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mText;

        public RecViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.item_recylcer_one_tv);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_recylcer_one_tv:
                    Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

}
