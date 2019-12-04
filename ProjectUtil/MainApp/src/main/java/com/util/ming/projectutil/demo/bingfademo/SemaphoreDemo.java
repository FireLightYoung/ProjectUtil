package com.util.ming.projectutil.demo.bingfademo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore类常用的方法：
 * <p>
 * acquire():获取一个许可证,
 * <p>
 * release():释放许可证,这时候有多余的线程就加入到线程池中
 * <p>
 * tryAcquire():尝试获取许可证
 * <p>
 * intavailblePermits()：返回此信号量当前可用的许可证数
 * <p>
 * intgetQueueLength()：返回正在等待获取许可证的线程数
 * <p>
 * hasQueuedThreads():是否有线程正在等待获取许可证
 * <p>
 * reducePermits(int reduction)：减少reduction个许可证,
 * <p>
 * getQueueThreads():返回所有等待获取许可证的线程集合
 * <p>
 * Semaphore类的构造函数中传入的数,表示同时并发访问控制在多少个线程.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(2);
        final int count = 5;
        for (int i = 0; i < count; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("线程:" + Thread.currentThread().getName() + "开始下载");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("线程:" + Thread.currentThread().getName() + "下载完毕");
                        semaphore.release();
                    }
                }
            };
            service.execute(runnable);//放在线程池中去维护
        }
        service.shutdown();
    }
}