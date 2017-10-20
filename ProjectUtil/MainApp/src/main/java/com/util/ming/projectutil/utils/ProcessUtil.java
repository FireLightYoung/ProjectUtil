package com.util.ming.projectutil.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.util.ming.projectutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ming on 17/10/17.
 */

public class ProcessUtil {
    /**
     * 获取当前系统中的进程
     *
     * @param ctx
     * @return
     */
    public static List<ProcessInfo> getProcessInfo(Context ctx) {
        //获取进程相关信息
        List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
        //1,activityManager管理者对象和PackageManager管理者对象
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = ctx.getPackageManager();
        //2,获取正在运行的进程的集合
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        //3,循环遍历上诉集合,获取进程相关信息(名称,包名,图标,使用内存大小,是否为系统进程(状态机))
        for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
            ProcessInfo processInfo = new ProcessInfo();
            //4,获取进程的名称 == 应用的包名
            processInfo.packName = info.processName;
            //5,获取进程占用的内存大小(传递一个进程对应的pid数组)
            android.os.Debug.MemoryInfo[] processMemoryInfo = am.getProcessMemoryInfo(new int[]{info.pid});
            //6,返回数组中索引位置为0的对象,为当前进程的内存信息的对象
            android.os.Debug.MemoryInfo memoryInfo = processMemoryInfo[0];
            //7,获取已使用的大小
            processInfo.size = memoryInfo.getTotalPrivateDirty() * 1024;

            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo(processInfo.packName, 0);
                //8,获取应用的名称
                processInfo.name = applicationInfo.loadLabel(pm).toString();
                //9,获取应用的图标
                processInfo.icon = applicationInfo.loadIcon(pm);
                //10,判断是否为系统进程
                if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
                    processInfo.isSystem = true;
                } else {
                    processInfo.isSystem = false;
                }
            } catch (PackageManager.NameNotFoundException e) {
                //需要处理
                processInfo.name = info.processName;
                processInfo.icon = ctx.getResources().getDrawable(R.drawable.ic_launcher);
                processInfo.isSystem = true;
                e.printStackTrace();
            }
            processInfoList.add(processInfo);
        }
        return processInfoList;
    }


    /**
     * 获取当前进程名
     *
     * @param context
     * @return
     */
    public static String getCurrProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager _am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = _am.getRunningAppProcesses();
        for (int i = 0; i < list.size(); i++) {
            if (pid == list.get(i).pid) {
                return list.get(i).processName;
            }
        }
        return null;
    }

    public static class ProcessInfo {
        public String name;  //进程的名字
        public Drawable icon;  //进程的图标
        public String packName;  //进程的包名
        public boolean isSystem;   //是否为系统应用
        public long size;         //进程的占用的内存
    }
}

