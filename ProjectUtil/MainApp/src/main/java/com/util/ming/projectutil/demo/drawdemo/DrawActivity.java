package com.util.ming.projectutil.demo.drawdemo;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

/**
 * Created by ming on 17/5/9.
 *
 * @author ming
 */

public class DrawActivity extends BaseActivity {
    private TextView mTextView;
    private DrawUI mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        initData();
        initView();
        initEvent();
    }

    private void initData() {

    }

    private void initView() {

//        mTextView = (TextView) findViewById(R.id.textView_draw);
        mSurfaceView = (DrawUI) findViewById(R.id.surfaceVew_draw);
    }

    private void initEvent() {
    }


}
