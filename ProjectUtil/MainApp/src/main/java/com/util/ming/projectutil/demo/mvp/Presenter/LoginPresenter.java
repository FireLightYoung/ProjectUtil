package com.util.ming.projectutil.demo.mvp.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.util.ming.projectutil.demo.mvp.entity.User;
import com.util.ming.projectutil.demo.mvp.loginbiz.ILoginBiz;
import com.util.ming.projectutil.demo.mvp.loginbiz.LoginBiz;
import com.util.ming.projectutil.demo.mvp.loginbiz.OnClearListener;
import com.util.ming.projectutil.demo.mvp.loginbiz.OnLoginListener;
import com.util.ming.projectutil.demo.mvp.view.ILoginView;


/**
 * Created by ming on 16/11/29.
 */
public class LoginPresenter {
    private ILoginView loginView;
    private ILoginBiz loginBiz;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginBiz = new LoginBiz();
    }

    public void login(User user) {
        loginView.ShowDialog();
        loginBiz.login(user, new OnLoginListener() {
            @Override
            public void loginSuccessed(User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.HideDialog();
                        loginView.showSuccess();
                    }
                });
            }

            @Override
            public void loginFailed(User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.HideDialog();
                        loginView.showFailed();
                    }
                });
            }


        });
    }


    public void clear() {
        loginView.ShowDialog();
        loginBiz.clear(new OnClearListener() {

            @Override
            public void clearSuccessed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.HideDialog();
                        loginView.clearSuccess();
                    }
                });
            }

            @Override
            public void clearFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.HideDialog();
                        loginView.clearFailed();
                    }
                });
            }
        });
    }

}
