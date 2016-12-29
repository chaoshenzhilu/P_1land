package com.bwf.p_1land.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/12/18.
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler{
    private String appID="wx93584718617ec36f";
    private IWXAPI api;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        api= WXAPIFactory.createWXAPI(this,appID,false);
        api.handleIntent(getIntent(),this);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result="";
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_OK:
                result="发送成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result="发送取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result="发送被拒绝";
                break;
           default:
                result="发送返回";
        }
        ToastUtil.showToastLong(result);
        LogUtils.e(result);
        finish();
    }
}
