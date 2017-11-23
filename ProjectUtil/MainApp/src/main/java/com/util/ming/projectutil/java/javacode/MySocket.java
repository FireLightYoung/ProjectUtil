package com.util.ming.projectutil.java.javacode;

/**
 * Created by ming on 17/11/8.
 */

public class MySocket extends Thread {

    public MySocketImp mImp;

    public MySocket(MySocketImp imp) {
        mImp = imp;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (mImp != null) {
            mImp.successed();
        }
    }
}
