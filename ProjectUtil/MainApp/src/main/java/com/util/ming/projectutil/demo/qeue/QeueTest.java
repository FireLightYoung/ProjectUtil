package com.util.ming.projectutil.demo.qeue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by ming on 17/8/21.
 */

public class QeueTest {
    public static LinkedBlockingDeque<DoSomething> qeue;

    public static void main(String[] args) {
        System.out.printf("打印-----开始");

        qeue = new LinkedBlockingDeque<>(1);
        List<DoSomething> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DoSomething d = new DoSomething("数据-----" + i);
            list.add(d);
        }

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        DoSomething d = qeue.take();
                        d.print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        for (int i = 0; i < list.size(); i++) {
            try {
                qeue.put(list.get(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}

class DoSomething {

    private String printString;

    public DoSomething(String printString) {
        this.printString = printString;
    }

    public void print() {
        System.out.println("打印-----" + printString);
    }
}

