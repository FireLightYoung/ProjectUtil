package com.util.ming.projectutil.demo.jishiqi;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.util.ming.projectutil.R;

public class CountTimerActivity extends Activity {

    Chronometer count_tv;
    Button button_chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_timer);
        count_tv = (Chronometer) findViewById(R.id.count_chronometer);
        button_chronometer = findViewById(R.id.button_chronometer);
        button_chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        count_tv.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer ch) {
                // 如果从开始计时到现在超过了60s
                if (SystemClock.elapsedRealtime() - ch.getBase() > 60 * 1000) {
                    ch.stop();
                }
            }
        });
    }


    /**
     *
     */
    public void start() {
        long a = SystemClock.elapsedRealtime();
        count_tv.setBase(a);
        count_tv.start();
    }

}
