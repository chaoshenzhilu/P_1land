package com.bwf.p_1land.requestclass.morehouseoneclass;

import com.bwf.p_1land.framework.base.Basebean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MoreHouseOneData extends Basebean{
    /**
     * resultStatus : 200
     * resultMsg : ok
     * result : [{"resblockOneId":"f355","houseOneId":"f316","resblockOneName":"紫御华府","bedroomAmount":4,"parlorAmount":4,"buildSize":380,"totalprBegin":2700,"totalprEnd":4000,"titlepicImg":"http://119.254.70.187/group1/M00/05/D8/rBAOJ1YfWQiEcWFiAAAAABxyNqA445.jpg","circleTypeName":"亚奥别墅区","resblockType":"公寓","totalShowing":1194,"sortNum":0,"unitprBegin":8.5,"unitprEnd":10.5,"totalNumber":203},{"resblockOneId":"f1130","houseOneId":"f1061","resblockOneName":"首开琅樾","bedroomAmount":4,"parlorAmount":2,"buildSize":230,"totalprBegin":900,"totalprEnd":1000,"titlepicImg":"http://119.254.70.187/group1/M00/05/CF/rBAOKFYbv3SEHTvjAAAAABbooy8598.jpg","circleTypeName":"中央别墅区","resblockType":"别墅","totalShowing":873,"sortNum":0,"unitprBegin":3.9,"unitprEnd":5,"totalNumber":0},{"resblockOneId":"f441","houseOneId":"f274","resblockOneName":"红玺台","bedroomAmount":4,"parlorAmount":2,"buildSize":310,"totalprBegin":3196,"totalprEnd":3391,"titlepicImg":"http://119.254.70.187/group1/M00/05/F0/rBAOJ1Yt6IaECfLKAAAAAPmDxa0949.jpg","circleTypeName":"太阳宫","resblockType":"公寓","totalShowing":829,"sortNum":0,"unitprBegin":9.8,"unitprEnd":11,"totalNumber":0}]
     */
    public List<MoreResult> result;

    @Override
    public String toString() {
        return "MoreHouseOneData{" +
                "result=" + result +
                '}';
    }
}
