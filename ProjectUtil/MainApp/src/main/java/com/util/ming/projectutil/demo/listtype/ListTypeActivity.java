package com.util.ming.projectutil.demo.listtype;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;
import com.util.ming.projectutil.demo.mvp.Presenter.LoginPresenter;
import com.util.ming.projectutil.demo.mvp.entity.User;
import com.util.ming.projectutil.demo.mvp.view.ILoginView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/5/9.
 */

public class ListTypeActivity extends BaseActivity implements View.OnClickListener {

    ListView mListView;
    ListTypeAdapter mAdapter;
    List<Company> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtype);
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Company company = null;
            if ((i % 5) == 0) {
                company = new Company("名字==" + i, "i=" + i);
            } else {
                company = new Company("目录==" + i);

            }
            mData.add(company);
        }
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.activity_listtype_list);
        mAdapter = new ListTypeAdapter(this, mData);
        mListView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
