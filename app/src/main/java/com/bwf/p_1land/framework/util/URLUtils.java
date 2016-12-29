package com.bwf.p_1land.framework.util;

/**
 * Created by Administrator on 2016/11/28.
 */

public class URLUtils {
    public static final String BASE_URL = "http://119.254.70.199:8080/landz-app";
    /*  在线房源*/
    public static final String ONLINE_HOUSE = BASE_URL + "/house/houseBuySellList";

    /* 搜索 */
    public static final String SEARCH_URL = BASE_URL + "/homePage/getResblockListByKeyWords";//"keyWords", keyWords type

    /* 一手房子详情 */
    public static final String HOUSE_DETAIL = BASE_URL + "/houseOne/houseOneDetail";
    /* 二手房子详情 */
    public static final String HOUSE_TWO_DETAIL = BASE_URL + "/house/houseDetail";
    /* 一手房子详情-看房记录 */
    public static final String HOUSE_DETAIL_LOOK = BASE_URL + "/see/houseOneDetailSeeHistoryList";
    /* 一手房子详情-看房记录 */
    public static final String HOUSE_TWO_DETAIL_LOOK = BASE_URL + "/see/houseDetailSeeHistoryList";
    /* 一手房源详情更多一手房源推荐(一手房源推荐列表) */
    public static final String HOUSE_ONE_DETAIL_RECOMMEND_LIST_URL = BASE_URL + "/houseOne/houseOneRecommendList";
    /* 顾问带看历史列表 */
    public static final String SEE_HISTORY_LIST_URL = BASE_URL + "/see/seeHistoryList";
   /*豪宅研究*/
   public static final String HOUSE_SEARCH = BASE_URL + "/houseReport/getHouseResportList";
    //一手豪宅
    public static final String ONE_HAND = BASE_URL + "/resblockOne/resblockOneList";
    //更多一手房源推荐
    public static final String MORE_HOUSE_ONE = BASE_URL + "/High_Reasstate_Server/houseOne/houseOneRecommendList";
    public static final String MAP_FOR_HOUSE = BASE_URL+"/lbs/searchhouse";


}
