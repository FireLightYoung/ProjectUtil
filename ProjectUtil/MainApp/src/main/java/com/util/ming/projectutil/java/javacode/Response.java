package com.util.ming.projectutil.java.javacode;

/**
 * Created by ming on 17/11/8.
 */

public class Response {

    private int id;
    private int code;
    private Message msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }
}
