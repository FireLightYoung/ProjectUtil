package com.util.ming.projectutil.java.javacode;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ming on 17/11/9.
 */

public class ThreadLocalTest {

    public static ThreadLocal<Date> mLocal = new ThreadLocal();


    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) {
//        try {
//
//            ThreadB b = new ThreadB();
//            b.start();
//
//            Thread.sleep(1000);
//
//            ThreadC c = new ThreadC();
//            c.start();
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ThreadD d = new ThreadD();
        d.start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadE e = new ThreadE();
        e.start();
    }


}


class ThreadB extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 20; i++) {
                if (ThreadLocalTest.mLocal.get() == null) {
                    ThreadLocalTest.mLocal.set(new Date());
                }

                System.out.println("B  " + ThreadLocalTest.mLocal.get().getTime());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadC extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 20; i++) {
                if (ThreadLocalTest.mLocal.get() == null) {
                    ThreadLocalTest.mLocal.set(new Date());
                }

                System.out.println("C  " + ThreadLocalTest.mLocal.get().getTime());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class ThreadD extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("lock");
            ThreadLocalTest.lock.lock();
            System.out.println("await");
            ThreadLocalTest.condition.await();
            System.out.println("next");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("unlock");
            ThreadLocalTest.lock.unlock();
        }
    }
}

class ThreadE extends Thread {
    @Override
    public void run() {
        ThreadLocalTest.lock.lock();
        System.out.println("signal");
        ThreadLocalTest.condition.signal();
        ThreadLocalTest.lock.unlock();
    }
}