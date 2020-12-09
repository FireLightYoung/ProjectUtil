package com.util.ming.projectutil.demo.recycermanager;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/5/9.
 */

public class RecylcerManagerActivity extends BaseActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    RecyclerManagerAdapter mAdapter;
    List<RecylcerManagerBean> mData;
    Button recylcer_btn;
    Button recylcer_btn1;
    Button recylcer_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer_manager);
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RecylcerManagerBean company = null;
//            if ((i % 5) == 0) {
            company = new RecylcerManagerBean("名字==" + i, "i=" + i, i % 2);


//            } else {
//                company = new RecylcerBean("目录==" + i);

//            }
            mData.add(company);
        }
    }

    private void initView() {
        recylcer_btn = (Button) findViewById(R.id.recylcer_manager_btn);
        recylcer_btn.setOnClickListener(this);
        recylcer_btn1 = (Button) findViewById(R.id.recylcer_manager_btn1);
        recylcer_btn1.setOnClickListener(this);
        recylcer_btn2 = (Button) findViewById(R.id.recylcer_manager_btn2);
        recylcer_btn2.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_manager_recylcer_rv);
        mRecyclerView.setLayoutManager(new Custom2LayoutManager());
        mAdapter = new RecyclerManagerAdapter(this, mData);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recylcer_manager_btn:
//                RecylcerBean company = new RecylcerBean("新加入--===", "999");
//
//                mData.add(1, company);
                mAdapter.notifyItemInserted(1);

                break;
        }
        switch (view.getId()) {
            case R.id.recylcer_manager_btn1:
//                RecylcerBean company = new RecylcerBean("新加入--===", "999");
//
//                mData.add(1, company);
                mAdapter.notifyItemInserted(1);

                break;
        }
        switch (view.getId()) {
            case R.id.recylcer_manager_btn2:
//                RecylcerBean company = new RecylcerBean("新加入--===", "999");
//
//                mData.add(1, company);
                mAdapter.notifyItemInserted(1);

                break;
        }
    }
}
