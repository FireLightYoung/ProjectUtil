package com.util.ming.projectutil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initData() {

    }

    private void initView() {
        listview = (ListView) findViewById(R.id.mvp_listView);
    }

    private void initEvent() {
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class MyAdapter extends BaseAdapter {

        List<String> list;
        Context context;

        public MyAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            return null;
        }
    }
}
