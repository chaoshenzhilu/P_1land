package com.bwf.p_1land.ui.sale;

import android.view.View;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.morehouseoneclass.MoreHouseOneData;
import com.bwf.p_1land.ui.sale.adapter.MoreHouseOneAdapter;
import com.bwf.p_1land.view.RefreshListView;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MoreHouseOneActivity extends BaseActivity {
    private RefreshListView listView;
    private MoreHouseOneAdapter adapter;
    private int pageNo=0;
    @Override
    public int getContentViewId() {
        return R.layout.activity_morehouseone;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        listView=findViewByIdNoCast(R.id.more_houseonelv);
        adapter=new MoreHouseOneAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void initViewData() {
        showDialog();
        getMoreHouseOneData();
    }

    @Override
    public void afterInitView() {
        listView.setRefreh_ListViewListener(new RefreshListView.Refreh_ListViewListener() {
            @Override
            public void onRefresh() {
                pageNo=0;
                getMoreHouseOneData();
            }

            @Override
            public void onLoadMore() {
                pageNo++;
                getMoreHouseOneData();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
    public void getMoreHouseOneData(){
        Httphelper.getMoreOneHandData2(this, pageNo, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissDialog();
                MoreHouseOneData moreHouseOneData=new Gson().fromJson(result,MoreHouseOneData.class);
                if(moreHouseOneData!=null){
                    if(pageNo==0){
                        adapter.setList(moreHouseOneData.result);
                    }else {
                        adapter.addList(moreHouseOneData.result);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String errMessage) {
                dismissDialog();
                ToastUtil.showToast(errMessage);
            }
        });
    }
}
