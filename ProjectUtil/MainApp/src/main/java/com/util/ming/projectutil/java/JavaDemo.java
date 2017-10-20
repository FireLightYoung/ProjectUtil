package com.util.ming.projectutil.java;

import java.util.ArrayList;

/**
 * Created by ming on 17/8/21.
 */

public class JavaDemo {


    public static void main(String[] args) {
        JavaDemo mDemo = new JavaDemo();

        System.out.println("thread=a=" + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("thread=b=" + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    System.out.println("sleep异常");
//                    e.printStackTrace();
//                }

                for (int i = 0; ; i++) {
                    if (Thread.interrupted()) {
                        System.out.println("线程停止");
                        break;
                    }
                    System.out.println("i=" + i);
                }
                System.out.println("其他程序");
            }
        };
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("暂停");
        thread.suspend();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("恢复");
        thread.resume();
    }
}
