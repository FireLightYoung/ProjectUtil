package com.util.ming.projectutil.demo.mvp.view;

/**
 * Created by ming on 16/11/29.
 */
public interface ILoginView {

    public String getUserName();

    public String getPassword();

    public void ShowDialog();

    public void HideDialog();

    public void showSuccess();

    public void showFailed();

    public void clearSuccess();

    public void clearFailed();

}
