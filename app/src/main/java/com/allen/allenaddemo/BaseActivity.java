package com.allen.allenaddemo;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bytedance.sdk.openadsdk.TTAdNative;

public class BaseActivity extends Activity {
    protected String[][] networks;
    protected String[] adtypes;
    protected TextView commonTitleTv;
    protected TTAdNative mTTAdNative;//PangleSDK

    private static final String LOG_TAG = "AllenBaseActivity===";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res =getResources();
        adtypes = res.getStringArray(R.array.adtypes);
        networks= new String[][]{res.getStringArray(R.array.Native),res.getStringArray(R.array.RewardedVideo),res.getStringArray(R.array.SplashAd)};
    }
    protected void AllenLog(String message){
        Log.i(LOG_TAG,message);
        Toast.makeText(BaseActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
