package com.bwf.p_1land.ui.welcome;

import android.animation.ObjectAnimator;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.util.DisplayUtil;
import com.bwf.p_1land.ui.welcome.adapter.GuideAdapter;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GuideActivity extends BaseActivity{
    private ViewPager viewPager;
    private LinearLayout ll;
    private ImageView guide_iv;
    private ImageView[] iv=new ImageView[3];
    private int[] image=new int[]{R.mipmap.splash_a,R.mipmap.splash_b,R.mipmap.splash_c};
    private GuideAdapter adapter;
    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        viewPager=findViewByIdNoCast(R.id.guide_viewpager);
        guide_iv=findViewByIdNoCast(R.id.guide_iv);
        ll=findViewByIdNoCast(R.id.guide_ll);
        for(int i=0;i<ll.getChildCount();i++){
            iv[i]=(ImageView) ll.getChildAt(i);
        }
        adapter=new GuideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initViewData() {


    }

    @Override
    public void afterInitView() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCheck(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setCheck(0);
    }

    @Override
    public void onClick(View view) {

    }
    void setCheck(int position){
        for (int i = 0; i < iv.length; i++) {
            if (i == position) {
                iv[i].setImageResource(R.mipmap.checked_page);
            }
            iv[i].setImageResource(R.mipmap.unchecked_page);
        }
        guide_iv.setImageResource(image[position]);
        setAnimation();
    }
    public void setAnimation(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(guide_iv,"translationX",0,
                DisplayUtil.getDensity_Width(this)-getResources().getDimension(R.dimen.splash_a),0);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setDuration(30000);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }
}
