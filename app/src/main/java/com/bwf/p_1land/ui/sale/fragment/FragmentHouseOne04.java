package com.bwf.p_1land.ui.sale.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.ImgUrlArr;
import com.bwf.p_1land.ui.sale.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 */

public class FragmentHouseOne04 extends BaseFragment {
    @Bind(R.id.fragment04_tv1)
    TextView fragment04Tv1;
    @Bind(R.id.fragment04_viewpager)
    ViewPager fragment04Viewpager;
    private ImageLoader loader;
    private ViewPagerAdapter adapter;

    public ViewPagerAdapter getAdapter() {
        return adapter;
    }

    private boolean isyangbanjian=false;
    private boolean isAuto=false;//自动
    private static final int TIME=3000;
    private int count=-1;
    private boolean iszidong=true;
    private List<ImgUrlArr> imgUrlArrList;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(isAuto){
                        if(iszidong){
                            count++;
                            fragment04Viewpager.setCurrentItem(count);
                            handler.sendEmptyMessageDelayed(1,TIME);
                        }
                    }
                    break;
            }
        }
    };
    @Override
    public int getLayout() {
        return R.layout.fragment_houseone04;
    }

    @Override
    public void beforInit() {

    }

    @Override
    public void init() {
        fragment04Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动中执行
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //选中的时候执行
            @Override
            public void onPageSelected(int position) {
                count=position;
            }
            //状态改变
            @Override
            public void onPageScrollStateChanged(int state) {
                if(isAuto){//样板间viewpager
                    if(state==ViewPager.SCROLL_STATE_IDLE){//停止的时候
                        if(!iszidong){
                            iszidong=true;
                            handler.sendEmptyMessageDelayed(1,TIME);
                        }
                    }else if(state==ViewPager.SCROLL_STATE_DRAGGING){//拖拽
                        iszidong=false;
                        handler.removeMessages(1);
                    }
                }else {//大图点击的viewPager回调控制gallery
                    if(count!=-1){
                        galleryCallBack.changeGallery(count);
                    }
                }
            }
        });
    }

    @Override
    public void dataInit() {

    }

    @Override
    public void afterInit() {
    }
    public void setFragment04Data(List<ImgUrlArr> list,ImageLoader loader,boolean isyangbanjian,boolean isAuto){
        this.loader=loader;
        this.isyangbanjian=isyangbanjian;
        this.isAuto=isAuto;
        if(!isyangbanjian){
            fragment04Tv1.setVisibility(View.GONE);
        }
        imgUrlArrList=new ArrayList<>();
        for(ImgUrlArr imgUrlArr:list){
            if(isyangbanjian){
                if(imgUrlArr.picType.equals("5")){
                    imgUrlArrList.add(imgUrlArr);
                }
            }else {
                imgUrlArrList.add(imgUrlArr);
            }
        }
        adapter=new ViewPagerAdapter(getContext(),imgUrlArrList,loader);
        fragment04Viewpager.setAdapter(adapter);
        if(isAuto){
            handler.sendEmptyMessageDelayed(1,TIME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate MoreHouseOneData fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fragment04_tv1, R.id.fragment04_viewpager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment04_tv1:
                break;
            case R.id.fragment04_viewpager:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
    public void chagePager(List<String> list,int position){
        LogUtils.e(position+"");
        int x=-1;
        for(int i=0;i<imgUrlArrList.size();i++){
            if((imgUrlArrList.get(i).picType).equals(getType(list.get(position)))){
                x=i;
                LogUtils.e(x+"");
                break;
            }
        }
        int y=count-count%imgUrlArrList.size()+x;
        if(x!=-1){
            fragment04Viewpager.setCurrentItem(y);
        }
        LogUtils.e(x+"");
    }
    public String getType(String type) {
        if (type.equals("外景图")) {
            return "1";
        }
        if (type.equals("地理位置图")) {
            return "2";
        }
        if (type.equals("座栋分布图")) {
            return "3";
        }
        if (type.equals("户型图")) {
            return "4";
        }
        if (type.equals("样板间")) {
            return "5";
        }
        if (type.equals("实勘图")) {
            return "6";
        }
        return "";
    }
    private GalleryCallBack galleryCallBack;

    public void setGalleryCallBack(GalleryCallBack galleryCallBack) {
        this.galleryCallBack = galleryCallBack;
    }

    public interface  GalleryCallBack{
        void changeGallery(int position);
    }
    public void setPager(int position){
        fragment04Viewpager.setCurrentItem(position);
    }
}
