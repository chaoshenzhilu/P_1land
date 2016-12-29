package com.bwf.p_1land.view.test;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.p_1land.R;

/**
 * @author freethinker 类说明 自定义ListView的头标View
 */
public class ListViewHeader extends LinearLayout {
	private ImageView iv;
	private ProgressBar progressBar;
	private TextView tv;
	private LinearLayout view;
	private Animation animationdown,animationup;
	private final int DOWNFLASH=1;//下拉刷新的状态
	private final int  LOOSEFLASH=2;//下松开刷新的状态；
	private final int FLASHING=3;//正在刷新的状态
    private int headState;//
	public ListViewHeader(Context context) {
		super(context);
		view= (LinearLayout) LinearLayout.inflate(context, R.layout.xlistview_header,null);
		LinearLayout.LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//		view.setLayoutParams(params);
//		this.addView(view);
		this.addView(view,params);
		iv = (ImageView) findViewById(R.id.xlistview_header_arrow);
		tv = (TextView) findViewById(R.id.xlistview_header_hint_textview);
		progressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
		animationup= new RotateAnimation(0,180);
		animationup.setDuration(200);
		animationup.setFillAfter(true);
		animationup= new RotateAnimation(180,0);
		animationdown.setDuration(200);
		animationdown.setFillAfter(true);
	}
	//设置头部的新高度
	public void setHeadHeight(int height){
		LayoutParams lp= (LayoutParams) view.getLayoutParams();
		lp.height=height;
		view.setLayoutParams(lp);
	}
    //获得头部的高度
    public int getHeadHeight(){
        LayoutParams lp= (LayoutParams) view.getLayoutParams();
        return lp.height;
    }
	public void setHeadState(int state){
        if(state==headState){
            return;
        }
		switch (state){
			case DOWNFLASH :
                iv.startAnimation(animationdown);
                tv.setText("下拉刷新");
                progressBar.setVisibility(View.GONE);
				break;
			case LOOSEFLASH :
                iv.startAnimation(animationdown);
                tv.setText("松开刷新");
                progressBar.setVisibility(View.GONE);
				break;
			case FLASHING :
                iv.setVisibility(GONE);
                tv.setText("正在刷新");
                progressBar.setVisibility(View.VISIBLE);
				break;
		}
        headState=state;
	}
}
