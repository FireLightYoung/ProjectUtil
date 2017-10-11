package com.util.ming.projectutil.demo.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

public class LayoutDemoActivity extends BaseActivity implements View.OnClickListener {

    Button includebtn1;
    Button includebtn2;
    Button mergebtn1;
    Button mergebtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_demo);
        includebtn1 = (Button) (findViewById(R.id.include_layout).findViewById(R.id.activity_include_btn1));
        includebtn2 = (Button) (findViewById(R.id.include_layout).findViewById(R.id.activity_include_btn2));
        mergebtn1 = (Button) findViewById(R.id.activity_merge_btn1);
        mergebtn2 = (Button) findViewById(R.id.activity_merge_btn2);
        includebtn1.setOnClickListener(this);
        includebtn2.setOnClickListener(this);
        mergebtn1.setOnClickListener(this);
        mergebtn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
