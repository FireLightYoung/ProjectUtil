package com.util.ming.projectutil.demo.recycermanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.demo.recyclerdemo.RecyclerAdapter;

import java.util.List;

/**
 * Created by ming on 17/10/18.
 *
 * @author ming
 */

public class RecyclerManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RecylcerManagerBean> mList;

    public RecyclerManagerAdapter(Context context, List<RecylcerManagerBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recylcer_manager1, parent, false);
            holder = new RecViewHolder1(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recylcer_manager2, parent, false);
            holder = new RecViewHolder2(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecViewHolder1) {
            RecViewHolder1 holder1 = (RecViewHolder1) holder;
            holder1.mText.setText(mList.get(position).getName());
            holder1.mText.setOnClickListener(holder1);
        } else if (holder instanceof RecViewHolder2) {
            RecViewHolder2 holder2 = (RecViewHolder2) holder;
            holder2.mText.setText(mList.get(position).getName());
            holder2.mText.setOnClickListener(holder2);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class RecViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mText;

        public RecViewHolder1(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.item_recylcer_manager_tv1);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_recylcer_manager_tv1:
                    Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    class RecViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mText;

        public RecViewHolder2(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.item_recylcer_manager_tv2);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_recylcer_manager_tv2:
                    Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

}
