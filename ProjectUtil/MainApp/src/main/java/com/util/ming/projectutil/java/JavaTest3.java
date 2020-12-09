package com.util.ming.projectutil.java;

import android.util.Log;

public class JavaTest3 {

    public static void main(String[] args) {

        test1();

    }

    private static void test1() {
        String s = "abcdefghijklmn";
        String s2 = s.substring(3);
        System.out.println("s2=" + s2);
    }
}
