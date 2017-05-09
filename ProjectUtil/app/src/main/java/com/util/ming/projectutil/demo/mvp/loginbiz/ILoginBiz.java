package com.util.ming.projectutil.demo.mvp.loginbiz;


import com.util.ming.projectutil.demo.mvp.entity.User;

/**
 * Created by ming on 16/11/29.
 */
public interface ILoginBiz {
    public void login(final User user, final OnLoginListener listener);

    public void clear(final OnClearListener listener);

}
