package com.bwf.p_1land.ui.sale.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseFragment;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;
import com.bwf.p_1land.ui.sale.ThumbnailActivity;
import com.bwf.p_1land.ui.sale.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/11.
 */

public class FragmentHouseOne01 extends BaseFragment {
    @Bind(R.id.fragment01_iv1)
    ImageView fragment01Iv1;
    @Bind(R.id.fragment01_tv1)
    TextView fragment01Tv1;
    @Bind(R.id.fragment01_iv2)
    ImageView fragment01Iv2;
    private int line=0;
    private HouseOneData houseOneData;
    private ImageLoader loader;
    private ViewPagerAdapter adapter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_houseone01;
    }

    @Override
    public void beforInit() {

    }

    @Override
    public void init() {
    }


    @Override
    public void dataInit() {
        if(houseOneData!=null){
            loader.displayImg(houseOneData.result.titlepicImg, fragment01Iv1);
            fragment01Tv1.setText(houseOneData.result.roomDescripe);
            fragment01Iv1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    line = fragment01Tv1.getLineCount();
                    if (line > 4) {
                        fragment01Tv1.setLines(4);
                    } else {
                        fragment01Tv1.setLines(line);
                    }
                }
            }, 50);
        }
    }

    @Override
    public void afterInit() {
    }

    public void setFragment01Data(HouseOneData houseOneData, ImageLoader loader) {
        this.houseOneData=houseOneData;
        this.loader=loader;
        dataInit();

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
    boolean ischange = false;
    @OnClick({R.id.fragment01_iv1, R.id.fragment01_iv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment01_iv1:
                startActivity();
                break;
            case R.id.fragment01_iv2:
                if (ischange) {
                    fragment01Iv2.setImageResource(R.mipmap.first_down);
                    if (line > 4) {
                        fragment01Tv1.setLines(4);
                    } else {
                        fragment01Tv1.setLines(line);
                    }
                    ischange = false;
                } else {
                    fragment01Iv2.setImageResource(R.mipmap.content_up);
                    fragment01Tv1.setLines(line);
                    ischange = true;
                }
                break;
        }
    }
    public void startActivity(){
        Intent intent=new Intent(getActivity(), ThumbnailActivity.class);
        intent.putExtra("HouseOneResult",houseOneData.result);
        startActivity(intent);
    }
}
