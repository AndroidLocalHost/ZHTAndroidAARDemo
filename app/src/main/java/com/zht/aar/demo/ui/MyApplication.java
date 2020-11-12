package com.zht.aar.demo.ui;

import android.app.Application;
import android.content.Context;

import com.zht.androidlibrary.utils.sdkUtils.YYExpressEngine;

/**
 * @author Administrator
 * 2020/11/10 0010 10:59
 */
public class MyApplication extends Application {


    public static Context AppContext;


    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
//        MultiDex.install(this);
        YYExpressEngine.getInstance().setTIMInstance(getApplicationContext());

    }
}
