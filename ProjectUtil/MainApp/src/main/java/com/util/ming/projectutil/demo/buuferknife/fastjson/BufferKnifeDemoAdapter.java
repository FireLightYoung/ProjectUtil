package com.util.ming.projectutil.demo.buuferknife.fastjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.util.ming.projectutil.R;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ming on 17/12/6.
 */

public class BufferKnifeDemoAdapter extends BaseAdapter {
    private Context context;
    LayoutInflater inflater;

    BufferKnifeDemoAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {

            view = inflater.inflate(R.layout.fragment_bufferknife, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.name.setText("John Doe");
        // etc...

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.textView_buffer_fragment)
        TextView name;
        @BindView(R.id.btn_buffer_fragment)
        Button jobTitle;

        public ViewHolder(View view) {
            //在此处进行bufferknife初始化
            ButterKnife.bind(this, view);
        }
    }

}
