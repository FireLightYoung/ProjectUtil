package com.util.ming.projectutil.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;


import java.util.Stack;

/**
 * 此文件涉及嵌入版，请在同步独立版程序时，同步嵌入版程序
 * 应用程序 activity 的管理类
 */
public class AppManager extends Application {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {

    }

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);

    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        try {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        } catch (Exception e) {
            Log.i("test_crash", "AppManager finishActivity(Class<?> cls) ==>" + e.toString());
        }
    }

    public void finishAllActivity() {
//		for (int i = 0, size = activityStack.size(); i < size; i++) {
//			if (null != activityStack.get(i)) {
//				activityStack.get(i).finish();
//			}
//		}
        while (!activityStack.empty()) {
            activityStack.pop().finish();
        }
        activityStack.clear();
    }

    @SuppressWarnings("deprecation")
    public void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
