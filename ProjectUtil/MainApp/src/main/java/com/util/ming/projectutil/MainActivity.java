package com.util.ming.projectutil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.util.ming.projectutil.activity.BaseActivity;
import com.util.ming.projectutil.demo.autofocus.AutoFocusActivity;
import com.util.ming.projectutil.demo.baseadapter.ListViewAdapterActivity;
import com.util.ming.projectutil.demo.bindservicedemo.ServiceDemoActivity;
import com.util.ming.projectutil.demo.buuferknife.fastjson.BufferKnifeActivity;
import com.util.ming.projectutil.demo.camerademo.CameraActivity;
import com.util.ming.projectutil.demo.dagger.DaggerActivity;
import com.util.ming.projectutil.demo.drawdemo.DrawActivity;
import com.util.ming.projectutil.demo.fastjson.FastJsonActivity;
import com.util.ming.projectutil.demo.fresco.FrescoActivity;
import com.util.ming.projectutil.demo.gsondemo.GsonActivity;
import com.util.ming.projectutil.demo.layout.LayoutDemoActivity;
import com.util.ming.projectutil.demo.listtype.ListTypeActivity;
import com.util.ming.projectutil.demo.mvp.MVPActivity;
import com.util.ming.projectutil.demo.recyclerdemo.RecylcerActivity;
import com.util.ming.projectutil.demo.retrofitdemo.Retrofit2Activity;
import com.util.ming.projectutil.demo.rxjava.RxJavaActivity;
import com.util.ming.projectutil.demo.sqldemo.SqlActivity;
import com.util.ming.projectutil.demo.zipdemo.ZipDemoActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    ListView listview;
    ArrayList<DemoBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        mList = new ArrayList<>();
        addDemo();
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.mvp_listView);
    }

    private void initEvent() {
        MyAdapter myAdapter = new MyAdapter(mList, this);
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class activityClass = mList.get(position).getActivityClass();
        if (null != activityClass) {
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
        addZipDemo();
        addBufferKnifeDemo();
        addServiceDemo();
        addDrawActivityDemo();
        addSQLActivityDemo();
        addMVPDemo();
        addRxJavaDemo();
        addRecylcerActivityDemo();
        addFrescoDemo();
        addAutoFocusDemo();
        addRetrofit2Demo();
        addCameraDemo();
        addDaggerDemo();
        addadapterDemo();
        addListTypeActivityDemo();
        addLayoutDemo();
        addGsonDemo();
        addFastJsonDemo();
    }

    public void addMVPDemo() {
        DemoBean demoBean = new DemoBean("MVP Demo", "一个采用MVP架构的例子", MVPActivity.class);
        mList.add(demoBean);
    }

    public void addRxJavaDemo() {
        DemoBean demoBean = new DemoBean("RxJava Demo", "一个采用RxJava2.0进行响应式开发的例子", RxJavaActivity.class);
        mList.add(demoBean);
    }

    public void addFrescoDemo() {
        DemoBean demoBean = new DemoBean("Fresco Demo", "一个采用Fresco加载图片的例子", FrescoActivity.class);
        mList.add(demoBean);
    }

    public void addAutoFocusDemo() {
        DemoBean demoBean = new DemoBean("AutoFocus Demo", "一个停止当前播放声音的功能", AutoFocusActivity.class);
        mList.add(demoBean);
    }


    public void addRetrofit2Demo() {
        DemoBean demoBean = new DemoBean("Retrofit2 Demo", "一个使用Retrofit请求网络的例子", Retrofit2Activity.class);
        mList.add(demoBean);
    }

    public void addCameraDemo() {
        DemoBean demoBean = new DemoBean("Camera Demo", "Camera录像，前后摄像头", CameraActivity.class);
        mList.add(demoBean);
    }

    public void addDaggerDemo() {
        DemoBean demoBean = new DemoBean("Daager2 Demo", "Daager2实例", DaggerActivity.class);
        mList.add(demoBean);
    }

    public void addadapterDemo() {
        DemoBean demoBean = new DemoBean("通用adapter Demo", "万能的通用通用adapter实例", ListViewAdapterActivity.class);
        mList.add(demoBean);
    }

    public void addLayoutDemo() {
        DemoBean demoBean = new DemoBean("研究Layout Demo", "研究layout实例", LayoutDemoActivity.class);
        mList.add(demoBean);
    }

    public void addGsonDemo() {
        DemoBean demoBean = new DemoBean("研究Gson Demo", "研究Gson实例", GsonActivity.class);
        mList.add(demoBean);
    }

    public void addFastJsonDemo() {
        DemoBean demoBean = new DemoBean("研究FastJson Demo", "研究FastJson实例", FastJsonActivity.class);
        mList.add(demoBean);
    }

    public void addListTypeActivityDemo() {
        DemoBean demoBean = new DemoBean("研究ListType Demo", "研究ListType实例", ListTypeActivity.class);
        mList.add(demoBean);
    }

    public void addRecylcerActivityDemo() {
        DemoBean demoBean = new DemoBean("研究Recylcer Demo", "研究Recylcer实例", RecylcerActivity.class);
        mList.add(demoBean);
    }

    public void addSQLActivityDemo() {
        DemoBean demoBean = new DemoBean("研究SQLITE Demo", "研究SQLITE实例", SqlActivity.class);
        mList.add(demoBean);
    }

    public void addDrawActivityDemo() {
        DemoBean demoBean = new DemoBean("研究触摸绘制 Demo", "研究触摸绘制实例", DrawActivity.class);
        mList.add(demoBean);
    }

    public void addServiceDemo() {
        DemoBean demoBean = new DemoBean("研究service Demo", "研究service实例", ServiceDemoActivity.class);
        mList.add(demoBean);
    }

    public void addBufferKnifeDemo() {
        DemoBean demoBean = new DemoBean("研究BufferKnife Demo", "研究BufferKnife实例", BufferKnifeActivity.class);
        mList.add(demoBean);
    }

    public void addZipDemo() {
        DemoBean demoBean = new DemoBean("研究zip Demo", "研究zip实例", ZipDemoActivity.class);
        mList.add(demoBean);
    }

}
