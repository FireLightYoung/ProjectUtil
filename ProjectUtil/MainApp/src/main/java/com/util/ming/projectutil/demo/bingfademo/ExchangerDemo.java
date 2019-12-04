package com.util.ming.projectutil.demo.bingfademo;


import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * task2进行检测，保证task1的数据上升到大于3才结束
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Task2(exchanger).start();
        new Task1(exchanger).start();
    }
}

class Task1 extends Thread {
    Exchanger<Integer> exchanger = null;

    public Task1(Exchanger<Integer> exchanger) {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int money = 0;
        for (int i = 0; i < 7; i++) {
            money += 1;//每次加1
            try {
                System.out.println("task1--exchange前");
                exchanger.exchange(money);//增加数据量大小
                System.out.println("task1--exchange后");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Task2 extends Thread {
    Exchanger<Integer> exchanger = null;
    int money = 0;

    public Task2(Exchanger<Integer> exchanger) {
        super();
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("task2--exchange前");
                money = exchanger.exchange(money);
                System.out.println("task2--exchange后");
                //因为循环task2比task1多用了money = exchanger.exchange(money)
                //导致第7个task2的exchanger.exchange(money);会一直阻塞在
                // 这里去等待task1再发exchange
                System.out.println(money > 3 ? "达到" + money + "成功结束！" : money + "未达到目标");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}