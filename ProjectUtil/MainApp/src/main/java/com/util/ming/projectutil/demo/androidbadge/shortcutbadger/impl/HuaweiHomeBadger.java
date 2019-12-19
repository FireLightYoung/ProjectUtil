package com.util.ming.projectutil.demo.androidbadge.shortcutbadger.impl;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import com.util.ming.projectutil.demo.androidbadge.shortcutbadger.Badger;

import java.util.Arrays;
import java.util.List;

//import android.util.Log;

/**
 */
public class HuaweiHomeBadger extends Badger {
    private static final String INTENT_EXTRA_BADGE_COUNT = "badgenumber";
    private static final String INTENT_EXTRA_PACKAGENAME = "package";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "class";
    private static Bundle bundle;

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Bundle extra = new Bundle();
        extra.putString(INTENT_EXTRA_PACKAGENAME, context.getPackageName());
        extra.putString(INTENT_EXTRA_ACTIVITY_NAME, launcherClassName);
        extra.putInt(INTENT_EXTRA_BADGE_COUNT, count);
        try {
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, extra);
            setNotification(notification, notificationId, context);
        } catch (Exception e) {
//            Log.i("", "");
        }
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.huawei.android.launcher"
        );
    }
}
