package com.util.ming.projectutil.java.ifelse;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Optional;

public class IfElseDemo {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        IfElseDemo demo1 = new IfElseDemo();
        IfElseDemo demo2 = null;
        Optional<IfElseDemo> optional = Optional.ofNullable(demo1);
//        optional.map(method1()).orElse(method2());
    }


    public static void method1() {

    }

    public static void method2() {

    }

}
