package com.util.ming.projectutil.demo.retrofitdemo;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.util.ming.projectutil.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 可以参考
 *
 * https://blog.csdn.net/carson_ho/article/details/73732076
 */
public class Retrofit2Activity extends Activity {

    String API = "https://api.github.com";    // BASE URL
    String API2 = "https://api.douban.com/v2/";    // BASE URL
    TextView txt;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
        txt = (TextView) findViewById(R.id.text_activity_retrofit2);
        new Thread() {
            @Override
            public void run() {
                super.run();
//                initService();
                initService2();
            }
        }.start();


    }

    /**
     * 普通请求
     */
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

                final gitmodel nodel = response.body();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(nodel.toString());
                        Logger.i(nodel.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<gitmodel> call, Throwable t) {
                Logger.i("onFailure");

            }
        });
    }

    private void initService2() {
        Retrofit retrofit = new Retrofit.Builder()//01:获取Retrofit对象
                .baseUrl(API2)//02绑定Base url
                .addConverterFactory(GsonConverterFactory.create())
                .build();//03执行操作
        GitHubService service = retrofit.create(GitHubService.class);//04获取API接口的实现类的实例对象
        Call<BookModel> repos = service.getSearchBook("金瓶梅", "", 0, 1);
        repos.enqueue(new Callback<BookModel>() {
            @Override
            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
                final BookModel nodel = response.body();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        txt.setText(nodel.toString());
//                        Logger.i("-------------" + nodel.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<BookModel> call, Throwable t) {
                Logger.i("onFailure--------");
            }
        });
    }

    /**
     * 绑定 okhttp3
     */
    private void initService3() {
        //也可以用 OkHttpClient 的 builder模式构造，加入，超时等各种属性
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()//01:获取Retrofit对象
                .baseUrl(API) //02绑定Base url
                .client(client)//绑定okhttp
                .addConverterFactory(GsonConverterFactory.create())
                .build();//03执行操作
        GitHubService service = retrofit.create(GitHubService.class);//04获取API接口的实现类的实例对象
        Call<gitmodel> repos = service.listRepos("basil2style");
        repos.enqueue(new Callback<gitmodel>() {
            @Override
            public void onResponse(Call<gitmodel> call, Response<gitmodel> response) {

                final gitmodel nodel = response.body();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(nodel.toString());
                        Logger.i(nodel.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<gitmodel> call, Throwable t) {
                Logger.i("onFailure");

            }
        });
    }

}