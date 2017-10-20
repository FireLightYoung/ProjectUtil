package com.util.ming.projectutil.demo.recyclerdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecViewHolder> {

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecViewHolder extends RecyclerView.ViewHolder {

        public RecViewHolder(View itemView) {
            super(itemView);
        }
    }

}
