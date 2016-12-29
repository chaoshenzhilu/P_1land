package com.bwf.p_1land.framework.http;

import android.app.Activity;

import com.bwf.p_1land.framework.util.URLUtils;
import com.bwf.p_1land.requestclass.OnLineHouseRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */

public class Httphelper {
    //在售豪宅
    public static void OnlineRequestData(Activity activity, OnLineHouseRequest onLineHouseRequest, HttpRequesAsyncTask.NetCallBack callBack) {
        Request request = new Request(URLUtils.ONLINE_HOUSE, Request.Method.POST, onLineHouseRequest == null ? null : onLineHouseRequest.getRequestMap());
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //豪宅研究
    public static void searcherRequestData(Activity activity, OnLineHouseRequest onLineHouseRequest, HttpRequesAsyncTask.NetCallBack callBack) {
        Request request = new Request(URLUtils.HOUSE_SEARCH, Request.Method.POST, onLineHouseRequest == null ? null : onLineHouseRequest.getRequestMap());
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //搜索
    public static void researchRequestData(Activity activity, Map<String, String> map, HttpRequesAsyncTask.NetCallBack callBack) {
        Request request = new Request(URLUtils.SEARCH_URL, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //楼盘鉴赏
    public static void getAppreciationData(Activity activity, OnLineHouseRequest onLineHouseRequest, HttpRequesAsyncTask.NetCallBack callBack) {
        Request request = new Request(URLUtils.ONLINE_HOUSE, Request.Method.POST, onLineHouseRequest == null ? null : onLineHouseRequest.getRequestMap());
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //一手豪宅
    public static void getOneHandData(Activity activity, OnLineHouseRequest onLineHouseRequest, HttpRequesAsyncTask.NetCallBack callBack) {
        Request request = new Request(URLUtils.ONE_HAND, Request.Method.POST, onLineHouseRequest == null ? null : onLineHouseRequest.getRequestMap());
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //一手房源详情
    public static void getHouseOneData(Activity activity, String houseOneId, HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseOneId", houseOneId);
        Request request = new Request(URLUtils.HOUSE_DETAIL, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }
    //二手房源详情
    public static void getHouseTwoData(Activity activity, String houseOneId, HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseId", houseOneId);
        Request request = new Request(URLUtils.HOUSE_TWO_DETAIL, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //一手房源顾问看房记录
    public static void getHouseOneGuwenData(Activity activity, String houseOneId, HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseOneId", houseOneId);
        Request request = new Request(URLUtils.HOUSE_DETAIL_LOOK, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }
    public static void getHouseTwoGuwenData(Activity activity, String houseId, HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseId", houseId);
        Request request = new Request(URLUtils.HOUSE_TWO_DETAIL_LOOK, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

    //一手房源更多房源
    public static void getMoreOneHandData(Activity activity, HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseId", "f1121");
        map.put("houseId", "f1141");
        map.put("flag", "1");
        map.put("pageNo", "0");
        map.put("pageSize", "3");
        Request request = new Request(URLUtils.HOUSE_ONE_DETAIL_RECOMMEND_LIST_URL, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }
    public static void getMoreOneHandData2(Activity activity, int pageNo,HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("houseId", "f1121");
        map.put("houseId", "f1141");
        map.put("flag", "1");
        map.put("pageNo", pageNo+"");
        map.put("pageSize", "10");
        Request request = new Request(URLUtils.HOUSE_ONE_DETAIL_RECOMMEND_LIST_URL, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }
    public static void getMapData(Activity activity,HttpRequesAsyncTask.NetCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("Server","Apache-Coyote/1.1");
        map.put("Content-Type","application/json;charset=UTF-8");
        Request request = new Request(URLUtils.MAP_FOR_HOUSE, Request.Method.POST, map);
        HttpRequesAsyncTask asyncTask = new HttpRequesAsyncTask(activity, callBack);
        asyncTask.execute(request);
    }

}