package com.bwf.p_1land.ui.sale.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.ImgUrlArr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */

public class ViewPagerAdapter extends PagerAdapter{
    private List<ImgUrlArr> imgUrlArrList;
    private List<ImageView> imageViewList;
    private ImageLoader loader;
    public ViewPagerAdapter(){
    }
    public ViewPagerAdapter(Context context,List<ImgUrlArr> imgUrlArrList, ImageLoader loader){
        this.imgUrlArrList=imgUrlArrList;
        this.loader=loader;
        imageViewList=new ArrayList<>();
        for(int i=0;i<imgUrlArrList.size();i++){
            ImageView imageView=new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViewList.add(imageView);
        }
    }
    @Override
    public int getCount() {
        return imgUrlArrList.size()==1?1:imgUrlArrList.size()*100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(imageViewList.get(position%imageViewList.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imageViewList.get(position%imageViewList.size());
        imageViewList.get(position%imageViewList.size()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.e("dasdasdasdasffasd");
                managerActivity.manager();
            }
        });
        imageView.setImageResource(R.mipmap.defult_onepic);
        loader.displayImg(imgUrlArrList.get(position%imageViewList.size()).picName,imageView);
        if(imageView.getParent()==null){
            container.addView(imageView);
        }else {
            ViewPager v= (ViewPager) imageView.getParent();
            v.removeView(imageView);
            container.addView(imageView);
        }
        return imageView;
    }

    public void setManagerActivity(ManagerActivity managerActivity) {
        this.managerActivity = managerActivity;
    }

    private ManagerActivity managerActivity;
    public interface ManagerActivity{
        void manager();
    }
}
