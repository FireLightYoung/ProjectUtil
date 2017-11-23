package com.util.ming.projectutil.demo.activitystack;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.util.ming.projectutil.R;

/**
 * Created by ming on 17/11/22.
 */
public class EStackActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_a);

        Button btn1 = (Button) findViewById(R.id.activity_stack_button1);
        Button btn2 = (Button) findViewById(R.id.activity_stack_button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
