package com.util.ming.projectutil.designpattern.singleton;

/**
 * Created by ming on 17/8/21.
 * 通过枚举实现单例
 */

public enum SingletonEnum {
    INSTANCE;

    public void method1() {

    }

    public void method2() {

    }

    public int method3() {
        System.out.println("INSTANCE.method3");
        return 0;
    }

    public static void main(String args[]) {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;
        SingletonEnum.INSTANCE.method3();
        System.out.println(instance == instance2);
    }
}
