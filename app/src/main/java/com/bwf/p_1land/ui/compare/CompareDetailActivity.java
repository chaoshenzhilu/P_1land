package com.bwf.p_1land.ui.compare;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.requestclass.guwenclass.guwen1.HouseOneGuwenData;
import com.bwf.p_1land.requestclass.guwenclass.guwen1.ShowArrBean;
import com.bwf.p_1land.requestclass.guwenclass.guwen2.HouseTwoGuWenResult;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.ImgUrlArr;
import com.bwf.p_1land.requestclass.hosuedetailclass.housetwodetail.HouseTwoDetailResult;
import com.bwf.p_1land.requestclass.hosuedetailclass.housetwodetail.ImgUrlArrBean;
import com.bwf.p_1land.requestclass.saleclass.HouseArrBean;
import com.bwf.p_1land.requestclass.saleclass.HouseOneArrBean;
import com.bwf.p_1land.requestclass.saleclass.OnLineHouseResult;
import com.bwf.p_1land.view.MyHorizontalScrollView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class CompareDetailActivity extends BaseActivity {
    private List<String> list,listtwo;
    private List<ShowArrBean> guWenList;
    private int count;
    private ScrollView scrollView;
    private MyHorizontalScrollView hscrollView_name, hscrollView_detail;
    private LinearLayout llNmae, llDetail;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private OnLineHouseResult onLineHouseResult;
    private List<Object> objectList;

    @Override
    public int getContentViewId() {
        return R.layout.activity_comparedetail;
    }

    @Override
    public void beforInitView() {
        onLineHouseResult=new OnLineHouseResult();
        onLineHouseResult.result=getIntent().getParcelableExtra("OnLineHouseResultBean");
        objectList=onLineHouseResult.initListData();
    }

    @Override
    public void initView() {
        scrollView = findViewByIdNoCast(R.id.compare_scrollview);
        hscrollView_name = findViewByIdNoCast(R.id.compare_hscrollViewName);
        hscrollView_detail = findViewByIdNoCast(R.id.compare_hscrollView_detail);
        llNmae = findViewByIdNoCast(R.id.compare_name);
        llDetail = findViewByIdNoCast(R.id.compare_detail);
    }

    @Override
    public void initViewData() {
        guWenList=new ArrayList<>();
        list = new ArrayList<>();
        listtwo = new ArrayList<>();
        for (int i = 0; i < objectList.size(); i++) {
            if(objectList.get(i) instanceof HouseOneArrBean){
                HouseOneArrBean houseOneArrBean= (HouseOneArrBean) objectList.get(i);
                list.add(houseOneArrBean.houseOneId);
                getGuWenImage(list.get(i),i);

            }else {
                LogUtils.e("aaaaaaaaaaa");
                HouseArrBean houseArrBean= (HouseArrBean) objectList.get(i);
                list.add(houseArrBean.housedelId);
                LogUtils.e(houseArrBean.housedelId);
                getGuWenImage2(list.get(i),i);
            }
        }
    }

    @Override
    public void afterInitView() {
        hscrollView_name.setListener(listener);
        hscrollView_detail.setListener(listener);
    }

    @Override
    public void onClick(View view) {

    }
    private MyHorizontalScrollView.MyScrollViewListener listener=new MyHorizontalScrollView.MyScrollViewListener() {
        @Override
        public void scrollview(MyHorizontalScrollView scrollView, int l, int t, int oldl, int oldt) {
            if(scrollView==hscrollView_name){
                hscrollView_detail.scrollTo(l,t);
            }else {
                hscrollView_name.scrollTo(l,t);
            }
        }
    };

    public void getCompareData(String houseoneId, final String guwenrul, final String phone) {
        Httphelper.getHouseOneData(this, houseoneId, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                HouseOneData houseOneData = new Gson().fromJson(result, HouseOneData.class);
                LogUtils.e(houseOneData.toString());
                setCompareDetail(houseOneData,guwenrul,phone);
                LogUtils.e(phone);
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
    }
    public void getCompareData2(String houseId, final String guwenrul, final String phone) {
        Httphelper.getHouseTwoData(this, houseId, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e(result);
                HouseTwoDetailResult houseTwoResult= new Gson().fromJson(result, HouseTwoDetailResult.class);
                LogUtils.e(houseTwoResult.toString());
                setCompareDetail(houseTwoResult,guwenrul,phone);
                LogUtils.e(phone);
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
    }

    public void getGuWenImage(String houseId, final int i){
        Httphelper.getHouseOneGuwenData(this, houseId, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                HouseOneGuwenData houseOneGuwenData = new Gson().fromJson(result, HouseOneGuwenData.class);
                guWenList.add(houseOneGuwenData.result.showArr.get(0));
                getCompareData(list.get(i),houseOneGuwenData.result.showArr.get(0).photo,houseOneGuwenData.result.showArr.get(0).phone);
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
    }
    public void getGuWenImage2(String houseId, final int i){
        Httphelper.getHouseTwoGuwenData(this, houseId, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e(result);
                HouseTwoGuWenResult houseGuwenResult = new Gson().fromJson(result, HouseTwoGuWenResult.class);
                LogUtils.e(houseGuwenResult.toString());
                guWenList.add(houseGuwenResult.result.showArr.get(0));
                getCompareData2(list.get(i),houseGuwenResult.result.showArr.get(0).photo,houseGuwenResult.result.showArr.get(0).phone);
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
    }

    public void setCompareDetail(Object object,String guwenur,String phone) {
        HouseOneData houseOneData = null;
        HouseTwoDetailResult houseTwoResult = null;
        if (object instanceof HouseOneData) {
            houseOneData = (HouseOneData) object;
            setNameOne(houseOneData);
            setDataOne(houseOneData,guwenur,phone);
        } else {
            houseTwoResult = (HouseTwoDetailResult) object;
            setNameTwo(houseTwoResult);
            setDataTwo(houseTwoResult,guwenur,phone);
        }
    }

    public void setNameOne(HouseOneData houseOneData){
        final RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,150), (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,100));
        params.addRule(RelativeLayout.ALIGN_RIGHT);
        rl.setLayoutParams(params);
        rl.setBackground(getResources().getDrawable(R.drawable.comparshape));
        TextView tv = new TextView(this);
        tv.setLayoutParams(params);
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(params1);
        tv.setTextColor(Color.parseColor("#41B3E1"));
        tv.setTextSize(16);
        tv.setText(houseOneData.result.resblockOneName);
        LogUtils.e(tv.getText().toString());
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.close);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(40, 40);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.ALIGN_PARENT_TOP);
        iv.setLayoutParams(params2);
        rl.addView(tv);
        rl.addView(iv);
        llNmae.addView(rl);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x=-1;
                for(int i=0;i<llNmae.getChildCount();i++){
                    if(rl==llNmae.getChildAt(i)){
                        x=i;
                        break;
                    }
                }
                if(x!=-1){
                    llNmae.removeViewAt(x);
                    llDetail.removeViewAt(x);
                }
            }
        });
    }
    public void setNameTwo(HouseTwoDetailResult houseTwoResult){
        final RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,150), (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,100));
        params.addRule(RelativeLayout.ALIGN_RIGHT);
        rl.setLayoutParams(params);
        rl.setBackground(getResources().getDrawable(R.drawable.comparshape));
        TextView tv = new TextView(this);
        tv.setLayoutParams(params);
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(params1);
        tv.setTextColor(Color.parseColor("#41B3E1"));
        tv.setTextSize(16);
        tv.setText(houseTwoResult.result.resblockName);
        LogUtils.e(tv.getText().toString());
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.close);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(40, 40);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.ALIGN_PARENT_TOP);
        iv.setLayoutParams(params2);
        rl.addView(tv);
        rl.addView(iv);
        llNmae.addView(rl);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x=-1;
                for(int i=0;i<llNmae.getChildCount();i++){
                    if(rl==llNmae.getChildAt(i)){
                        x=i;
                        break;
                    }
                }
                if(x!=-1){
                    llNmae.removeViewAt(x);
                    llDetail.removeViewAt(x);
                }
            }
        });
    }
    public void setDataOne(HouseOneData houseOneData,String guwenurl,String phone) {
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,150), (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,100));
        ll.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout rlshikan = new RelativeLayout(this);
        RelativeLayout rlhuxing = new RelativeLayout(this);
        rlshikan.setLayoutParams(params);
        rlhuxing.setLayoutParams(params);
        String shikantu = "";
        String huxingtu = "";
        String guwen = "";
        boolean shikan = true, huxing = true;
        for (ImgUrlArr imgUrlArr : houseOneData.result.imgUrlArr) {
            if ((imgUrlArr.picType.equals("6")) && shikan) {
                shikantu = imgUrlArr.picName;
                shikan = false;
            } else if ((imgUrlArr.picType.equals("4")) && huxing) {
                huxingtu = imgUrlArr.picName;
                huxing = false;
            }
        }
        LogUtils.e(shikantu);
        LogUtils.e(huxingtu);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        ImageView ivshikan = new ImageView(this);
        params1.width = (int)getRawSize(TypedValue.COMPLEX_UNIT_DIP,100);
        params1.height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,80);
        ivshikan.setScaleType(ImageView.ScaleType.FIT_XY);
        ivshikan.setLayoutParams(params1);
        ivshikan.setBackground(getResources().getDrawable(R.drawable.comparshape));
        ImageView ivhuxing = new ImageView(this);
        ivhuxing.setLayoutParams(params1);
        ivhuxing.setScaleType(ImageView.ScaleType.FIT_XY);
        ivhuxing.setBackground(getResources().getDrawable(R.drawable.comparshape));
        Glide.with(this).load(shikantu).into(ivshikan);
        Glide.with(this).load(huxingtu).into(ivhuxing);
        rlshikan.addView(ivshikan);
        rlhuxing.addView(ivhuxing);
        ll.addView(rlshikan);
        ll.addView(rlhuxing);
        TextView danjia=getText();
        danjia.setText(houseOneData.result.unitprBegin+"-"+houseOneData.result.unitprEnd+"万元");
        TextView price=getText();
        price.setText(houseOneData.result.totalprBegin+"-"+houseOneData.result.totalprEnd+"万元");
        TextView yingyeshui=getText();
        yingyeshui.setText("核算中");
        TextView geshui=getText();
        geshui.setText("核算中");
        TextView qishui=getText();
        qishui.setText("核算中");
        TextView shoufang=getText();
        shoufang.setText("/");
        TextView erfang=getText();
        erfang.setText("/");
        TextView jushi=getText();
        jushi.setText("/");
        TextView mianji=getText();
        mianji.setText(houseOneData.result.buildSize+"㎡");
        TextView louceng=getText();
        louceng.setText("/");
        TextView chaoxiang=getText();
        chaoxiang.setText("/");
        TextView zhuangxiu=getText();
        zhuangxiu.setText(houseOneData.result.decorationName);
        TextView schoolName=getText();
        jushi.setText("暂无");
        TextView schoolLevel=getText();
        schoolLevel.setText("暂无");
        TextView blocks=getText();
        blocks.setText("暂无");
        TextView schoolDistance=getText();
        schoolDistance.setText("暂无");
        TextView chengqu=getText();
        chengqu.setText(houseOneData.result.districtName);
        TextView shangquan=getText();
        shangquan.setText(houseOneData.result.circleTypeName);
        TextView address=getText();
        address.setText(houseOneData.result.resblockAddr);
        TextView buildyear=getText();
        buildyear.setText("/");
        TextView buildyear2=getText();
        buildyear2.setText("/");
        RelativeLayout guwenrl=new RelativeLayout(this);
        guwenrl.setLayoutParams(params);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        ImageView gunwen = new ImageView(this);
        layoutParams.width = (int)getRawSize(TypedValue.COMPLEX_UNIT_DIP,80);
        layoutParams.height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,100);
        gunwen.setScaleType(ImageView.ScaleType.FIT_XY);
        gunwen.setLayoutParams(layoutParams);
        Glide.with(this).load(guwenurl).into(gunwen);
        guwenrl.addView(gunwen);
        TextView dianhua=getText();
        dianhua.setText(phone);
        ll.addView(danjia);
        ll.addView(price);
        ll.addView(yingyeshui);
        ll.addView(geshui);
        ll.addView(qishui);
        ll.addView(shoufang);
        ll.addView(erfang);
        ll.addView(jushi);
        ll.addView(mianji);
        ll.addView(louceng);
        ll.addView(chaoxiang);
        ll.addView(zhuangxiu);
        ll.addView(schoolName);
        ll.addView(schoolLevel);
        ll.addView(blocks);
        ll.addView(schoolDistance);
        ll.addView(chengqu);
        ll.addView(shangquan);
        ll.addView(address);
        ll.addView(buildyear);
        ll.addView(buildyear2);
        ll.addView(guwenrl);
        ll.addView(dianhua);
        llDetail.addView(ll);
    }
    public void setDataTwo(HouseTwoDetailResult houseTwoResult,String guwenurl,String phone) {
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,150), (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,100));
        ll.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout rlshikan = new RelativeLayout(this);
        RelativeLayout rlhuxing = new RelativeLayout(this);
        rlshikan.setLayoutParams(params);
        rlhuxing.setLayoutParams(params);
        String shikantu = "";
        String huxingtu = "";
        String guwen = "";
        boolean shikan = true, huxing = true;
        for (ImgUrlArrBean imgUrlArrBean : houseTwoResult.result.imgUrlArr) {
            if ((imgUrlArrBean.picType.equals("6")) && shikan) {
                shikantu = imgUrlArrBean.pictureName;
                shikan = false;
            } else if ((imgUrlArrBean.picType.equals("4")) && huxing) {
                huxingtu = imgUrlArrBean.pictureName;
                huxing = false;
            }
        }
        LogUtils.e(shikantu);
        LogUtils.e(huxingtu);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        ImageView ivshikan = new ImageView(this);
        params1.width = (int)getRawSize(TypedValue.COMPLEX_UNIT_DIP,100);
        params1.height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,80);
        ivshikan.setScaleType(ImageView.ScaleType.FIT_XY);
        ivshikan.setLayoutParams(params1);
        ivshikan.setBackground(getResources().getDrawable(R.drawable.comparshape));
        ImageView ivhuxing = new ImageView(this);
        ivhuxing.setLayoutParams(params1);
        ivhuxing.setScaleType(ImageView.ScaleType.FIT_XY);
        ivhuxing.setBackground(getResources().getDrawable(R.drawable.comparshape));
        Glide.with(this).load(shikantu).into(ivshikan);
        Glide.with(this).load(huxingtu).into(ivhuxing);
        rlshikan.addView(ivshikan);
        rlhuxing.addView(ivhuxing);
        ll.addView(rlshikan);
        ll.addView(rlhuxing);
        TextView danjia=getText();
        danjia.setText(houseTwoResult.result.unitPrice+"万元");
        TextView price=getText();
        price.setText(houseTwoResult.result.totalPrices+"万元");
        TextView yingyeshui=getText();
        yingyeshui.setText("核算中");
        TextView geshui=getText();
        geshui.setText("核算中");
        TextView qishui=getText();
        qishui.setText("核算中");
        TextView shoufang=getText();
        shoufang.setText("/");
        TextView erfang=getText();
        erfang.setText("/");
        TextView jushi=getText();
        jushi.setText("/");
        TextView mianji=getText();
        mianji.setText(houseTwoResult.result.buildSize+"㎡");
        TextView louceng=getText();
        louceng.setText("/");
        TextView chaoxiang=getText();
        chaoxiang.setText("/");
        TextView zhuangxiu=getText();
        zhuangxiu.setText("");
        TextView schoolName=getText();
        jushi.setText("暂无");
        TextView schoolLevel=getText();
        schoolLevel.setText("暂无");
        TextView blocks=getText();
        blocks.setText("暂无");
        TextView schoolDistance=getText();
        schoolDistance.setText("暂无");
        TextView chengqu=getText();
        chengqu.setText(houseTwoResult.result.districtName);
        TextView shangquan=getText();
        shangquan.setText(houseTwoResult.result.circleTypeName);
        TextView address=getText();
        address.setText(houseTwoResult.result.propertyAddress);
        TextView buildyear=getText();
        buildyear.setText("/");
        TextView buildyear2=getText();
        buildyear2.setText(houseTwoResult.result.buildingType);
        RelativeLayout guwenrl=new RelativeLayout(this);
        guwenrl.setLayoutParams(params);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        ImageView gunwen = new ImageView(this);
        layoutParams.width = (int)getRawSize(TypedValue.COMPLEX_UNIT_DIP,80);
        layoutParams.height = (int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,100);
        gunwen.setScaleType(ImageView.ScaleType.FIT_XY);
        gunwen.setLayoutParams(layoutParams);
        Glide.with(this).load(guwenurl).into(gunwen);
        guwenrl.addView(gunwen);
        TextView dianhua=getText();
        dianhua.setText(phone);
        ll.addView(danjia);
        ll.addView(price);
        ll.addView(yingyeshui);
        ll.addView(geshui);
        ll.addView(qishui);
        ll.addView(shoufang);
        ll.addView(erfang);
        ll.addView(jushi);
        ll.addView(mianji);
        ll.addView(louceng);
        ll.addView(chaoxiang);
        ll.addView(zhuangxiu);
        ll.addView(schoolName);
        ll.addView(schoolLevel);
        ll.addView(blocks);
        ll.addView(schoolDistance);
        ll.addView(chengqu);
        ll.addView(shangquan);
        ll.addView(address);
        ll.addView(buildyear);
        ll.addView(buildyear2);
        ll.addView(guwenrl);
        ll.addView(dianhua);
        llDetail.addView(ll);
    }
    public TextView getText(){
        TextView textView=new TextView(this);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams((int)getRawSize(TypedValue.COMPLEX_UNIT_DIP,150),(int) getRawSize(TypedValue.COMPLEX_UNIT_DIP,50));
        textView.setLayoutParams(params);
        textView.setTextSize(14);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setBackground(getResources().getDrawable(R.drawable.comparshape));
        return textView;
    }

    public float getRawSize(int unit, float value) {
        Resources res = this.getResources();
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics());
    }
}
