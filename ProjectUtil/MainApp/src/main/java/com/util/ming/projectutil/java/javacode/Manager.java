package com.util.ming.projectutil.java.javacode;

/**
 * Created by ming on 17/11/8.
 */

public class Manager extends Thread {

    public Request mRequest;
    public Response mResponse;


    public Manager(Request request) {
        mRequest = request;
    }

    @Override
    public void run() {



    }
}
