package com.util.ming.projectutil.view;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ming on 17/10/25.
 * 无关activity场景跳转出dialog
 *
 * @author ming
 */
public class DialogContainerActivity extends Activity {

    private static DialogContextProvider mContextProvider;

    public static void show(DialogContextProvider contextProvider) {
        mContextProvider = contextProvider;
//        Intent intent = new Intent(MyApplication.getInstance() /*全局 Application 实例*/, DialogContainerActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        App.getApp().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContextProvider.getContext(this);
    }

    public interface DialogContextProvider {
        void getContext(Activity activity);
    }
}
