package com.util.ming.projectutil.demo.bindservicedemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Service;

import com.util.ming.projectutil.R;

import java.util.List;

/**
 * Created by ming on 17/11/22.
 */
public class ServiceDemoActivity extends Activity {


    public BinderService service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack_a);


        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);


        Button btn1 = (Button) findViewById(R.id.activity_stack_button1);
        Button btn2 = (Button) findViewById(R.id.activity_stack_button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemoActivity.this, BinderService.class);
                bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder services) {
            BinderService.LocalBinder binder = (BinderService.LocalBinder) services;
            service = binder.getService();
            service.setOnresponse(new BinderService.OnResponse() {
                @Override
                public void response(String str) {
                    Log.i("yang", "第二次回调==binder==" + str);

                }
            });
            Log.i("yang", "回调==binder==" + binder.getService().getTag());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
        }
    };

}
