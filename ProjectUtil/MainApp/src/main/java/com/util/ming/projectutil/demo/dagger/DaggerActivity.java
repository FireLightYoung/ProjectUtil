package com.util.ming.projectutil.demo.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import javax.inject.Inject;

/**
 * Created by ming on 17/5/9.
 */

public class DaggerActivity extends BaseActivity {

    private static final String TAG = "DaggerActivity";
    TextView text;

    @Inject
    DaggerPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        text = (TextView) findViewById(R.id.textView_daager);
        inject();
        presenter.showUserName();
        //Log.i(TAG, "client = " + (client == null ? "null" : client));
    }

    private void inject() {
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .build().inject(this);
    }

    public void showUserName(String name) {
        text.setText(name);
    }
}