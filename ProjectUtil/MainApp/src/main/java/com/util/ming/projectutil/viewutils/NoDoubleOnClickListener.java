package com.util.ming.projectutil.viewutils;

import android.view.View;

/**
 * 禁止点击某个按钮时进行连续快速点击
 * Created by ming on 17/7/14.
 */

public abstract class NoDoubleOnClickListener implements View.OnClickListener {

    /**
     * 两次点击之间时间差
     */
    public static final long NO_DOUBLE_CLICK_TIMER = 200;


    /**
     * 中间值来缓存上次点击按钮时间，从未点击过则设置为0;
     */
    private long clickTime = 0;

    @Override
    public void onClick(View v) {
        long current = System.currentTimeMillis();
        if (current - clickTime > NO_DOUBLE_CLICK_TIMER) {
            clickTime = System.currentTimeMillis();
            noDoubleOnClick(v);
        }
    }

    /**
     * 由原来的接口OnClick改至noDoubleOnClick抽象类来处理
     *
     * @param v
     */
    abstract public void noDoubleOnClick(View v);
}
