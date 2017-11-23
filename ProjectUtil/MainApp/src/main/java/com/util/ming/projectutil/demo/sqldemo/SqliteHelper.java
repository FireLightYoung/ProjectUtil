package com.util.ming.projectutil.demo.sqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ming on 17/10/25.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public static final int version = 4;
    public static final String DB_NAME = "test_db";
    public static final String TABLE_NAME = "entity";


    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table Orders(Id integer primary key, CustomName text, OrderPrice integer, Country text);
        String sql = "create table if not exists " + TABLE_NAME + " (Id integer primary key, CustomName text, OrderPrice integer, Country text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //此处的oldVersion 用来做一个中间变量，依次进行if 的版本判断，依次向上升级
        if (1 == oldVersion) {
            oldVersion = 2;
        }
        if (2 == oldVersion) {
            oldVersion = 3;
        }
        if (3 == oldVersion) {
            oldVersion = 4;
        }
        //当版本经过上面的筛选后，都不符合最新版本的情况，说明出了异常，此时，重新创建数据库
        if (oldVersion != version) {
            //此处删除原数据库
            //----代码省略
            onCreate(db);
        }

    }
}
