package com.util.ming.projectutil.java.javacode;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by ming on 17/11/8.
 */

public class SocketTest {


    public static void main(String[] args) {
        BlockingDeque<Manager> mDeque = new LinkedBlockingDeque<>();
        try {
            mDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
