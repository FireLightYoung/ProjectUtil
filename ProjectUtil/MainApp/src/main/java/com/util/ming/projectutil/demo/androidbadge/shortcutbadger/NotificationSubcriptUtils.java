package com.util.ming.projectutil.demo.androidbadge.shortcutbadger;

import android.app.NotificationManager;

import java.lang.reflect.Method;
import java.util.HashMap;

public class NotificationSubcriptUtils {
    /**
     *  For third part application
     *  set amount of subscript for launcher to show
     * @param mainActivityOnLauncher    activity link to icon on launcher
     * @param msgCount      notification subscript want to show
     */
    @SuppressWarnings("finally")
    public static boolean setNotificationSubscript(NotificationManager manager,Class mainActivityOnLauncher,int msgCount) {
        boolean result = false;
        Class clazz = manager.getClass();
        try {
            Method setNotificationSubscriptMethod = clazz.getMethod("setNotificationSubscript",Class.class,Integer.class);
            Object resultObj = setNotificationSubscriptMethod.invoke(manager, mainActivityOnLauncher,msgCount);
            if(resultObj != null && resultObj instanceof Boolean)
            result = (Boolean) resultObj;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return result;
        }
    }
    
    /**
     *  For internal application
     *  set amount of subscript for launcher to show
     * @param mainActivityOnLauncher    activity link to icon on launcher
     * @param msgCount      notification subscript want to show
     */
    @SuppressWarnings("finally")
    public static boolean setNotificationSubscript(NotificationManager manager,String appPkg,String mainActivityOnLauncher,int msgCount) {
        boolean result = false;
        Class clazz = manager.getClass();
        try {
            Method setNotificationSubscriptMethod = clazz.getMethod("setNotificationSubscript",String.class,String.class,Integer.class);
            Object resultObj = setNotificationSubscriptMethod.invoke(manager, appPkg,mainActivityOnLauncher,msgCount);
            if(resultObj != null && resultObj instanceof Boolean)
            result = (Boolean) resultObj;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return result;
        }
    }
    
    /**
     *  For third part application
     *  get amount of subscript
     * @param mainActivityOnLauncher    activity link to icon on launcher
     * @return  default value is -1
     */
    @SuppressWarnings("finally")
    public static int getNotificationSubscript(NotificationManager manager,Class mainActivityOnLauncher) {
        int result = -2;
        Class clazz = manager.getClass();
        try {
            Method getNotificationSubscriptMethod = clazz.getMethod("getNotificationSubscript",Class.class);
            Object resultObj = getNotificationSubscriptMethod.invoke(manager, mainActivityOnLauncher);
            if(resultObj != null && resultObj instanceof Integer)
            result = (Integer) resultObj;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return result;
        }
    }
    
    /**
     *  For internal application
     *  get amount of subscript
     * @param mainActivityOnLauncher    activity link to icon on launcher
     * @return  default value is -1
     */
    @SuppressWarnings("finally")
    public static int getNotificationSubscript(NotificationManager manager,String appPkg,String activityName) {
        int result = -1;
        Class clazz = manager.getClass();
        try {
            Method getNotificationSubscriptMethod = clazz.getMethod("getNotificationSubscript",String.class,String.class);
            Object resultObj = getNotificationSubscriptMethod.invoke(manager, appPkg,activityName);
            if(resultObj != null && resultObj instanceof Integer)
            result = (Integer) resultObj;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return result;
        }
    }
    
    /**
     * For Launcher application
     *  get all subscript info
     *  @return  default value is null
     */
    @SuppressWarnings("finally")
    public static HashMap<String,Integer> getNotificationSubscriptMap(NotificationManager manager){
        HashMap<String,Integer> map = null;
        Class clazz = manager.getClass();
        try {
            Method getNotificationSubscriptMapMehtod = clazz.getMethod("getNotificationSubscriptMap");
            Object resultObj = getNotificationSubscriptMapMehtod.invoke(manager);
            if(resultObj != null && resultObj instanceof HashMap)
                map = (HashMap<String, Integer>) resultObj;
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return map;
        }
    }
    
}
