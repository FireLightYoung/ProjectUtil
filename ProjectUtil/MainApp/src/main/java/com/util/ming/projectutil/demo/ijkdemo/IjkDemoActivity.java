package com.util.ming.projectutil.demo.ijkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.util.ming.projectutil.R;

import java.io.IOException;

public class IjkDemoActivity extends Activity implements View.OnClickListener {
    IjkplayerView video_content;

    public final String TAG = "xiaoming";
    //    public final String path = "/storage/emulated/0/DCIM/Camera/1595234806729.mp4";
        public final String videoUrl = "/storage/emulated/0/DCIM/Camera/1593587623352.mp4";
//    String videoUrl = "https://v-cdn.zjol.com.cn/280443.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_demo);
        findViewById(R.id.ijk_btn).setOnClickListener(this);
        video_content = findViewById(R.id.video_content);
        video_content.setPath(videoUrl);
        try {
            video_content.load();
            video_content.start();
        } catch (IOException e) {
            Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {


    }

}