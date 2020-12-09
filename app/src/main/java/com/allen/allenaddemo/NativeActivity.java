package com.allen.allenaddemo;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.allen.allenaddemo.config.TTAdManagerHolder;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;

import java.util.HashMap;
import java.util.List;

public class NativeActivity extends BaseActivity {
    private FrameLayout containerView;
    private TTNativeExpressAd ttNativeExpressAd;
    private Spinner TTSpinner;
    private HashMap<String, String> TTplacement;
    private String[] PanglePlacementIdsKey,PanglePlacementIdsValue;
    private Button loadButton;
    private String  currentPlacementID="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        commonTitleTv=findViewById(R.id.common_title_1);
        containerView=findViewById(R.id.container_view);
        TTSpinner=findViewById(R.id.spinnerPlacement);
        loadButton=findViewById(R.id.load_btn);
        initNetwork();
        initView();
    }

    private void initView(){

    }
    private void initNetwork(){
        switch (MyApplicaiton.selectedNetwordk){
            case "Pangle"://Pangle
                TTplacement=new HashMap<String, String>();
                Resources res =getResources();
                PanglePlacementIdsKey = res.getStringArray(R.array.PanglePlacementIdsKey);
                PanglePlacementIdsValue = res.getStringArray(R.array.PanglePlacementIdsValue);
                for (int i = 0; i < PanglePlacementIdsKey.length; i++) {
                    TTplacement.put(PanglePlacementIdsKey[i],PanglePlacementIdsValue[i]);
                }
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(NativeActivity.this, android.R.layout.simple_spinner_item, PanglePlacementIdsKey);
                TTSpinner.setVisibility(View.VISIBLE);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                TTSpinner.setAdapter(spinnerAdapter);
                spinnerAdapter.notifyDataSetChanged();
                TTSpinner.setSelection(0);
                currentPlacementID=PanglePlacementIdsValue[0];
                TTSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        currentPlacementID=PanglePlacementIdsValue[i];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                commonTitleTv.setText("Pangle Native");
                loadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTTAdNative = TTAdManagerHolder.get().createAdNative(NativeActivity.this);
                        AdSlot adSlot = new AdSlot.Builder()
                                .setCodeId(currentPlacementID) //广告位id  901121253  945661908
//                        .set
                                .setSupportDeepLink(true)
                                .setAdCount(1) //请求广告数量为1到3条
                                .build();
                        mTTAdNative.loadNativeExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {

                            //广告请求失败
                            @Override
                            public void onError(int code, String message) {
                                AllenLog("code"+code+"===message=="+message);
                            }

                            //广告请求成功
                            @Override
                            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                                TTNativeExpressAd ttNativeExpressAd=ads.get(0);
                                if (ttNativeExpressAd!=null){
                                    ttNativeExpressAd.render();
                                    ttNativeExpressAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {

                                        //广告点击回调
                                        @Override
                                        public void onAdClicked(View view, int type) {
                                            AllenLog("onAdClicked");
                                        }

                                        //广告展示回调
                                        @Override
                                        public void onAdShow(View view, int type) {
                                            AllenLog("onAdShow");
                                        }

                                        //广告渲染失败回调
                                        @Override
                                        public void onRenderFail(View view, String msg, int code) {
                                            AllenLog("onRenderFail==="+msg);
                                        }

                                        //广告渲染成功回调
                                        @Override
                                        public void onRenderSuccess(View view, float width, float height) {
                                            AllenLog("onRenderSuccess");
                                            containerView.removeAllViews();
                                            containerView.addView(view);

                                        }
                                    });
                                    ttNativeExpressAd.setDislikeCallback(NativeActivity.this, new TTAdDislike.DislikeInteractionCallback() {
                                        @Override
                                        public void onSelected(int i, String s) {
                                            AllenLog("setDislikeCallback==onSelected=="+s);
                                        }

                                        @Override
                                        public void onCancel() {
                                            AllenLog("setDislikeCallback==onCancel");
                                        }

                                        @Override
                                        public void onRefuse() {
                                            AllenLog("setDislikeCallback==onRefuse");

                                        }
                                    });
                                }
                            }
                        });
                    }
                });

                break;
            case "Tencnet"://Tencnet
                break;
            case "Mintegral"://Mintegral
                break;
            case "Vungle"://Vungle
                break;
            case "Applovin"://Applovin
                break;
            case "Admob"://Admob
                break;
            case "Mopub"://Mopub
                break;
            case "ironSource"://ironSource
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        TTSpinner.setVisibility(View.GONE);
        if (ttNativeExpressAd != null) {
            ttNativeExpressAd.destroy();
        }
    }
}
