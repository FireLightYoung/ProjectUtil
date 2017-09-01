package com.util.ming.projectutil.demo.retrofitdemo;

import com.util.ming.projectutil.demo.mvp.entity.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ming on 17/5/12.
 */
public interface GitHubService {

    @GET("users/{user}")
    Call<gitmodel> listRepos(@Path("user") String user);
// you can add some other meathod

    @POST("users/new")
    Call<User> createUser(@Body User user);

    @GET("book/search")
    Call<BookModel> getSearchBook(@Query("q") String name,
                                  @Query("tag") String tag,
                                  @Query("start") int start,
                                  @Query("count") int count);

    /**
     * 参数较多时直接封装到map中，调取更方便
     *
     * @param options
     * @return
     */
    @GET("book/search")
    Call<BookModel> getSearchBook(@QueryMap Map<String, String> options);


    /**
     * 替换url中某个字段
     * 注释里面括号里的和@Path后面括号的保持一样就可以，是对应关系
     * 假设我们groupId传入1，sort传入“2”，那么它拼接成的url就是group/1/users?sort=2
     *
     * @param groupId
     * @return
     */
    @GET("group/{id}/users")
    Call<BookModel> groupList(@Path("id") int groupId, @Query("sort") String sort);
}