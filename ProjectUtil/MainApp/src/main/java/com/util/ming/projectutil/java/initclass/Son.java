package com.util.ming.projectutil.java.initclass;

public class Son extends Father {

    String s1 = printLogSon1();
    static String s2 = printLogSon2();

    {
        System.out.println("加载子类动态代码块");
    }

    static {
        System.out.println("加载子类静态代码块");
    }

    public String printLogSon1() {
        System.out.println("加载子类成员变量");
        return "";
    }

    public static String printLogSon2() {
        System.out.println("加载子类静态变量");
        return "";
    }

    public Son() {
        System.out.println("加载子类构造方法");
    }
}
