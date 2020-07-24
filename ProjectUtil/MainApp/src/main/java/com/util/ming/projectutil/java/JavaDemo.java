package com.util.ming.projectutil.java;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ming on 17/8/21.
 */

public class JavaDemo {


    public static void main(String[] args) {
        method7();
    }

    private static void method2() {
        String s = "123";
        s = null;
        method3(s);
    }

    private static void method3(String s) {
        assert s != null : "s 不能是null";
        System.out.println(s);
    }


    private static void method1() {
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

    public static void method5() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long time1 = System.currentTimeMillis();
        System.out.println(time1);
    }

    public static void method6() {
        String aaa = null;
        if ("".equals(aaa)) {
            System.out.println("123");

        } else {
            System.out.println("321");

        }
    }

    public static void method7() {
        HashMap<Integer, String> hashMap = new HashMap<>();

        hashMap.put(1, "a");
        hashMap.put(1, "b");
        hashMap.put(1, "c");
        hashMap.put(1, "d");
        hashMap.put(1, "e");
        System.out.println(hashMap.get(1));


    }
}
