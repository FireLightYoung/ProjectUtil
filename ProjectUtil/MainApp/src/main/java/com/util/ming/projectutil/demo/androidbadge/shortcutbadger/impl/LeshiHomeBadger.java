package com.util.ming.projectutil.demo.androidbadge.shortcutbadger.impl;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;


import com.util.ming.projectutil.demo.androidbadge.shortcutbadger.Badger;
import com.util.ming.projectutil.demo.androidbadge.shortcutbadger.NotificationSubcriptUtils;

import java.util.Arrays;
import java.util.List;

/**
 */
public class LeshiHomeBadger extends Badger {

    @Override
    public void executeBadge(Context context, Notification notification, int notificationId, int thisNotificationCount, int count) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String launcherClassName = getLauncherClassName(context);
        Class clazz = null;
        try {
            clazz = Class.forName(launcherClassName);
            NotificationSubcriptUtils.setNotificationSubscript(manager, clazz, count);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            setNotification(notification, notificationId, context);
        }
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.android.launcher3"
        );
    }
}
