package com.util.ming.projectutil.demo.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.util.ming.projectutil.R;
import com.util.ming.projectutil.demo.mvp.Presenter.LoginPresenter;
import com.util.ming.projectutil.demo.mvp.entity.User;
import com.util.ming.projectutil.demo.mvp.view.ILoginView;

/**
 * Created by ming on 17/5/9.
 */

public class MVPActivity extends Activity implements ILoginView, View.OnClickListener {
    private Button buttonLogin;
    private Button buttonClear;


    private EditText textName;
    private EditText textPassword;
    private TextView textview;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initData();
        initView();
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
        textview.setText("账号:zhangsan 密码 123");
        buttonLogin.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

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
    }

    @Override
    public void clearFailed() {
        textview.setText("清除成功");
    }

    @Override
    public void clearText() {
        textName.setText("");
        textPassword.setText("");
        textview.setText("账号:zhangsan 密码 123");
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
