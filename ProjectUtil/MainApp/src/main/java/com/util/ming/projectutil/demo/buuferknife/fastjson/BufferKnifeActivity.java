package com.util.ming.projectutil.demo.buuferknife.fastjson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.widget.Toast.LENGTH_SHORT;


//一、在项目的project 的build.gradle 文件中的dependencies标签下添加。

//        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'


//例如
//buildscript {
//        repositories {
//        jcenter()
//        }
//
//        dependencies {
//        classpath 'com.android.tools.build:gradle:2.2.2'
//        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
//        }
//        }
//
//        allprojects {
//        repositories {
//        jcenter()
//        }
//        }
//
//        task clean(type: Delete) {
//        delete rootProject.buildDir
//        }

//二、在module的build.gredle 文件中添加
//
//        apply plugin: 'android-apt'
//
// 三、在module的build.gredle 文件中的dependencies标签中添加
//
//        compile 'com.jakewharton:butterknife:8.4.0'
//        apt 'com.jakewharton:butterknife-compiler:8.4.0'

/**
 * Created by ming on 17/5/9.
 */
public class BufferKnifeActivity extends BaseActivity {

    //    @BindString(R.string.title) String title;
//    @BindDrawable(R.drawable.graphic) Drawable graphic;
//    @BindColor(R.color.red) int red; // int or ColorStateList field
//    @BindDimen(R.dimen.spacer) Float spacer; // int (for pixel size) or float (for exact value) field
//    // ...
    private Unbinder unbinder;

    @BindView(R.id.textView_bufferknife)
    TextView textview;
    @BindView(R.id.btn_bufferknife)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bufferknife);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
    }

    private void initView() {
        //加载bufferKnife的初始化方法
        unbinder = ButterKnife.bind(this);
    }

    private void initEvent() {


    }

    @OnClick(R.id.btn_bufferknife)
    public void submit(View view) {
        // TODO submit data to server...
    }
//也可以无参数绑定
//    @OnClick(R.id.btn_bufferknife)
//    public void submit() {
//        // TODO submit data to server...
//    }
// 也可以强转参数
//    @OnClick(R.id.btn_bufferknife)
//    public void sayHi(Button button) {
//        button.setText("Hello!");
//    }
//也可以绑定多个
//    @OnClick({R.id.btn_bufferknife, R.id.textView_bufferknife})
//    public void pickDoor(View view) {
//        Toast.makeText(this, "Try again", LENGTH_SHORT).show();
//    }


//绑定list
//    @OnItemSelected(R.id.list_view)
//    void onItemSelected(int position) {
//        // TODO ...
//    }
//
//    @OnItemSelected(value = R.id.maybe_missing, callback = NOTHING_SELECTED)
//    void onNothingSelected() {
//        // TODO ...
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑，fragment进行此操作是必要的
        unbinder.unbind();
    }
}
