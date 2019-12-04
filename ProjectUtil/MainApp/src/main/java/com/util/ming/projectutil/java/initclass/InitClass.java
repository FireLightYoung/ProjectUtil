package com.util.ming.projectutil.java.initclass;

public class InitClass {

    public static void main(String[] args) {
        Father father = new Father();
        System.out.println("-------------------------");
        Son son = new Son();
        System.out.println("-------------------------");
        Father father1 = new Son();
    }

}
