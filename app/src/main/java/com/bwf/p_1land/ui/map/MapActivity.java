package com.bwf.p_1land.ui.map;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.mapclass.MapData;
import com.bwf.p_1land.requestclass.mapclass.MapResult;
import com.bwf.p_1land.view.MyMapView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/1.
 */

public class MapActivity extends BaseActivity {

    @Bind(R.id.map_et)
    EditText mapEt;
    @Bind(R.id.map_title)
    RelativeLayout mapTitle;
    @Bind(R.id.map_quyu)
    TextView mapQuyu;
    @Bind(R.id.map_rlquyu)
    RelativeLayout mapRlquyu;
    @Bind(R.id.map_jiage)
    TextView mapJiage;
    @Bind(R.id.map_rljiage)
    RelativeLayout mapRljiage;
    @Bind(R.id.map_type)
    TextView mapType;
    @Bind(R.id.map_rltype)
    RelativeLayout mapRltype;
    @Bind(R.id.map_more)
    TextView mapMore;
    @Bind(R.id.map_rlmore)
    RelativeLayout mapRlmore;
    @Bind(R.id.map_ll)
    LinearLayout mapLl;
    @Bind(R.id.mapView)
    MapView mapView;
    private BaiduMap baiduMap;
    private List<MapResult> list;
    private Marker marker;
    private  MapStatusUpdate mapStatusUpdate;
    @Override
    public int getContentViewId() {
        SDKInitializer.initialize(getApplicationContext());
        return R.layout.activity_map;
    }

    @Override
    public void beforInitView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        baiduMap=mapView.getMap();
        getMapNetData();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
    @Override
    public void initViewData() {

    }

    @Override
    public void afterInitView() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                MapResult mapResult= (MapResult) marker.getExtraInfo().get("MapResult");
                ToastUtil.showToast(mapResult.name);
                return true;
            }
        });
    }

    @OnClick({R.id.map_et, R.id.map_title, R.id.map_rlquyu, R.id.map_rljiage, R.id.map_rltype, R.id.map_rlmore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.map_et:
                break;
            case R.id.map_title:
                break;
            case R.id.map_rlquyu:
                break;
            case R.id.map_rljiage:
                break;
            case R.id.map_rltype:
                break;
            case R.id.map_rlmore:
                break;
        }
    }
    public void getMapNetData(){
        Httphelper.getMapData(this, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("result"+result);
                MapData mapData=new Gson().fromJson(result,MapData.class);
                LogUtils.e(mapData.toString());
                list=mapData.result;
                for(MapResult mapResult:list){
                    setMap(mapResult);
                }
            }
            @Override
            public void onFail(String errMessage) {

            }
        });
    }
    public void setMap(MapResult mapResult){
        MyMapView myMapView=new MyMapView(this);
        myMapView.setText(mapResult.name,mapResult.num);
        BitmapDescriptor descriptor=BitmapDescriptorFactory.fromView(myMapView);
        //默认中心在天安门，修改中心点
        LatLng lng=new LatLng(mapResult.latitude , mapResult.longitude);//经纬度,后面的是经度
        OverlayOptions options=new MarkerOptions().position(lng).icon(descriptor).zIndex(5).draggable(true);
        marker= (Marker) baiduMap.addOverlay(options);
        Bundle bundle=new Bundle();
        bundle.putSerializable("MapResult",mapResult);
        marker.setExtraInfo(bundle);
        mapStatusUpdate= MapStatusUpdateFactory.newLatLng(lng);
        baiduMap.setMapStatus(mapStatusUpdate);
//        //图层
//        MapStatusUpdate mapStatusUpdate1=MapStatusUpdateFactory.zoomTo(19);
//        baiduMap.setMapStatus(mapStatusUpdate1);
//        //开启定位图层
//        baiduMap.setMyLocationEnabled(true);
//        MyLocationData locationData=new MyLocationData.Builder().direction(0)
//                .latitude(30.622164).longitude(104.07646).build();
//        baiduMap.setMyLocationData(locationData);
////        BitmapDescriptor descriptor= BitmapDescriptorFactory.fromResource(R.mipmap.ground_overlay);
//        BitmapDescriptor descriptor=BitmapDescriptorFactory.fromView(myMapView);
//        MyLocationConfiguration configuration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true,descriptor);
//        baiduMap.setMyLocationConfigeration(configuration);
//        baiduMap.setTrafficEnabled(true);
//        baiduMap.setBaiduHeatMapEnabled(true);
    }
}
