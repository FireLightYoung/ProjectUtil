package com.util.ming.projectutil.demo.finduser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/9/26.
 * <p>
 * 原型模式，保持获取的数据一直是安全的
 */
public class User implements Cloneable {


    private String name;
    private String pwd;
    private ArrayList<String> list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public User clone() {
        try {
            User user = (User) super.clone();
            user.name = this.name;
            user.pwd = this.pwd;
            user.list = (ArrayList<String>) this.list.clone();
            return user;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
