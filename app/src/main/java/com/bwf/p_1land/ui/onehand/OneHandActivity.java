package com.bwf.p_1land.ui.onehand;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p_1land.MyApplication;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.OnLineHouseRequest;
import com.bwf.p_1land.requestclass.assetsclass.OnlineParam;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResult;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResultData;
import com.bwf.p_1land.requestclass.onehand.OneData;
import com.bwf.p_1land.requestclass.onehand.OneResult;
import com.bwf.p_1land.ui.onehand.adapter.OneHandAdapter;
import com.bwf.p_1land.ui.search.SearchActivity;
import com.bwf.p_1land.view.OneHandPopupWindow;
import com.bwf.p_1land.view.RefreshListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public class OneHandActivity extends BaseActivity{
    private RelativeLayout rlquyu,rljunjia,rlmore;
    private TextView quyu,junjia,more;
    private EditText et;
    private OneHandPopupWindow popupWindow;
    private OnLineHouseRequest onLineHouseRequest;
    private OnlineParam onlineParam;
    private List<OneResult> list;
    private OneHandAdapter adapter;
    private LinearLayout ll;
    private RefreshListView lv;
    private OnlineResultData onlineResultData;
    private  List<OnlineResult> quyuList,junjiaList;

    @Override
    public int getContentViewId() {
        return R.layout.activity_onehand;
    }

    @Override
    public void beforInitView() {
        //获得筛选的数据
        onlineResultData = MyApplication.getApplication().getOnlineResultData();
        //进行分类
        if(onlineResultData != null){
            LogUtils.e(onlineResultData.toString());
            for(OnlineParam onlineParam : onlineResultData.result){
                if(onlineParam.paramType.equals("1001")){//地区
                    quyuList = onlineParam.paramList;
                }
                if(onlineParam.paramType.equals("1003")){//均价
                    junjiaList = onlineParam.paramList;
                }
            }
        }
    }
    @Override
    public void initView() {
        rlquyu=findViewByIdNoCast(R.id.onehand_rlquyu);
        rljunjia=findViewByIdNoCast(R.id.onehand_rljunjia);
        rlmore=findViewByIdNoCast(R.id.onehand_rlmore);
        quyu=findViewByIdNoCast(R.id.onehand_quyu);
        junjia=findViewByIdNoCast(R.id.onehand_junjia);
        more=findViewByIdNoCast(R.id.onehand_more);
        lv=findViewByIdNoCast(R.id.onehand_lv);
        ll=findViewByIdNoCast(R.id.sale_fail_ll);
        et=findViewByIdNoCast(R.id.online_et);
        et.setHint("请输入楼盘名称");
    }

    @Override
    public void initViewData() {
        setOnclick(rlquyu,rljunjia,rlmore,et);
        adapter=new OneHandAdapter(this);
        lv.setAdapter(adapter);
        lv.setRefreh_ListViewListener(new RefreshListView.Refreh_ListViewListener() {
            @Override
            public void onRefresh() {
                onLineHouseRequest.pageNo=0;
                getNetData();
            }

            @Override
            public void onLoadMore() {
                onLineHouseRequest.pageNo++;
                getNetData();
            }
        });
    }

    @Override
    public void afterInitView() {
        showDialog();
        onlineParam=new OnlineParam();
        onLineHouseRequest=new OnLineHouseRequest();
        onLineHouseRequest.pageNo=0;
        getNetData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.onehand_rlquyu:
                popupWindow=new OneHandPopupWindow(this,true,0);
                popupWindow.setPopupAdapter(quyuList,callBackPupopWindow);
                popupWindow.showPopupWindow(rlquyu);
                break;
            case R.id.onehand_rljunjia:
                popupWindow=new OneHandPopupWindow(this,false,1);
                popupWindow.setPopupAdapter(junjiaList,callBackPupopWindow);
                popupWindow.showPopupWindow(rljunjia);
                break;
            case R.id.onehand_rlmore:
                Intent intent=new Intent(OneHandActivity.this,OneMoreActivity.class);
                intent.putExtra("onlineParam",onlineParam);
                startActivityForResult(intent,100);
                break;
            case R.id.online_et:
                Intent inten1=new Intent(OneHandActivity.this, SearchActivity.class);
                inten1.putExtra("chose",1);
                startActivityForResult(inten1,1111);
                break;
        }
    }
    public OneHandPopupWindow.CallBackPupopWindow callBackPupopWindow=new OneHandPopupWindow.CallBackPupopWindow() {
        @Override
        public void changetitle(OnlineResult onlineResult, int position) {
            if(onlineResult==null){
                return;
            }
            switch (position){
                case 0:
                    quyu.setText(onlineResult.name);
                    onLineHouseRequest.circleTypeCode=onlineResult.key;
                    break;
                case 1:
                    junjia.setText(onlineResult.name);
                    onLineHouseRequest.totalPricesEnd=onlineResult.maxValue;
                    LogUtils.e(onLineHouseRequest.totalPricesBegin+" "+onLineHouseRequest.totalPricesEnd);
                    break;
            }
            onLineHouseRequest.pageNo=0;
            getNetData();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==500) {
//            onlineParam = (OnlineParam) data.getSerializableExtra("onlineParam");
//        }
//        onLineHouseRequest.pageNo=0;
//        getNetData();
    }

    public void getNetData(){
        Httphelper.getOneHandData(this,onLineHouseRequest,new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e(result);
                dismissDialog();
                OneData oneData=new Gson().fromJson(result,OneData.class);
                list=oneData.result;
                LogUtils.e(oneData.toString());
                if(onLineHouseRequest.pageNo==0){
                    adapter.setList(oneData.result);
                }else {
                    adapter.addList(oneData.result);
                }
                adapter.notifyDataSetChanged();
                //加载数据刷新适配器之后，只要发现适配器里面没有数据 就显示暂无数据页面
                if(adapter.getCount() == 0){
                    ll.setVisibility(View.VISIBLE);
                }else{
                    ll.setVisibility(View.GONE);
                }
                //如果是适配器里面有数据，且在上拉加载更多的时候，发现加载不到新的数据
                if((list == null ||  list.size() == 0) &&adapter.getCount() > 0){
                    ToastUtil.showToast("没有更多的数据了");
                }
            }

            @Override
            public void onFail(String errMessage) {
                ToastUtil.showToast(errMessage);
                ll.setVisibility(View.VISIBLE);
                dismissDialog();
                lv.setOnComplete();
            }
        });

    }
}
