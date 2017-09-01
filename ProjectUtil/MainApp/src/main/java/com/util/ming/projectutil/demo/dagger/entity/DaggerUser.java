package com.util.ming.projectutil.demo.dagger.entity;

/**
 * Created by ming on 16/11/29.
 */
public class DaggerUser {
    String userName;
    String userPassword;

    public DaggerUser() {
    }

    public DaggerUser(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
