package com.util.ming.projectutil.singleton;

/**
 * Created by ming on 17/8/21.
 * 静态内部类，最合适的单例模式实现方法
 */
public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        return Nested.instance;
    }

    //在第一次被引用时被加载
    static class Nested {
        private static Singleton instance = new Singleton();
    }

    public static void main(String args[]) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
    }
}
