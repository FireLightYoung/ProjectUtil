package com.util.ming.projectutil.demo.volatiledemo;

public class TestVolatile {

    public static void main(String[] args) {
        Aobing a = new Aobing();
        a.start();
        for (; ; ) {
            if (a.isFlag()) {
                System.out.println("更改成功！！！！！");
            }
        }

    }

}

class Aobing extends Thread {
    boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {
        super.run();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("更改flag-----");
    }
}