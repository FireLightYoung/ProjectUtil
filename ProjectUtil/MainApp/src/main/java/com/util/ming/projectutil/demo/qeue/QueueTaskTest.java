package com.util.ming.projectutil.demo.qeue;


import java.util.concurrent.ArrayBlockingQueue;


/**
 * 阻塞队列ArrayBlockingQueue
 * <p>
 * 　　add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
 * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
 * 　　peek       返回队列头部的元素             如果队列为空，则返回null
 * 　　put         添加一个元素                      如果队列满，则阻塞
 * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
 */
public class QueueTaskTest {

    public ArrayBlockingQueue<MyTask> mQueue = new ArrayBlockingQueue<MyTask>(1);

    public static void main(String[] args) {
        method2();
    }

    private static void method1() {


        final ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);

        new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    int num = queue.poll();
                    System.out.printf("移出数字num=" + num + "...\n");


                }

            }
        }.start();
        for (int i = 0; i < 10; i++) {
            System.out.printf("放入数字i=" + i + "...");
            boolean b = false;
            try {
                queue.put(i);
                System.out.printf("放入数字成功\n");
//                if (b) {
//                    System.out.printf("放入数字成功\n");
//                } else {
//                    System.out.printf("放入数字失败\n");
//
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }


    private static void method2() {
        final QueueTaskTest test = new QueueTaskTest();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (true) {
                        System.out.printf("\n获取一次队列元素");
                        MyTask myTask = test.mQueue.take();
                        System.out.printf("\n获取队列元素完成");
                        new Thread(myTask).start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.printf("队列取出异常");

                }

            }
        }.start();


        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.putTask(i);

        }

    }

    public void putTask(int num) {

        MyTask task = new MyTask(num);

        try {
            System.out.printf("\n发起一次请求，任务编号为..." + num);
            mQueue.offer(task);
            System.out.printf("\n放入队列成功，任务编号为..." + num);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("队列放入异常");

        }


    }

    class MyTask implements Runnable {
        int num;

        public MyTask(int num) {
            this.num = num;
        }

        @Override
        public void run() {


            System.out.printf("\n开始执行任务..." + num + "\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("\n结束执行任务..." + num + "\n");


        }
    }

}
