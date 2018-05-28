package com.util.ming.projectutil.demo.bindservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;

public class BinderService extends Service {
    private String tag = "created";
    private OnResponse response;

    public BinderService() {
    }


    public class LocalBinder extends Binder {
        public BinderService getService() {
            return BinderService.this;
        }
    }

    public interface OnResponse {
        public void response(String str);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        tag = "started";
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                response.response(tag);
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public String getTag() {
        return tag;
    }

    public void setOnresponse(OnResponse response) {
        this.response = response;
    }
}
