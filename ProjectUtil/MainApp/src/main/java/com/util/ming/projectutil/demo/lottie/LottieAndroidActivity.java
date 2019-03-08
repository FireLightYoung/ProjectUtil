package com.util.ming.projectutil.demo.lottie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.util.ming.projectutil.R;

public class LottieAndroidActivity extends Activity implements View.OnClickListener {
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_android);
        mButton1 = (Button) findViewById(R.id.btn_lottie1);
        mButton2 = (Button) findViewById(R.id.btn_lottie2);
        mButton3 = (Button) findViewById(R.id.btn_lottie3);
        mButton4 = (Button) findViewById(R.id.btn_lottie4);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lottie1:
                break;
            case R.id.btn_lottie2:
                break;
            case R.id.btn_lottie3:
                break;
            case R.id.btn_lottie4:
                break;
        }
    }
}
