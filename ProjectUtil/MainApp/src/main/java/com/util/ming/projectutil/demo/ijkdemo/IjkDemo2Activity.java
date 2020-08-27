package com.util.ming.projectutil.demo.ijkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.util.ming.projectutil.R;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkDemo2Activity extends Activity implements View.OnClickListener, SurfaceHolder.Callback {
    SurfaceView mSurfaceView;
    IjkMediaPlayer mPlayer;
    public final String TAG = "xiaoming";
    //    public final String path = "/storage/emulated/0/DCIM/Camera/1595234806729.mp4";
    public final String videoUrl = "/storage/emulated/0/DCIM/Camera/1593587623352.mp4";
//    String videoUrl = "https://v-cdn.zjol.com.cn/280443.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_two_demo);
        findViewById(R.id.ijk2_btn).setOnClickListener(this);
        initSurfaceView();
        initPlayer();
    }

    private void initSurfaceView() {
        mSurfaceView = findViewById(R.id.ijk2_video_surface);
        mSurfaceView.getHolder().addCallback(this);
    }

    private void initPlayer() {
        mPlayer = new IjkMediaPlayer();
        try {
            String path = videoUrl;
            mPlayer.setDataSource(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.prepareAsync();
        mPlayer.start();
    }

    @Override
    public void onClick(View view) {


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}