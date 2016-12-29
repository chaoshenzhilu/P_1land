package com.bwf.p_1land.ui.onehand;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.p_1land.MyApplication;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.requestclass.OnLineHouseRequest;
import com.bwf.p_1land.requestclass.assetsclass.OnlineParam;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResult;
import com.bwf.p_1land.requestclass.assetsclass.OnlineResultData;
import com.bwf.p_1land.ui.sale.adapter.PopupWindowAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */

public class OneMoreActivity extends BaseActivity {
    private TextView tv_clean;
    private Button btn;
    private ListView lv1,lv2;
    private OnLineHouseRequest onLineHouseRequest;
    private OnlineResultData onlineResultData;
    private List<OnlineResult> mianji_paramList;
    private OnlineParam onlineParam;
    private PopupWindowAdapter adapter1,adapter2;
    private int position;
    private String[] mianji = {"0.0","100.0","140.0","180.0","220.0","280.0","360.0","420.0","500.0"};
    @Override
    public int getContentViewId() {
        return R.layout.activity_more;
    }
    @Override
    public void beforInitView() {

        onlineResultData = MyApplication.getApplication().getOnlineResultData();
        for (OnlineParam onlineParam : onlineResultData.result) {
            if (onlineParam.paramType.equals("1004")) {
                mianji_paramList = onlineParam.paramList;
            }
        }
        mianji_paramList.get(0).isSelect=true;
        onlineParam= (OnlineParam) this.getIntent().getSerializableExtra("onlineParam");
        if(onlineParam.paramList==null){
            List<OnlineResult> paramList = new ArrayList<>();
            paramList.add(new OnlineResult("排序", true, getPaiXu()));
            paramList.add(new OnlineResult("优惠", false, getyouhui()));
            onlineParam.paramList = paramList;
        }
    }
    /** 特色类型：1今日可看房 2是否钥匙房 */
    public List<OnlineResult> getPaiXu() {
        List<OnlineResult> OnlineResult = new ArrayList<>();
        OnlineResult.add(new OnlineResult("不限", "0",true));
        OnlineResult.add(new OnlineResult("降序", "1"));
        OnlineResult.add(new OnlineResult("升序", "2"));
        return OnlineResult;
    }

    public List<OnlineResult> getyouhui() {
        List<OnlineResult> OnlineResult = new ArrayList<>();
        OnlineResult.add(new OnlineResult("优惠", "0",true));
        OnlineResult.add(new OnlineResult("优惠", "1"));
        OnlineResult.add(new OnlineResult("不优惠", "2"));
        return OnlineResult;
    }

    @Override
    public void initView() {
        btn=findViewByIdNoCast(R.id.more_btn);
        tv_clean=findViewByIdNoCast(R.id.more_clean);
        lv1=findViewByIdNoCast(R.id.more_lv1);
        lv2=findViewByIdNoCast(R.id.more_lv2);
        adapter1=new PopupWindowAdapter(this,false,true);
        adapter2=new PopupWindowAdapter(this,true,true);
        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
        setOnclick(tv_clean,btn);
        setAdapter(onlineParam.paramList);
    }

    public void setAdapter(final List<OnlineResult> resultList) {
        adapter1.setResultList(resultList);
        adapter2.setResultList(resultList.get(0).childList);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectDatas(resultList,i);
                adapter1.notifyDataSetChanged();
                adapter2.setResultList(resultList.get(i).childList);
                adapter2.notifyDataSetChanged();
                position=i;
            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(position==2){
                    setSelectDataMore(resultList.get(position).childList,i);
                }else {
                    setSelectDatas(resultList.get(position).childList,i);
                }
                adapter2.notifyDataSetChanged();

            }
        });
    }
    @Override
    public void initViewData() {}
    @Override
    public void afterInitView() {}
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_clean:
                clen();
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
                break;
            case R.id.more_btn:
                Intent intent=new Intent();
                intent.putExtra("onlineParam",onlineParam);
                this.setResult(500,intent);
                finish();
                break;
        }
    }
    public void clen(){
        LogUtils.e(onlineParam.paramList.toString());
        for(OnlineResult onlineResult:onlineParam.paramList){
            if(onlineResult==onlineParam.paramList.get(0)){
                onlineResult.isSelect=true;
            }else {
                onlineResult.isSelect=false;
            }
            for(OnlineResult onlineResult2:onlineResult.childList){
                if(onlineResult2==onlineResult.childList.get(0)){
                    onlineResult2.isSelect=true;
                }else {
                    onlineResult2.isSelect=false;
                }
            }
        }
    }
    //选中变化
    public void setSelectDatas(List<OnlineResult> resultList, int position){
        for(int i = 0;i< resultList.size();i++){
            if(i == position){
                resultList.get(i).isSelect = true;
            }else{
                resultList.get(i).isSelect = false;
            }
        }
    }
    //特色多选
    public void setSelectDataMore(List<OnlineResult> OnlineResult,int position){
        if(position == 0){
            OnlineResult.get(0).isSelect = true;
            OnlineResult.get(1).isSelect = false;
            OnlineResult.get(2).isSelect =false;
        }else{
            if(OnlineResult.get(position).isSelect){
                OnlineResult.get(position).isSelect = false;
            }else {
                OnlineResult.get(position).isSelect = true;
            }
            OnlineResult.get(0).isSelect =false;
        }
    }
}

