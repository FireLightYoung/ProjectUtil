package com.util.ming.projectutil.demo.mvp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.activity.BaseActivity;
import com.util.ming.projectutil.demo.mvp.Presenter.LoginPresenter;
import com.util.ming.projectutil.demo.mvp.entity.User;
import com.util.ming.projectutil.demo.mvp.view.ILoginView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ming on 17/5/9.
 */

public class MVPActivity extends BaseActivity implements ILoginView, View.OnClickListener {
    private Button buttonLogin;
    private Button buttonClear;


    private EditText textName;
    private EditText textPassword;
    private TextView textview;
    private TextView textview_message;

    LoginPresenter loginPresenter;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initData();
        initView();
        new Thread() {
            @Override
            public void run() {
                method();
            }
        }.start();

    }

    private void initData() {
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        buttonLogin = (Button) findViewById(R.id.buttonlogin);
        buttonClear = (Button) findViewById(R.id.buttonclear);

        textName = (EditText) findViewById(R.id.editTextname);
        textPassword = (EditText) findViewById(R.id.editTextpassword);

        textview = (TextView) findViewById(R.id.textView);
        textview_message = (TextView) findViewById(R.id.textView_message);
        textview_message.setText("账号:zhangsan 密码 123");
        buttonLogin.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void method() {
        String a = "[\"[{\\\"fileName\\\":\\\"1001042869.txt\\\"},{\\\"messageAttachment\\\":\\\"82378127A0664F2989546C0B1FC8BFE4\\\",\\\"messageContent\\\":\\\"[图片]\\\",\\\"messageTo\\\":1001042869,\\\"messageFrom\\\":1001042870,\\\"messageId\\\":\\\"82378127A0664F2989546C0B1FC8BFE4\\\",\\\"messageType\\\":2,\\\"messageDirection\\\":1,\\\"sendTime\\\":1518070139000,\\\"messageStatus\\\":2},{\\\"messageFrom\\\":1001042870,\\\"messageContent\\\":\\\"星期四 14:08\\\",\\\"messageTo\\\":1001042869,\\\"messageId\\\":\\\"78941E9F3CC94D7DBB3C3E220CEEEE63\\\",\\\"sendTime\\\":1518070138000,\\\"messageType\\\":30}]\",\"[{\\\"fileName\\\":\\\"1001037758.txt\\\"},{\\\"messageAttachment\\\":\\\"AABA797F49D84D9E88A9390C63602FF5\\\",\\\"messageContent\\\":\\\"[图片]\\\",\\\"messageTo\\\":1001037758,\\\"messageFrom\\\":1001042870,\\\"messageId\\\":\\\"AABA797F49D84D9E88A9390C63602FF5\\\",\\\"messageType\\\":2,\\\"messageDirection\\\":1,\\\"sendTime\\\":1518070149000,\\\"messageStatus\\\":2},{\\\"messageFrom\\\":1001042870,\\\"messageContent\\\":\\\"星期四 14:09\\\",\\\"messageTo\\\":1001037758,\\\"messageId\\\":\\\"0A3FE2D3FFFA4971A831D76817B73F20\\\",\\\"sendTime\\\":1518070148000,\\\"messageType\\\":30}]\"]";
//        a = a.replace("\\", "");
        int messageType = 55;
        try {
            JSONArray object = new JSONArray(a);
            for (int i = 0; i < object.length(); i++) {
//                JSONObject jsonObject = object.getJSONObject(i);
                String aaa = object.getString(i);


                JSONArray ob1 = new JSONArray(aaa);
                for (int j = 0; j < ob1.length(); j++) {
                    JSONObject jsonObject = ob1.getJSONObject(j);
                    int e = 0;
                }
//                object.remove(i);
                int c = 0;
                int d = 0;
            }

        } catch (
                JSONException e)

        {
            e.printStackTrace();
            int b = 0;
        }

    }

    @Override
    public String getUserName() {
        return textName.getText().toString();
    }

    @Override
    public String getPassword() {
        return textPassword.getText().toString();
    }

    @Override
    public void ShowDialog() {
        Toast.makeText(this, "加载中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HideDialog() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        textview.setText("登录成功");
    }

    @Override
    public void showFailed() {
        textview.setText("登录失败");
    }

    @Override
    public void clearSuccess() {

        textview.setText("清除成功");
        clearText();
    }

    @Override
    public void clearFailed() {
        textview.setText("清除失败");
    }

    @Override
    public void clearText() {
        textName.setText("");
        textPassword.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonlogin:
                User user = new User(getUserName(), getPassword());
                loginPresenter.login(user);
                break;
            case R.id.buttonclear:
                loginPresenter.clear();
                break;
        }
    }
}
