package com.util.ming.projectutil.demo.okhttptest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttp3Test {


    public static void main(String[] args) {
        method1();
    }

    private static void method1() {


        OKHttp3Test test = new OKHttp3Test();
        test.getReq();

    }


    /**
     * 同步get请求
     */
    public void getReq() {
        String url = "https://www.baidu.com/";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
//放入键值方式：
//        Request request = new Request.Builder()
//                .url(url)
//                .header("键", "值")
//                .header("键", "值")
//    ...
//    .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步post请求
     */
    public void postReq() {
        String url = "https://www.baidu.com/";
        OkHttpClient okHttpClient = new OkHttpClient();
        //表单
        RequestBody body = new FormBody.Builder()
                .add("键", "值")
                .add("键", "值")
//              ...
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 异步get请求
     */
    public void getReq2() {
        String url = "https://www.baidu.com/";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("我是异步线程,线程Id为:" + Thread.currentThread().getId());
            }
        });
        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程,线程Id为:" + Thread.currentThread().getId());
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 处理cookie
     */
    public void cookieHttp() {
        final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }

    public void settingHttp() {
        long READ_TIMEOUT = 3000;
        long WRITE_TIMEOUT = 3000;
        long CONNECT_TIMEOUT = 3000;
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .build();
    }
}
