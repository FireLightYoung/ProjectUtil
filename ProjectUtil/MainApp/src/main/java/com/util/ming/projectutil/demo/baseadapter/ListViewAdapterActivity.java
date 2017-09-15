package com.util.ming.projectutil.demo.baseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.util.ming.projectutil.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapterActivity extends Activity {

    private ListView mListView;
    private List<Bean> mDatas;
    private MyAdapterWithCommViewHolder mAdapterWithCommViewHolder;
    private MyAdapterWithCommAdapter mAdapterWithCommAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        initDatas();
        initView();
    }

    /**
     * 初始化数据和适配器
     *
     * @return void
     * @author Yann
     * @date 2015-8-5 下午10:15:03
     */
    private void initDatas() {
        mDatas = new ArrayList<Bean>();
        Bean bean = new Bean("Android新技能 Get 1", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 2", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 3", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 4", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 5", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);

        bean = new Bean("Android新技能 Get 6", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 7", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 8", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 9", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);

        bean = new Bean("Android新技能 Get 10", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 11", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 12", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);
        bean = new Bean("Android新技能 Get 13", "Android-打造万能的ListView和GridView适配器", "2015-08-05", "10086");
        mDatas.add(bean);

        mAdapterWithCommViewHolder = new MyAdapterWithCommViewHolder(this, mDatas, R.layout.item_listview);
        mAdapterWithCommAdapter = new MyAdapterWithCommAdapter(this, mDatas, R.layout.item_listview);
    }

    /**
     * 为列表设置适配器
     *
     * @return void
     * @author Yann
     * @date 2015-8-5 下午10:15:04
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
//		mListView.setAdapter(mAdapterWithCommViewHolder);
        mListView.setAdapter(mAdapterWithCommAdapter);
    }
}
