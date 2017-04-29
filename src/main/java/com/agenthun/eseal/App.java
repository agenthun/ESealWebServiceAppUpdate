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
    private static boolean updateESeal = true;
    private UpdateRequest ESealUpdateRequest = new UpdateRequest(
            Api.UPDATE_APP_TYPE_ESEAL,
            "7",
            "1.2.5",
            "http://www.freight-track.com/files/ESeal_v_1_2_5_2017-04-28.apk",
            "13451773",
            "1.修改蓝牙协议上传时间为UTC时间"
    );

    //NFC封锁 Lite
    private static boolean updateESealLite = false;
    private UpdateRequest ESealLiteUpdateRequest = new UpdateRequest(
            Api.UPDATE_APP_TYPE_ESEAL_LITE,
            "6",
            "2.0.0",
            "http://www.freight-track.com/files/ESeal_Lite_v_2_0_0_2017-03-12.apk",
            "12626089",
            "1.全新软件框架\r\n2.解决了一些已知问题"
    );

    private Person agenthun = new Person("demodemo", "123456");

    private String token = null;

    private RetrofitManager retrofitManager = RetrofitManager.builder(Api.WEB_SERVICE_V2_RELEASE);

    public static void main(String[] args) {
        App app = new App();
        System.out.println("开始更新数据");

        app.requestToken(app.agenthun);
        while (app.getToken() == null) ;

        if (updateESeal) {
            UpdateRequest request = app.getESealUpdateRequest();
            request.setToken(app.getToken());
            app.updateVersion(request, Api.ESeal_UPDATE_SERVICE_URL);
        }

        if (updateESealLite) {
            UpdateRequest liteRequest = app.getESealLiteUpdateRequest();
            liteRequest.setToken(app.getToken());
            app.updateVersion(liteRequest, Api.ESeal_LITE_UPDATE_SERVICE_URL);
        }
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
