package com.bwf.p_1land.ui.welcome;

import android.view.View;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.share.ShareHelper;
import com.bwf.p_1land.framework.util.IntentUtils;
import com.bwf.p_1land.ui.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/29.
 */

public class WelcomeActivity extends BaseActivity{
    private Boolean isFirst;

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initViewData() {
        ShareHelper helper=ShareHelper.getInstance(this);
        isFirst=helper.getFirst();
    }

    @Override
    public void afterInitView() {
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                if (isFirst) {
                    IntentUtils.openActivity(WelcomeActivity.this, GuideActivity.class);
                    finish();
                } else {
                    IntentUtils.openActivity(WelcomeActivity.this, MainActivity.class);
                    finish();
                }
            }
        };
        timer.schedule(timerTask,2000);
    }

    @Override
    public void onClick(View view) {

    }
}
