package com.util.ming.projectutil.view;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ming on 17/5/10.
 */

public class SoftKeybroadView {
    /**
     * 自动打开键盘
     *
     * @param context
     * @param v
     */
    private static void openSoftKeybroad(final Context context, final View v) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() { // 弹出软键盘的代码
                InputMethodManager imm = (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(v, InputMethodManager.RESULT_SHOWN);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }, 300);
    }

    /**
     * 自动关闭键盘
     *
     * @param context
     * @param v
     */
    private static void closeSoftKeybroad(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
