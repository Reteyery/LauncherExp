package com.reteyery.launcherexp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import fm.qingting.qtsdk.QTSDK;
import fm.qingting.qtsdk.play.QTPlay;

public class BaseApplication extends Application {
    public static Context context;
    boolean isDebug;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        if (isDebug(this)){
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
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

    public boolean isDebug(Context context){
        isDebug = context.getApplicationInfo() != null&& (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return isDebug;
    }

}
