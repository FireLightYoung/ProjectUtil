package com.util.ming.projectutil.demo.recyclerdemo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/5/9.
 */

public class RecylcerActivity extends BaseActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    RecyclerAdapter mAdapter;
    List<RecylcerBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer);
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RecylcerBean company = null;
//            if ((i % 5) == 0) {
            company = new RecylcerBean("名字==" + i, "i=" + i);
//            } else {
//                company = new RecylcerBean("目录==" + i);

//            }
            mData.add(company);
        }
    }

    private void initView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_recylcer_rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new RecyclerAdapter(this, mData);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
