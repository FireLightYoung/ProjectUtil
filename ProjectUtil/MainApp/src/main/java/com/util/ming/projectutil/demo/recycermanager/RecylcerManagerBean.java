package com.util.ming.projectutil.demo.recycermanager;

/**
 * Created by ming on 17/10/18.
 */

public class RecylcerManagerBean {
    String name;
    String code;

    int type;


    public RecylcerManagerBean() {
    }

    public RecylcerManagerBean(String name) {
        this.name = name;
    }

    public RecylcerManagerBean(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public RecylcerManagerBean(String name, String code, int type) {
        this.name = name;
        this.code = code;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
