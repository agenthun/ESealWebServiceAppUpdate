package com.agenthun.eseal.service;

import com.agenthun.eseal.bean.Result;
import com.agenthun.eseal.bean.UpdateResponse;
import com.agenthun.eseal.bean.User;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by agenthun on 2017/2/27.
 */
public interface FreightTrackWebService {
    //登陆，获取Token
    @GET("GetTokenByUserNameAndPassword")
    Observable<User> getToken(
            @Query("userName") String userName,
            @Query("password") String password,
            @Query("language") String language);

    /**
     * @description 版本检测更新
     */
    //版本检测更新
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET
    Observable<UpdateResponse> checkUpdate(@Url String url);

    /**
     * @description 写入版本更新内容
     */
    //写入版本更新内容
    @GET("UpdateVersion")
    Observable<Result> updateVersion(
            @Query("token") String token,
            @Query("module") String module,
            @Query("versionCode") String versionCode,
            @Query("versionNumber") String versionNumber,
            @Query("url") String url,
            @Query("size") String size,
            @Query("updateContent") String updateContent);
}
