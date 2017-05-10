package com.util.ming.projectutil.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.util.ming.projectutil.config.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此文件涉及嵌入版，请在同步独立版程序时，同步嵌入版程序
 */

public class ICBCUtil {

    // 应用程序的Context
    private static Context context = ContextHelper.getContext();

    // 获取手机的UUID
    public static final String getDeviceId() {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice = "";
        // IMEI拼接AndroidID
        tmDevice = tm.getDeviceId() + "-" + Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        // 取不到，则通过UUID生成设备标识
        if ("".equals(tmDevice)) {
            tmDevice = UUID.randomUUID().toString();
        }
        // 大于50截前50个
        tmDevice = (tmDevice.length() > 50 ? tmDevice.substring(0, 50) : tmDevice);
        tmDevice = tmDevice.toUpperCase();
        return tmDevice;
    }

    // 获取IM的设备ID
    public static final String getIMDeviceId() {
        String tmDevice = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

        try {
            tmDevice = LocalStringUtils.byteArray2HEXString(tmDevice.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            tmDevice = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        }
        return tmDevice;
        // return getDeviceId();
    }

    // 获取手机屏幕横向分辨率
    public static final int getScreenWidth() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    // 获取手机屏幕纵向分辨率
    public static final int getScreenHeight() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    // 获取手机屏幕像素密度
    public static final float getScreenDensity() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }

    // 获取手机屏幕像素密度
    public static final float getScreenDensityDpi() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

    // dip转px
    public static final int dipToPx(float dipValue) {
        final float scale = getScreenDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    // px转dip
    public static int pxToDip(float pxValue) {
        final float scale = getScreenDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    // 获取客户端版本代号
    public static int getVerCode() {
        int verCode = -1;
        try {
            // verCode = context.getPackageManager().getPackageInfo("com.icbc",
            // 0).versionCode;
            // 取常量中的
            verCode = Constants.ClientVerCode;
        } catch (Exception e) {
        }
        return verCode;
    }

    // 获取联网接入点方式
    public static String[] getConnectType() {
        String[] type = new String[2];
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取网络的连接情况
        NetworkInfo activeNetInfo = conManager.getActiveNetworkInfo();
        NetworkInfo nowNetInfo;
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            // 判断WIFI网
            nowNetInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            type[0] = (nowNetInfo.getTypeName() == null) ? "" : nowNetInfo.getTypeName().toLowerCase();
            type[1] = (nowNetInfo.getExtraInfo() == null) ? "" : nowNetInfo.getExtraInfo().toLowerCase();
        } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            // 判断3G网
            nowNetInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            type[0] = (nowNetInfo.getTypeName() == null) ? "" : nowNetInfo.getTypeName().toLowerCase();
            type[1] = (nowNetInfo.getExtraInfo() == null) ? "" : nowNetInfo.getExtraInfo().toLowerCase();
        }
        return type;
    }


    // 获取具体联网接入点方式
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final int NETWORK_CLASS_2_G = 1;
    public static final int NETWORK_CLASS_3_G = 2;
    public static final int NETWORK_CLASS_4_G = 3;
    public static final int NETWORK_WIFI = 4;

    public static int getConnectTypeNew() {
        int netType = -1;
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取网络的连接情况
        NetworkInfo activeNetInfo = conManager.getActiveNetworkInfo();
        NetworkInfo nowNetInfo;
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            netType = NETWORK_WIFI;
        } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            // 判断具体哪种网络
            nowNetInfo = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (nowNetInfo != null) {
                int subType = nowNetInfo.getSubtype();
                String subTypeName = nowNetInfo.getSubtypeName();
                netType = getNetworkClass(subType);
            }
        }
        return netType;
    }

    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }

    // 获取客户端完整版本代号
    // 注意 客户经理版本要修改此处
    public static int verCode = 0;

    public static int getFullVerCode() {
        try {
            if (verCode == 0)
                verCode = context.getPackageManager().getPackageInfo("com.icbc.im", 0).versionCode;
        } catch (Exception e) {
        }
        return verCode;
    }

    // 获取客户端版本名称
    public static String getVerName() {
        String verName = "";
        try {
            // verName = context.getPackageManager().getPackageInfo("com.icbc",
            // 0).versionName;
            // 取常量中的
            verName = Constants.ClientVerName;
        } catch (Exception e) {
        }
        return verName;
    }


    /**
     * 缩放图片
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        } else {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidth = ((float) width / w);
            float scaleHeight = ((float) height / h);
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
            Bitmap roundCornerBitmap = getRoundCornerBitmap(newbmp);
            return roundCornerBitmap;
        }
    }

    /**
     * 图片加圆角
     */
    public static Bitmap getRoundCornerBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap bitmap2 = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        paint.setColor(color);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, 6, 6, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return bitmap2;
    }

    public static int getRandom() {
        Random rand = new Random();
        // int i = rand.nextInt();//int范围类的随机数
        int randomInt = rand.nextInt(240); // 生成0-120以内的随机数
        // i = (int)(Math.random() * 100); //0-100以内的随机数，用Matn.random()方式
        return randomInt;
    }

    // 判断应用是否安装
    public static boolean isApkInstall(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isActivityForeHead(final Context context, String activityName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            String currentName = topActivity.getClassName();
            return activityName.equals(currentName);
        }
        return false;
    }


    /**
     * 检查是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;

    }

    public static <T> boolean isServiceRunning(Context mContext, Class<T> className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className.getName()) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * release版本
     *
     * @return
     */
    public static String getReleaseVersion() {
        return "android-" + Build.VERSION.SDK;
    }

    public static boolean isContainKey(ArrayList<Long> list, long userId) {
        if (list == null) {
            return false;
        }
        for (long item : list) {
            if (item == userId)
                return true;
        }
        return false;
    }

    public static String replaceBlank(String str) {

        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\\t|\\|\\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
//			Log.e(TAG, "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
//					Log.e(TAG, "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    public static boolean canUseBadge() {
        boolean canUse = true;
        String proName = ICBCUtil.getSystemProperty("ro.build.version.emui");
        if (!TextUtils.isEmpty(proName) && proName.contains("EmotionUI") && proName.indexOf(".") > 0) {
            try {
                String version = proName.substring(proName.indexOf(".") - 1, proName.indexOf(".") + 2);
                float vercode = Float.valueOf(version);
                if (vercode < 3.1f) {
                    canUse = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                canUse = false;
            }
        }
        return canUse;
    }


}
