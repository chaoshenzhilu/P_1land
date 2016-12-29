package com.bwf.p_1land.requestclass.mapclass;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/26.
 */

public  class MapResult implements Serializable{
    /**
     * id : 23008624
     * name : 顺义区
     * num : 98
     * avergPrice : null
     * longitude : 116.662406
     * latitude : 40.14035
     * resblockId : null
     * circleTypeCode : null
     * districtId : 23008624
     * resblockOneId : null
     */

    public String id;
    public String name;
    public String num;
    public Object avergPrice;
    public double longitude;
    public double latitude;
    public Object resblockId;
    public Object circleTypeCode;
    public String districtId;
    public Object resblockOneId;

    @Override
    public String toString() {
        return "MapResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", avergPrice=" + avergPrice +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", resblockId=" + resblockId +
                ", circleTypeCode=" + circleTypeCode +
                ", districtId='" + districtId + '\'' +
                ", resblockOneId=" + resblockOneId +
                '}';
    }
}
