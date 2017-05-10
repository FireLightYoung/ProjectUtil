package com.util.ming.projectutil.activity;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icbc.swipebacklayout.SwipeBackActivity;
import com.icbc.swipebacklayout.SwipeBackLayout;
import com.util.ming.projectutil.R;
import com.util.ming.projectutil.data.DataListener;
import com.util.ming.projectutil.utils.ICBCUtil;
import com.util.ming.projectutil.view.DialogFactory;

/**
 * 此文件涉及嵌入版，请在同步独立版程序时，同步嵌入版程序
 */

public class BaseActivity extends SwipeBackActivity {
    protected Dialog mProgressDialog;
    public BaseActivity thisActivity;
    private boolean isMobileAgent = false;

    private static final int SHOW_NETWORK_HINT_COUNT = 15;


    LinearLayout mNetWorkHint;
    protected LinearLayout mTopBackArea;

    //protected LinearLayout mTopRightArea;
    LinearLayout mTopBackButton;    // 左上角返回按钮
    protected TextView mTopTitle;
    protected ImageButton mTitleRightImageBtn, mTitleRightImageBtn02, mTitleRightAddFriendImageBtn;
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registerLongReceiver();

//		if(!LocalStringUtils.isEmpty(ConstantRegistLogon.channel)) {
//			isMobileAgent = true;
//			try {
//				MobileAgent.init(this, "300002567275", ConstantRegistLogon.channel);
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
        // 初始化当前Activity给子类用
        thisActivity = this;
        // 添加当前Activity到管理类
        AppManager.getAppManager().addActivity(this);

        mTopBackButton = (LinearLayout) findViewById(R.id.btn_left_back);
        mTopTitle = (TextView) findViewById(R.id.title);
        if (mTopBackButton != null) {
            mTopBackButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftTopReturn();
                }
            });
        }

        mSwipeBackLayout = getSwipeBackLayout();

    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }


    protected void setTopTitleText(String text) { //设置标题栏标题
        if (mTopTitle != null) {
            mTopTitle.setText(text);
        }
    }

    protected void setTopTitleText(int id) { //设置标题栏标题
        if (mTopTitle != null) {
            mTopTitle.setText(id);
        }
    }

    protected void setRightBtnImageResource(int id) { //设置标题栏右边ImageButton的图片资源
        if (mTitleRightImageBtn != null) {
            mTitleRightImageBtn.setImageResource(id);
        }
    }

    protected void setRightBtnImageResource02(int id) { //设置标题栏右边倒数第二个ImageButton的图片资源
        if (mTitleRightImageBtn02 != null) {
            mTitleRightImageBtn02.setImageResource(id);
        }
    }

    protected void setRightBtnAddFriendImageResource(int id) { //设置标题栏右边倒数第一个ImageButton的图片资源（添加新的朋友）
        if (mTitleRightAddFriendImageBtn != null) {
            mTitleRightAddFriendImageBtn.setImageResource(id);
        }
    }

    protected void setRightBtnVisibility(int visibility) { //设置标题栏右边ImageButton的可见性
        if (mTitleRightImageBtn != null) {
            mTitleRightImageBtn.setVisibility(visibility);
        }
    }

    protected void setRightBtnVisibility02(int visibility) { //设置标题栏右边ImageButton的可见性
        if (mTitleRightImageBtn02 != null) {
            mTitleRightImageBtn02.setVisibility(visibility);
        }
    }

    protected void setRightBtnAddFriendVisibility(int visibility) { //设置标题栏右边ImageButton的可见性（添加新的朋友）
        if (mTitleRightAddFriendImageBtn != null) {
            mTitleRightAddFriendImageBtn.setVisibility(visibility);
        }
    }


    public void setEnableGesture(boolean enable) {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.setEnableGesture(enable);
        }
    }

    @Override
    protected void onResume() {
        Log.d("BaseActivity", getClass().getSimpleName());
        super.onResume();
//        IMApp.getInstance().getNotificationManager().clearNotificationCommon(this);
        try {
            NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.cancel(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从管理类删除当前Activity
        AppManager.getAppManager().finishActivity(this);
    }


    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    // 返回按钮
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            backKeyPress();
            return false;
        }
        return false;
    }

    // 子类可以重写此方法
    public void backKeyPress() {
        scrollToFinishActivity();
    }

    ;


    public void showProgressDialog(String msg) {
        mProgressDialog = DialogFactory.createIndetemineProgressDialog(this, msg);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void showNetWorkError() {
        //ToastUtils.showShortToast(getBaseContext(), R.string.network_disconnect);
    }


    public void closeSoftKeybroad(EditText edit) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void closeSoftKeybroad(IBinder binder) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void leftTopReturn() {
        scrollToFinishActivity();
    }    // 公共返回方法，子类中实现


}
