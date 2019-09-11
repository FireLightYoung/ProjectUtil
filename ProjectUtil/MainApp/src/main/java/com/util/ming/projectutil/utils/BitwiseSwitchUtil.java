package com.util.ming.projectutil.utils;

/**
 * 通过位运算实现设置的控制
 * <p>
 * 模拟simple
 */
public class BitwiseSwitchUtil {

    //原始权限
    private int insertAuth = 1 << 0;//增权限
    private int updateAuth = 1 << 1;//修改权限
    private int queryAuth = 1 << 2;//查询权限
    private int deleteAuth = 1 << 3;//删除权限
    //用户权限
    //增删改查所有的权限
    public int boosAuth = insertAuth | updateAuth | queryAuth | deleteAuth;
    //增改查的权限
    public int managerAuth = insertAuth | updateAuth | queryAuth;
    //增查的权限
    public int salesAuth = insertAuth | queryAuth;

    //判断是否有插入的权限
    public boolean isInsertAuth(int currentAuth) {
        return (currentAuth & insertAuth) > 0;
    }

    //判断是否有查询的权限
    public boolean isQueryAuth(int currentAuth) {
        return (currentAuth & queryAuth) > 0;
    }

    //判断是否有修改的权限
    public boolean isUpdateAuth(int currentAuth) {
        return (currentAuth & updateAuth) > 0;
    }

    //判断是否有删除的权限
    public boolean isDeleteAuth(int currentAuth) {
        return (currentAuth & deleteAuth) > 0;
    }
}
