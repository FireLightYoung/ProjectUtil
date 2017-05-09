package com.util.ming.projectutil.demo.mvp.view;

/**
 * Created by ming on 16/11/29.
 * 显示View的接口，显示所有登录相关功能View层的操作和响应接口
 * （当结构比较复杂时，也可以将操作和响应的接口方法分别分开至两个接口中）
 * ***一半保证此接口的实现运行在UI线程中
 */
public interface ILoginView {

    /**
     * 获取TextView的登录名
     *
     * @return
     */
    public String getUserName();

    /**
     * 获取TextView的密码
     *
     * @return
     */
    public String getPassword();

    /**
     * 显示加载中
     */
    public void ShowDialog();

    /**
     * 关闭显示加载中
     */
    public void HideDialog();

    /**
     * 显示成功
     */
    public void showSuccess();

    /**
     * 显示失败
     */
    public void showFailed();

    /**
     * 清除成功
     */
    public void clearSuccess();

    /**
     * 清除失败
     */
    public void clearFailed();

    public void clearText();
}
