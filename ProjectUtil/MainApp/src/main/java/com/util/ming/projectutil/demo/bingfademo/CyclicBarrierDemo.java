package com.util.ming.projectutil.demo.bingfademo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    public static void main(String[] args) throws InterruptedException {

        String[] strs = {"AAA", "BBB", "CCC"};
        CyclicBarrier barrier = new CyclicBarrier(strs.length);
        for (int i = 0; i < strs.length; i++) {
            new Thread(new Employee(strs[i], barrier)).start();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }


}

class Employee implements Runnable {
    private String name;
    private CyclicBarrier barrier;

    public Employee(String name, final CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println(name + "已达到目标");
            barrier.await(); //等待最后一个线程到达（底层会去判断）
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name + "结束");
    }
}
