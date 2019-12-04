package com.util.ming.projectutil.java.initclass;

public class Father {


    String str = printLog1();
    static String str2 = printLog2();

    {
        System.out.println("加载父类动态代码块");
    }

    static {
        System.out.println("加载父类静态代码块");
    }

    public String printLog1() {
        System.out.println("加载父类成员变量");
        return "";
    }

    public static String printLog2() {
        System.out.println("加载父类静态变量");
        return "";
    }

    public Father() {
        System.out.println("加载父类构造方法");
    }
}
