package com.agenthun.eseal;

import com.agenthun.eseal.bean.*;
import com.agenthun.eseal.service.Api;
import com.agenthun.eseal.service.RetrofitManager;
import rx.functions.Action1;

/**
 * Created by agenthun on 2017/2/27.
 */
public class App {
    //蓝牙锁
    private UpdateRequest ESealUpdateRequest = new UpdateRequest(
            Api.UPDATE_APP_TYPE_ESEAL,
            "5",
            "1.2.3",
            "http://www.freight-track.com/files/ESeal_v_1_2_3_2017-02-27.apk",
            "13430744",
            "1.支持Google地图\r\n2.增加GPS定位"
    );

    //NFC封锁 Lite
    private UpdateRequest ESealLiteUpdateRequest = new UpdateRequest(
            Api.UPDATE_APP_TYPE_ESEAL_LITE,
            "4",
            "1.0.4",
            "http://www.freight-track.com/files/ESeal_Lite_v_1_0_4_2017-02-27.apk",
            "13113066",
            "1.UI更新\r\n2.支持Google地址逆解析服务"
    );

    private Person agenthun = new Person("demodemo", "123456");

    private String token = null;

    private RetrofitManager retrofitManager = RetrofitManager.builder(Api.WEB_SERVICE_V2_RELEASE);

    public static void main(String[] args) {
        App app = new App();
        System.out.println("开始更新数据");

        app.requestToken(app.agenthun);
        while (app.getToken() == null) ;

        UpdateRequest request = app.getESealUpdateRequest();
        request.setToken(app.getToken());
        app.updateVersion(request, Api.ESeal_UPDATE_SERVICE_URL);

        UpdateRequest liteRequest = app.getESealLiteUpdateRequest();
        liteRequest.setToken(app.getToken());
        app.updateVersion(liteRequest, Api.ESeal_LITE_UPDATE_SERVICE_URL);
    }

    public String getToken() {
        return token;
    }

    public UpdateRequest getESealUpdateRequest() {
        return ESealUpdateRequest;
    }

    public UpdateRequest getESealLiteUpdateRequest() {
        return ESealLiteUpdateRequest;
    }

    private void requestToken(Person person) {
        retrofitManager
                .getTokenObservable(person.getmUsername(), person.getmPassword())
                .subscribe(new Action1<User>() {
                    public void call(User user) {
                        token = user.getTOKEN();
                        System.out.println("token = " + token);
                        System.out.println();
                    }
                });
    }

    private void checkUpdate(final String url) {
        retrofitManager
                .checkUpdateObservable(url)
                .subscribe(new Action1<UpdateResponse.Entity>() {
                    public void call(UpdateResponse.Entity entity) {
                        //蓝牙锁
                        if (Api.ESeal_UPDATE_SERVICE_URL.equals(url)) {
                            System.out.println("检测 [ 蓝牙锁 ] 更新信息");
                        }
                        //NFC封锁 Lite
                        if (Api.ESeal_LITE_UPDATE_SERVICE_URL.equals(url)) {
                            System.out.println("检测 [ NFC封锁 Lite ] 更新信息");
                        }
                        System.out.println("entity = " + entity.toString());
                        System.out.println();
                        System.out.println();
                    }
                });
    }

    private void updateVersion(UpdateRequest updateRequest, final String url) {
        retrofitManager
                .updateVersionObservable(updateRequest)
                .subscribe(new Action1<Result>() {
                    public void call(Result result) {
//                        System.out.println("result = " + result.toString());
                        if (result.getRESULT() == 1) {
                            System.out.println("更新版本号成功");
                        }
                        if ("1".equals(result.getERRORINFO())) {
                            System.out.println("更新版本号失败");
                        }

                        checkUpdate(url);
                    }
                });
    }
}
