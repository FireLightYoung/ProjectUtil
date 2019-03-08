package com.util.ming.projectutil.java;


import java.io.File;

public class JavaCode2 {
    public static void main(String[] args) {
        mehod1();
    }


    public static void mehod1() {
        Hunman men = Hunman.MAN;
        System.out.println("men==" + men.getName());
    }
}

enum Hunman {
    MAN {
        @Override
        public String getName() {
            return "Jack";
        }
    },
    WOMAN {
        @Override
        public String getName() {
            return "Lucy";
        }
    };

    public abstract String getName();
}