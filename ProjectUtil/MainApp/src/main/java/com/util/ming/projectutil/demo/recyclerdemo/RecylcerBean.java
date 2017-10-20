package com.util.ming.projectutil.demo.recyclerdemo;

/**
 * Created by ming on 17/10/18.
 */

public class RecylcerBean {
    String name;
    String code;

    public RecylcerBean() {
    }

    public RecylcerBean(String name) {
        this.name = name;
    }

    public RecylcerBean(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
