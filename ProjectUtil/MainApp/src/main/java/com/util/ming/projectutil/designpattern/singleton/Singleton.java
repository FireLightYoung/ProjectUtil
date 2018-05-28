package com.util.ming.projectutil.designpattern.singleton;

/**
 * Created by ming on 17/8/21.
 * 静态内部类，最合适的单例模式实现方法
 * 但是不支持反序列化生成多个实例，如果避免这个问题的话要用枚举实现单例的形式
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
