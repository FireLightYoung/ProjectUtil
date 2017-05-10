package com.util.ming.projectutil.demo.mvp.loginbiz;


import com.util.ming.projectutil.demo.mvp.entity.User;

/**
 * Created by ming on 16/11/29.
 */
public interface OnLoginListener {
    public void loginSuccessed(User user);

    public void loginFailed(User user);
}
