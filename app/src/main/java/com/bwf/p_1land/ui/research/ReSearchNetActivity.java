package com.bwf.p_1land.ui.research;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.ToastUtil;

/**
 * Created by Administrator on 2016/12/8.
 */

public class ReSearchNetActivity extends BaseActivity {
    private WebView webView;
    private TextView tv;
    private ImageView iv;
    private WebSettings settings;
    private String url;
    private String title;
    @Override
    public int getContentViewId() {
        return R.layout.avtivity_searchnet;
    }

    @Override
    public void beforInitView() {
        url=this.getIntent().getStringExtra("url");
        title=this.getIntent().getStringExtra("title");
        LogUtils.e(url);
    }

    @Override
    public void initView() {
       webView=findViewByIdNoCast(R.id.webview);
        tv=findViewByIdNoCast(R.id.webview_tv);
        iv=findViewByIdNoCast(R.id.webview_iv);
        setOnclick(iv);
        tv.setText(title);
    }

    @Override
    public void initViewData() {
        webView.loadUrl(url);
        settings=webView.getSettings();
        //设置支持缩放
        settings.setBuiltInZoomControls(true);
        //自适应屏幕
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }
    @Override
    public void afterInitView() {

    }
    private boolean ischeck=true;
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.webview_iv){
            if(ischeck){
                iv.setImageResource(R.mipmap.shoucang_sel);
                ischeck=false;
                ToastUtil.showToast("点了关注不迷路");
            }else {
                iv.setImageResource(R.mipmap.shoucang);
                ischeck=true;
                ToastUtil.showToast("取消关注");
            }
        }
    }
}
