package com.util.ming.projectutil.demo.videoviewdemo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.VideoView;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.utils.onDoubleClickListener;

public class VideoViewDemoActivity extends Activity {

    Button button_videoplayer;
    VideoView vv_act;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        button_videoplayer = findViewById(R.id.button_videoplayer);
        vv_act = findViewById(R.id.vv_act);

        button_videoplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayer();
            }
        });
    }


    /**
     *
     */
    public void startPlayer() {
        String path = "/storage/emulated/0/icbcim/video/123.mp4";
        vv_act.setVideoPath(path);
        vv_act.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer player, int i, int i1) {
                return false;
            }
        });
        vv_act.start();
    }

}
