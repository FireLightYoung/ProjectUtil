package com.util.ming.projectutil.application;

import android.app.Application;

import com.util.ming.projectutil.utils.ContextHelper;

/**
 * Created by ming on 17/5/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextHelper.init(getApplicationContext());
    }
}
