package com.example.administrator.threeuomengdemo;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


public class App extends Application {
//静态代码块为了
    {
        PlatformConfig.setQQZone("100424468", "561cae6ae0f55abd990035bf");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
    }
}
