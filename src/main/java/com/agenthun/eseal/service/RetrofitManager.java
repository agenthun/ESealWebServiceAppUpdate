package com.agenthun.eseal.service;

import com.agenthun.eseal.bean.Result;
import com.agenthun.eseal.bean.UpdateRequest;
import com.agenthun.eseal.bean.UpdateResponse;
import com.agenthun.eseal.bean.User;
import com.agenthun.eseal.util.LanguageUtil;
import com.agenthun.eseal.util.schedulers.SchedulerProvider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;
import rx.Observable;
import rx.functions.Func1;

/**
 * @project SmartSwitch
 * @authors agenthun
 * @date 2016/12/16 23:36.
 */

public class RetrofitManager {

    private static FreightTrackWebService service;

    private SchedulerProvider schedulerProvider;

    public static RetrofitManager builder(String baseUrl) {
        return new RetrofitManager(baseUrl);
    }

    private RetrofitManager(String baseUrl) {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(XMLGsonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            service = retrofit.create(FreightTrackWebService.class);

            schedulerProvider = SchedulerProvider.getInstance();
        }
    }

/*    private void subscribe() {

    }

    private void unsubscribe() {
    }*/

    //登陆,获取token
    public Observable<User> getTokenObservable(String userName, String password) {
        return service.getToken(userName, password, LanguageUtil.getLanguage())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .unsubscribeOn(schedulerProvider.io());
    }

    /**
     * @description 版本检测更新
     */
    //版本检测更新
    public Observable<UpdateResponse.Entity> checkUpdateObservable(@Url String url) {
        Observable<UpdateResponse> response = service.checkUpdate(url);
        return response.map(new Func1<UpdateResponse, UpdateResponse.Entity>() {
            public UpdateResponse.Entity call(UpdateResponse updateResponse) {
                if (updateResponse == null) {
                    return null;
                }
                if (updateResponse.getError() == null || updateResponse.getError().getResult() != 1) {
                    return null;
                }
                if (updateResponse.getEntity() != null) {
                    return updateResponse.getEntity();
                }
                return null;
            }
        })
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .unsubscribeOn(schedulerProvider.io());
    }

    /**
     * @description 写入版本更新内容
     */
    //写入版本更新内容
    public Observable<Result> updateVersionObservable(UpdateRequest updateRequest) {
        return service.updateVersion(updateRequest.getToken(),
                updateRequest.getModule(),
                updateRequest.getVersionCode(),
                updateRequest.getVersionNumber(),
                updateRequest.getUrl(),
                updateRequest.getSize(),
                updateRequest.getUpdateContent()
        )
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .unsubscribeOn(schedulerProvider.io());
    }
}