package com.util.ming.projectutil.application;

import android.app.Application;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.util.ming.projectutil.MainActivity;
import com.util.ming.projectutil.logutil.CrashHandler;
import com.util.ming.projectutil.utils.ContextHelper;

import java.lang.Thread.UncaughtExceptionHandler;


/**
 * Created by ming on 17/5/10.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextHelper.init(getApplicationContext());
        Fresco.initialize(this);

        CrashHandler crashHandler= CrashHandler.getInstance();
        crashHandler.init(this);


        Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程  以下用来捕获程序崩溃异常
    }// 创建服务用于捕获崩溃异常

    private UncaughtExceptionHandler restartHandler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            restartApp();//发生崩溃异常时,重启应用
        }
    };

    public void restartApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }
}
