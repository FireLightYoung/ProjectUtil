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

import com.util.ming.projectutil.demo.mvp.MVPActivity;
import com.util.ming.projectutil.demo.rxjava.RxJavaActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
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

        mylist.add((getMVPDemo()));
        mylist.add((getRxJavaDemo()));


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
        Intent mIntent = new Intent();
        switch (position) {
            case 0:
                mIntent.setClass(this, MVPActivity.class);
                break;
            case 1:
                mIntent.setClass(this, RxJavaActivity.class);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        startActivity(mIntent);
    }

    public HashMap<String, String> getMVPDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "MVP Demo.");
        map.put("ItemText", "一个采用MVP架构的例子");
        return map;
    }

    public HashMap<String, String> getRxJavaDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ItemTitle", "RxJava Demo");
        map.put("ItemText", "一个采用RxJava2.0进行响应式开发的例子");
        return map;
    }

}
