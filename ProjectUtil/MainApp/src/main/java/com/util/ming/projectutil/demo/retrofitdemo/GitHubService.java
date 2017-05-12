package com.util.ming.projectutil.demo.retrofitdemo;

import com.util.ming.projectutil.demo.mvp.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ming on 17/5/12.
 */
public interface GitHubService {

    @GET("users/{user}")
    Call<gitmodel> listRepos(@Path("user") String user);
// you can add some other meathod

    @POST("users/new")
    Call<User> createUser(@Body User user);
}