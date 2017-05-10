package com.util.ming.projectutil.demo.mvp.loginbiz;


import com.util.ming.projectutil.demo.mvp.entity.User;

/**
 * Created by ming on 16/11/29.
 * moudel
 */
public class LoginBiz implements ILoginBiz {


    @Override
    public void login(final User user, final OnLoginListener listener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (user.getUserName().equals("zhangsan") && user.getUserPassword().equals("123")) {
                    listener.loginSuccessed(user);
                } else {
                    listener.loginFailed(user);
                }
            }
        }.start();
    }

    @Override
    public void clear(final OnClearListener listener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listener.clearSuccessed();
            }
        }.start();
    }

}
