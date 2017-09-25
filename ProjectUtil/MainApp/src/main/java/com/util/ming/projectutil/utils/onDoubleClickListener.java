package com.util.ming.projectutil.utils;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by ming on 17/8/14.
 */

public abstract class onDoubleClickListener implements View.OnClickListener {
    private long[] mHits = new long[2];

    @Override
    public void onClick(View view) {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();//获取手机开机时间
        if (mHits[mHits.length - 1] - mHits[0] < 500) {
            /**双击的业务逻辑*/
            onDoubleClick(view);
        }

    }

    public abstract void onDoubleClick(View view);


//    private void doubleClick_2() {
//        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
//        mHits[mHits.length - 1] = SystemClock.uptimeMillis();//获取手机开机时间
//        if (mHits[mHits.length - 1] - mHits[0] < 500) {
//            /**双击的业务逻辑*/
//        }
//    }
}
