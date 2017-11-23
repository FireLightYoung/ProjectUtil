package com.util.ming.projectutil.demo.sqldemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;
import com.util.ming.projectutil.demo.gsondemo.GsonUser;

import java.io.IOException;

/**
 * Created by ming on 17/5/9.
 *
 * @author ming
 */

public class SqlActivity extends BaseActivity implements View.OnClickListener {
    private TextView textview;
    private Button btn_add;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_quary;
    SqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        helper = new SqliteHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        database.execSQL(SqlContents.createTable1);
    }

    private void initView() {

        textview = (TextView) findViewById(R.id.textView_SQL);
        btn_add = (Button) findViewById(R.id.sql_btn0);
        btn_delete = (Button) findViewById(R.id.sql_btn1);
        btn_update = (Button) findViewById(R.id.sql_btn2);
        btn_quary = (Button) findViewById(R.id.sql_btn3);
    }

    private void initEvent() {
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_quary.setOnClickListener(this);
    }


    public boolean add() {
        try {
            SQLiteDatabase database = helper.getWritableDatabase();
            database.execSQL(SqlContents.insertData1);
            database.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete() {
        return true;
    }

    public boolean update() {
        return true;
    }

    public boolean quary() {
//        for(cur.moveToFirst();!cur.isAfterLast();cur.moveToNext()){
//            int nameColumn = cur.getColumnIndex(People.NAME);
//            int phoneColumn = cur.getColumnIndex(People.NUMBER);
//            String name = cur.getString(nameColumn);
//            String phoneNumber = cur.getString(phoneColumn);
//        }
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery(SqlContents.selectData, null);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int nameColumn = cursor.getColumnIndex("NAME");
            int phoneColumn = cursor.getColumnIndex("AGE");
            String name = cursor.getString(nameColumn);
            String phoneNumber = cursor.getString(phoneColumn);
        }
        cursor.moveToFirst();
        while (cursor.moveToNext()) {

            Log.i("yang", "name==");

        }
        cursor.close();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sql_btn0:
                add();
                break;
            case R.id.sql_btn1:
                delete();
                break;
            case R.id.sql_btn2:
                update();
                break;
            case R.id.sql_btn3:
                quary();
                break;
            default:
                break;
        }
    }
}
