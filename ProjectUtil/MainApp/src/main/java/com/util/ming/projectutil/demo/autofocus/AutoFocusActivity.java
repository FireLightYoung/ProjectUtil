package com.util.ming.projectutil.demo.autofocus;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.util.ming.projectutil.R;

/**
 * Created by ming on 17/5/10.
 * 停止声音
 */

public class AutoFocusActivity extends Activity {

    //用于录制时关闭音乐，这里使用的是AudioManager的竞争AudioFocus的方式
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //竞争AudioFocus,停止其他音乐播放
        mAudioManager.requestAudioFocus(null, AudioManager.STREAM_RING, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //让出AudioFocus
        mAudioManager.abandonAudioFocus(null);
    }
}
