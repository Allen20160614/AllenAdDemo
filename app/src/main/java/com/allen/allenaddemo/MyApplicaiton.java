package com.allen.allenaddemo;

import android.app.Application;

import com.allen.allenaddemo.config.TTAdManagerHolder;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;

public class MyApplicaiton extends Application {
    public  static  String selectedNetwordk="";//唯一的全局变量
    @Override
    public void onCreate() {
        super.onCreate();
        TTAdManagerHolder.init(this);
    }

}
