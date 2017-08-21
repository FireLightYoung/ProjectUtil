package com.util.ming.projectutil.utils;

import android.view.View;

/**
 * Created by ming on 17/8/14.
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 800;

    private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentClickTime;
            onNoDoubleClick(view);
        }
    }

    public abstract void onNoDoubleClick(View view);
}
