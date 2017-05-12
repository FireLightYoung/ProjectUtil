package com.util.ming.projectutil.demo.retrofitdemo;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.util.ming.projectutil.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Retrofit2Activity extends Activity {

    String API = "https://api.github.com";    // BASE URL
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
        txt = (TextView) findViewById(R.id.text_activity_retrofit2);
        new Thread() {
            @Override
            public void run() {
                super.run();
                initService();
            }
        }.start();


    }

    private void initService() {
        Retrofit retrofit = new Retrofit.Builder()//01:获取Retrofit对象
                .baseUrl(API) //02绑定Base url
                .addConverterFactory(GsonConverterFactory.create())
                .build();//03执行操作
        GitHubService service = retrofit.create(GitHubService.class);//04获取API接口的实现类的实例对象
        Call<gitmodel> repos = service.listRepos("basil2style");
        repos.enqueue(new Callback<gitmodel>() {
            @Override
            public void onResponse(Call<gitmodel> call, Response<gitmodel> response) {

                gitmodel nodel = response.body();
                txt.setText(nodel.toString());
                Log.i("yang", "onResponse");
            }

            @Override
            public void onFailure(Call<gitmodel> call, Throwable t) {
                Log.i("yang", "onFailure");
            }
        });
    }


}