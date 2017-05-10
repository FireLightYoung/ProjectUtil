package com.util.ming.projectutil.demo.mvp.loginbiz;


import com.util.ming.projectutil.demo.mvp.entity.User;

/**
 * Created by ming on 16/11/29.
 * 进行与moudel连接的接口
 */
public interface ILoginBiz {
    /**
     * 登录功能
     *
     * @param user
     * @param listener
     */
    public void login(final User user, final OnLoginListener listener);

    /**
     * 清除功能
     *
     * @param listener
     */
    public void clear(final OnClearListener listener);

}
