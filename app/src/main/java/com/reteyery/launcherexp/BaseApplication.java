package com.reteyery.launcherexp;

import android.app.Application;
import android.content.Context;

import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.play.QTPlay;

public class BaseApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        QTSDK.init(getApplicationContext(),"MWVlMmNhMjgtYWUzOS0xMWU2LTkyM2YtMDAxNjNlMDAyMGFk","MjZiMjEwYzEtNDQyZS0zYzk3LTk4OWYtMGNlZWEzMzgwNGMz");
        QTSDK.Debug = true;//开启日志
        QTSDK.setAuthRedirectUrl("http://qttest.qingting.fm");
//        QTSDK.setQTThirdPartPayCallBack(new QTSDK.QTThirdPartPayCallBack() {
//            @Override
//            public void pay(List<PurchaseItem> purchaseItems) {
//                //do your own business
//            }
//        });

        QTPlay.initial((success, error) -> {
            if (success){

            }
        });
    }
}
