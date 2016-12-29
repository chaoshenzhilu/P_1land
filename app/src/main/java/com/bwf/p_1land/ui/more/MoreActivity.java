package com.bwf.p_1land.ui.more;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.requestclass.OnLineHouseRequest;
import com.bwf.p_1land.ui.more.adapter.MoreAdapter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */

public class MoreActivity extends BaseActivity {
    private TextView tv_clean;
    private Button btn;
    private ListView lv1,lv2;
    private MoreAdapter adapter1,adapter2;
    private Map<String,List<String>> map;
    private List<String> list;
    private OnLineHouseRequest request;
    private int position;
    private int paiXu,mianJi,teSe,yiShou;
    private boolean more;

    @Override
    public int getContentViewId() {
        return R.layout.activity_more;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        btn=findViewByIdNoCast(R.id.more_btn);
        tv_clean=findViewByIdNoCast(R.id.more_clean);
        lv1=findViewByIdNoCast(R.id.more_lv1);
        lv2=findViewByIdNoCast(R.id.more_lv2);
        setOnclick(tv_clean,btn);
    }

    @Override
    public void initViewData() {
        map=getMap();
        list=getList();
        adapter1=new MoreAdapter(this,false);
        adapter2=new MoreAdapter(this,true);
        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
        setAdapter();
    }
    public void setAdapter(){
        adapter1.setList(list);
        adapter2.setList(map.get(list.get(0)));
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void afterInitView() {
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(position==i){
                    return;
                }
                position=i;
                adapter1.setIsChangeRl(i);
                adapter2.setList(map.get(list.get(i)));
                switch (position){
                    case 0:
                        adapter2.setIsSelect(paiXu);
                        break;
                    case 1:
                        adapter2.setIsSelect(mianJi);
                        break;
                    case 2:
                        if(!more){
                            adapter2.setIsSelect(teSe);
                        }else {
                            adapter2.setMore(true);
                        }
                        break;
                    case 3:
                        adapter2.setIsSelect(yiShou);
                        break;
                }
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter2.setFirstcheck(position);
                adapter2.setIsSelect(i);
                adapter2.notifyDataSetChanged();
                boolean isSelec=true;
                switch (position){
                    case 0:
                        paiXu=i;
                        break;
                    case 1:
                        mianJi=i;
                        break;
                    case 2:
                        if(i==0){
                            more=false;
                        }else {
                            more=true;
                        }
                        break;
                    case 3:
                        yiShou=i;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_clean:
                paiXu=mianJi=teSe=yiShou=0;
                more=false;
                adapter1.setIsChangeRl(0);
                adapter2.setIsSelect(0);
                setAdapter();
                break;
            case R.id.more_btn:
                Intent intent=new Intent();
                this.setResult(200,intent);
                finish();
                break;
        }
    }
    public Map<String,List<String>> getMap(){
        Map<String,List<String>> map= new Hashtable<String, List<String>>();
        List<String> list1=new ArrayList<String>();
        list1.add("默认排序");list1.add("面积从大到小");list1.add("面积从小到大");
        list1.add("总价从低到高");list1.add("总价从高到低");list1.add("关注度从高到低");
        List<String> list2=new ArrayList<String>();
        list2.add("请选择");list2.add("100m²以下");list2.add("100-140m²");
        list2.add("140-180m²");list2.add("180-220m²");list2.add("220-280m²");
        list2.add("280-360m²");list2.add("360-420m²");list2.add("420-500m²");list2.add("500m²以上");
        List<String> list3=new ArrayList<String>();
        list3.add("不限");list3.add("今日可看房");list3.add("钥匙房");
        List<String> list4=new ArrayList<String>();
        list4.add("不限");list4.add("只看一手房");list4.add("只看二手房");
        map.put("排序",list1);map.put("面积",list2);map.put("特色",list3);map.put("一手/二手",list4);
        return map;
    }
    public List<String> getList(){
        List<String> list=new ArrayList<String>();
        list.add("排序");list.add("面积");list.add("特色");list.add("一手/二手");
        return list;
    }
}
