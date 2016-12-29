package com.bwf.p_1land.ui.welcome.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.share.ShareHelper;
import com.bwf.p_1land.framework.util.DisplayUtil;
import com.bwf.p_1land.framework.util.IntentUtils;
import com.bwf.p_1land.ui.MainActivity;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GuideFragment extends BaseFragment{
    private TextView tv1,tv2;
    private View view;
    private int position;
    private Button btn;
    private LinearLayout ll;
    public static GuideFragment getFragment(int position){
        GuideFragment fragment=new GuideFragment();
        fragment.position=position;
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragmen_guide;
    }

    @Override
    public void beforInit() {

    }

    @Override
    public void init() {
        tv1=findViewByIdNoCast(R.id.guide_tv1);
        tv2=findViewByIdNoCast(R.id.guide_tv2);
        btn=findViewByIdNoCast(R.id.guide_btn);
        ll=findViewByIdNoCast(R.id.fragment_ll);
        btn.setOnClickListener(this);
    }

    @Override
    public void dataInit() {
        //设置文字的边框的宽度
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll.getLayoutParams();
        params.width = DisplayUtil.getDensity_Width(getContext());//设置成屏幕的宽度
        ll.setLayoutParams(params);
        switch (position){
            case 0:
                tv1.setText(getString(R.string.splash_tip1));
                tv2.setText(getString(R.string.splash_tip01));
                break;
            case 1:
                tv1.setText(getString(R.string.splash_tip2));
                tv2.setText(getString(R.string.splash_tip02));
                break;
            case 2:
                tv1.setText(getString(R.string.splash_tip3));
                tv2.setText(getString(R.string.splash_tip03));
                btn.setVisibility(View.VISIBLE);
                btn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //设置体验Button的位置
                        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) btn.getLayoutParams();
                        //设置成屏幕的宽度减去自己的宽度除以2
                        params1.leftMargin = (DisplayUtil.getDensity_Width(getContext())-btn.getWidth())/2;
                        btn.setLayoutParams(params1);
                    }
                },500);
                break;
        }
    }

    @Override
    public void afterInit() {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.guide_btn){
            ShareHelper helper=ShareHelper.getInstance(getContext());
            helper.putBoolean("isfirst",false);
            IntentUtils.openActivity(getContext(), MainActivity.class);
            getActivity().finish();
        }
    }
}
