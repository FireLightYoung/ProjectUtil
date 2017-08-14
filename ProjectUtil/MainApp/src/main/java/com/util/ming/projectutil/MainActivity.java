package com.util.ming.projectutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.util.ming.projectutil.activity.BaseActivity;
import com.util.ming.projectutil.demo.mvp.MVPActivity;
import com.util.ming.projectutil.demo.rxjava.RxJavaActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    ListView listview;
    ArrayList<HashMap<String, String>> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        mylist = new ArrayList<HashMap<String, String>>();
        addDemo();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.mvp_listView);
    }

    private void initEvent() {
        SimpleAdapter mSchedule = new SimpleAdapter(this,
                mylist,
                R.layout.item_main,
                new String[]{"ItemTitle", "ItemText"},
                new int[]{R.id.item_main_txt1, R.id.item_main_txt2});
        listview.setAdapter(mSchedule);
        listview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> map = mylist.get(position);
        String activityName = map.get("ItemActivity");
        Class activityClass = null;
        try {
            activityClass = Class.forName(activityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (activityClass != null) {
            Intent mIntent = new Intent();
            mIntent.setClass(this, activityClass);
            startActivity(mIntent);
        } else {
            Toast.makeText(this, "跳转失败", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 添加demo
     */
    public void addDemo() {
        addMVPDemo();
        addRxJavaDemo();
        addFrescoDemo();
        addAutoFocusDemo();
        addRetrofit2Demo();
        addCameraDemo();
    }

    public void addMVPDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "MVP Demo.");
        map.put("ItemText", "一个采用MVP架构的例子");
        map.put("ItemActivity", "com.util.ming.projectutil.demo.mvp.MVPActivity");
        mylist.add(map);
    }

    public void addRxJavaDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "RxJava Demo");
        map.put("ItemText", "一个采用RxJava2.0进行响应式开发的例子");
        map.put("ItemActivity", "com.util.ming.projectutil.demo.rxjava.RxJavaActivity");
        mylist.add(map);
    }

    public void addFrescoDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "Fresco Demo");
        map.put("ItemText", "一个采用Fresco加载图片的例子");
        map.put("ItemActivity", "com.util.ming.projectutil.demo.fresco.FrescoActivity");
        mylist.add(map);
    }

    public void addAutoFocusDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "AutoFocus Demo");
        map.put("ItemText", "一个停止当前播放声音的功能");
        map.put("ItemActivity", "com.util.ming.projectutil.demo.autofocus.AutoFocusActivity");
        mylist.add(map);
    }


    public void addRetrofit2Demo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "Retrofit2 Demo");
        map.put("ItemText", "一个使用Retrofit请求网络的例子");
        map.put("ItemActivity", "com.util.ming.projectutil.demo.retrofitdemo.Retrofit2Activity");
        mylist.add(map);
    }

    public void addCameraDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "Camera Demo");
        map.put("ItemText", "Camera录像，前后摄像头");
        map.put("ItemActivity", "com.util.ming.projectutil.demo.camerademo.CameraActivity");
        mylist.add(map);
    }

}
