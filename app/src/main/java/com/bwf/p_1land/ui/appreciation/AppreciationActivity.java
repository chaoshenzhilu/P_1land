package com.bwf.p_1land.ui.appreciation;

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
import com.bwf.p_1land.requestclass.researchclass.ResearchResult;
import com.bwf.p_1land.requestclass.saleclass.OnLineHouseResult;
import com.bwf.p_1land.ui.search.SearchActivity;
import com.bwf.p_1land.ui.onehand.OneMoreActivity;
import com.bwf.p_1land.ui.sale.adapter.SaleAdapter;
import com.bwf.p_1land.view.RefreshListView;
import com.bwf.p_1land.view.SalePopupWindow;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */

public class AppreciationActivity extends BaseActivity {
    private RelativeLayout rlquyu,rljiage,rljushi,rlleixing,rlmore;
    private TextView quyu,jiage,jushi,leixing,more;
    private EditText et;
    private SalePopupWindow popupWindow;
    private OnLineHouseRequest onLineHouseRequest;
    private OnlineParam onlineParam;
    private List<Object> list;
    private SaleAdapter adapter;
    private LinearLayout ll;
    private RefreshListView lv;
    private OnlineResultData onlineResultData;
    private  List<OnlineResult> quyuList,jiageList,jushiList,leixingList;
    @Override
    public int getContentViewId() {
        return R.layout.activity_sale;
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
                if(onlineParam.paramType.equals("1008")){//价格
                    jiageList = onlineParam.paramList;
                }
                if(onlineParam.paramType.equals("1005")){//居室
                    jushiList = onlineParam.paramList;
                }
                if(onlineParam.paramType.equals("1006")){//类型
                    leixingList = onlineParam.paramList;
                }
            }
        }
    }
    @Override
    public void initView() {
        rlquyu=findViewByIdNoCast(R.id.online_rlquyu);
        rljiage=findViewByIdNoCast(R.id.online_rljiage);
        rljushi=findViewByIdNoCast(R.id.online_rljushi);
        rlleixing=findViewByIdNoCast(R.id.online_rlleixing);
        rlmore=findViewByIdNoCast(R.id.online_rlmore);
        quyu=findViewByIdNoCast(R.id.online_quyu);
        jiage=findViewByIdNoCast(R.id.online_jiage);
        jushi=findViewByIdNoCast(R.id.online_jushi);
        leixing=findViewByIdNoCast(R.id.online_leixing);
        more=findViewByIdNoCast(R.id.online_more);
        lv=findViewByIdNoCast(R.id.sale_lv);
        ll=findViewByIdNoCast(R.id.sale_fail_ll);
        et=findViewByIdNoCast(R.id.online_et);
        et.setHint("请输入楼盘或商圈名称");
    }

    @Override
    public void initViewData() {
        setOnclick(rlquyu,rljiage,rljushi,rlleixing,rlmore,et);
        adapter=new SaleAdapter(this);
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
        ResearchResult researchResult= (ResearchResult) this.getIntent().getSerializableExtra("researchResult");
        if(researchResult!=null){
//            onLineHouseRequest.circleTypeCode=researchResult.id;
            onLineHouseRequest.resblockName=researchResult.name;
            onLineHouseRequest.pageNo=0;
            getNetData();
            LogUtils.e(onLineHouseRequest.resblockName);
            LogUtils.e(onLineHouseRequest.circleTypeCode);
            return;
        }
        LogUtils.e(onLineHouseRequest.resblockName);
        LogUtils.e(onLineHouseRequest.circleTypeCode);
        onLineHouseRequest.pageNo=0;
        getNetData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.online_rlquyu:
                popupWindow=new SalePopupWindow(this,true,0);
                popupWindow.setPopupAdapter(quyuList,callBackPupopWindow);
                popupWindow.showPopupWindow(rlquyu);
                break;
            case R.id.online_rljiage:
                popupWindow=new SalePopupWindow(this,false,1);
                popupWindow.setPopupAdapter(jiageList,callBackPupopWindow);
                popupWindow.showPopupWindow(rljiage);
                break;
            case R.id.online_rljushi:
                popupWindow=new SalePopupWindow(this,false,2);
                popupWindow.setPopupAdapter(jushiList,callBackPupopWindow);
                popupWindow.showPopupWindow(rljushi);
                break;
            case R.id.online_rlleixing:
                popupWindow=new SalePopupWindow(this,false,3);
                popupWindow.setPopupAdapter(leixingList,callBackPupopWindow);
                popupWindow.showPopupWindow(rlleixing);
                break;
            case R.id.online_rlmore:
                Intent intent=new Intent(AppreciationActivity.this,OneMoreActivity.class);
                intent.putExtra("onlineParam",onlineParam);
                startActivityForResult(intent,100);
                break;
            case R.id.online_et:
                Intent inten1=new Intent(AppreciationActivity.this, SearchActivity.class);
                inten1.putExtra("chose",1);
                startActivityForResult(inten1,1111);
                break;
        }
    }
    public SalePopupWindow.CallBackPupopWindow callBackPupopWindow=new SalePopupWindow.CallBackPupopWindow() {
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
                    jiage.setText(onlineResult.name);
                    onLineHouseRequest.totalPricesBegin=onlineResult.minValue;
                    onLineHouseRequest.totalPricesEnd=onlineResult.maxValue;
                    LogUtils.e(onLineHouseRequest.totalPricesBegin+" "+onLineHouseRequest.totalPricesEnd);
                    break;
                case 2:
                    jushi.setText(onlineResult.name);
                    onLineHouseRequest.bedroomAmount=onlineResult.value;
                    break;
                case 3:
                    leixing.setText(onlineResult.name);
                    onLineHouseRequest.buildingType=onlineResult.name;
                    break;
            }
            onLineHouseRequest.pageNo=0;
            getNetData();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==500) {
            onlineParam = (OnlineParam) data.getSerializableExtra("onlineParam");
            //储存数据到请求对象中
            if (onLineHouseRequest != null) {
                List<OnlineResult> onlineParams = onlineParam.paramList;
                //排序
                for (OnlineResult paixuResult : onlineParams.get(0).childList) {
                    if (paixuResult.isSelect == true) {
                        onLineHouseRequest.sort = paixuResult.value;
                    }
                }
                //面积
                for (OnlineResult mianjiResult : onlineParams.get(1).childList) {
                    if (mianjiResult.isSelect == true) {
                        onLineHouseRequest.buildSizeBegin = mianjiResult.minValue;
                        onLineHouseRequest.buildSizeEnd = mianjiResult.maxValue;

                    }
                }
                //特色
                List<OnlineResult> tesheResult = onlineParams.get(2).childList;
                //多项选择
                if (tesheResult.get(0).isSelect) {
                    onLineHouseRequest.feature = "0";
                } else {
                    if (tesheResult.get(1).isSelect) {
                        onLineHouseRequest.feature = "1";
                    }
                    if (tesheResult.get(2).isSelect) {
                        onLineHouseRequest.feature = "2";
                    }
                    if (tesheResult.get(1).isSelect && tesheResult.get(2).isSelect) {
                        onLineHouseRequest.feature = "1,2";
                    }
                }
                //一手 二手
                for (OnlineResult handReault : onlineParams.get(3).childList) {
                    if (handReault.isSelect == true) {
                        onLineHouseRequest.onlyLook = handReault.value;
                    }
                }
            }
        }
        LogUtils.e(resultCode+"");
        onLineHouseRequest.pageNo=0;
        getNetData();
    }

    public void getNetData(){
        Httphelper.OnlineRequestData(this,onLineHouseRequest,new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e(result);
                dismissDialog();
                OnLineHouseResult onLineHouseResult=new Gson().fromJson(result,OnLineHouseResult.class);
                LogUtils.e(onLineHouseResult.toString());
                list=onLineHouseResult.initListData();
                if(onLineHouseRequest.pageNo==0){
                    adapter.setList(list);
                }else {
                    adapter.addList(list);
                }
                adapter.notifyDataSetChanged();
                if(adapter.getCount()==0){
                    ll.setVisibility(View.VISIBLE);
                }else {
                    ll.setVisibility(View.GONE);
                }
                lv.setOnComplete();
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
