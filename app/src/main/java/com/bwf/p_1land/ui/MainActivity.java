package com.bwf.p_1land.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.dataUtils.DataFromAssets;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.util.DrawableUtils;
import com.bwf.p_1land.framework.util.IntentUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.ui.appreciation.AppreciationActivity;
import com.bwf.p_1land.ui.map.MapActivity;
import com.bwf.p_1land.ui.onehand.OneHandActivity;
import com.bwf.p_1land.ui.research.ReSearchActivity;
import com.bwf.p_1land.ui.sale.SaleActivity;
import com.bwf.p_1land.ui.search.SearchActivity;

public class MainActivity extends BaseActivity {
    private LinearLayout ll_zaishou;
    private LinearLayout ll_daizu;
    private LinearLayout ll_loupan;
    private LinearLayout ll_yishou;
    private LinearLayout ll_ditu;
    private LinearLayout ll_yanjiu;
    private LinearLayout ll_guwen;
    private LinearLayout ll_wode;
    private TextView tv_zaishou;
    private TextView tv_daizu;
    private TextView tv_loupan;
    private TextView tv_yishou;
    private TextView tv_ditu;
    private TextView tv_yanjiu;
    private TextView tv_guwen;
    private TextView tv_wode;
    private TextView[] textViews;
    private LinearLayout[] ll;
    private EditText et;
    private int[] id_check;
    private int[] id_nomal;
    private boolean isexit = true;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        ll_zaishou = findViewByIdNoCast(R.id.ll_zaishou);
        ll_daizu = findViewByIdNoCast(R.id.ll_daizu);
        ll_loupan = findViewByIdNoCast(R.id.ll_loupan);
        ll_yishou = findViewByIdNoCast(R.id.ll_yishou);
        ll_ditu = findViewByIdNoCast(R.id.ll_ditu);
        ll_yanjiu = findViewByIdNoCast(R.id.ll_yanjiu);
        ll_guwen = findViewByIdNoCast(R.id.ll_guwen);
        ll_wode = findViewByIdNoCast(R.id.ll_wode);
        tv_zaishou = findViewByIdNoCast(R.id.main_zaishou);
        tv_daizu = findViewByIdNoCast(R.id.main_daizu);
        tv_loupan = findViewByIdNoCast(R.id.main_loupan);
        tv_yishou = findViewByIdNoCast(R.id.main_yishou);
        tv_ditu = findViewByIdNoCast(R.id.main_ditu);
        tv_yanjiu = findViewByIdNoCast(R.id.main_yanjiu);
        tv_guwen = findViewByIdNoCast(R.id.main_guwen);
        tv_wode = findViewByIdNoCast(R.id.main_wode);
        et = findViewByIdNoCast(R.id.main_et);
    }

    @Override
    public void initViewData() {
        textViews = new TextView[]{tv_zaishou, tv_daizu, tv_loupan, tv_yishou, tv_ditu,
                tv_yanjiu, tv_guwen, tv_wode};
        ll = new LinearLayout[]{ll_zaishou, ll_daizu, ll_loupan, ll_yishou, ll_ditu,
                ll_yanjiu, ll_guwen, ll_wode};
        id_check = new int[]{R.mipmap.main_online, R.mipmap.main_wait_rent, R.mipmap.main_seebuild, R.mipmap.main_onehouse,
                R.mipmap.main_map, R.mipmap.main_study, R.mipmap.main_man, R.mipmap.main_myhouse,};
        id_nomal = new int[]{R.mipmap.main_online_normal, R.mipmap.main_wait_rent_normal, R.mipmap.main_seebuild_normal, R.mipmap.main_onehouse_normal,
                R.mipmap.main_map_normal, R.mipmap.main_study_normal, R.mipmap.main_man_normal, R.mipmap.main_myhouse_normal,};

        setOnclick(ll);
        setOnclick(et);
    }

    @Override
    public void afterInitView() {
        DataFromAssets.getGsonData(this);
    }
    private static final int TIEMS = 2000;

    private boolean isBack = true;
    /**
     * 监听back 再2s内再次点击退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){//按下返回键
            if(isBack){//第一次进来
                ToastUtil.showToast("再次点击退出");
                isBack = false;
                //发一个延迟消息 通知handler 2s之后改变isBack的值
                mHandler.sendEmptyMessageDelayed(10001,TIEMS);
            }else{
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 10001:
                    isBack = true;//回到true 需要再点击两次才能退出
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zaishou://在售豪宅
                setTextView(0);
                IntentUtils.openActivity(MainActivity.this, SaleActivity.class);
                break;
            case R.id.ll_daizu://待租豪宅
                setTextView(1);
                break;
            case R.id.ll_loupan://楼盘鉴赏
                setTextView(2);
                IntentUtils.openActivity(MainActivity.this, AppreciationActivity.class);
                break;
            case R.id.ll_yishou://一手豪宅
                IntentUtils.openActivity(MainActivity.this, OneHandActivity.class);
                setTextView(3);
                break;
            case R.id.ll_ditu://地图找房
                IntentUtils.openActivity(MainActivity.this, MapActivity.class);
                setTextView(4);
                break;
            case R.id.ll_yanjiu://豪宅研究
                setTextView(5);
                IntentUtils.openActivity(MainActivity.this, ReSearchActivity.class);
                break;
            case R.id.ll_guwen://豪宅顾问
                setTextView(6);
                break;
            case R.id.ll_wode://我的豪宅
                setTextView(7);
                break;
            case R.id.main_et://搜索
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("chose",4);
                startActivity(intent);
                break;
        }
    }

    public void setTextView(int position) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == position) {
                textViews[i].setTextColor(Color.parseColor("#fff0cb7e"));
                DrawableUtils.drawableTop(this, textViews[i], id_check[i]);
            } else {
                textViews[i].setTextColor(Color.WHITE);
                DrawableUtils.drawableTop(this, textViews[i], id_nomal[i]);
            }
        }
    }
}
