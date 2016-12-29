package com.bwf.p_1land.requestclass.mapclass;

import com.bwf.p_1land.framework.base.Basebean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */

public class MapData extends Basebean {
    /**
     * resultStatus : 200
     * resultMsg : ok
     * result : [{"id":"23008624","name":"顺义区","num":"98","avergPrice":null,"longitude":116.662406,"latitude":40.14035,"resblockId":null,"circleTypeCode":null,"districtId":"23008624","resblockOneId":null},{"id":"23008614","name":"东城区","num":"91","avergPrice":null,"longitude":116.430661,"latitude":39.922692,"resblockId":null,"circleTypeCode":null,"districtId":"23008614","resblockOneId":null},{"id":"23008613","name":"朝阳区","num":"416","avergPrice":null,"longitude":116.515282,"latitude":39.94449,"resblockId":null,"circleTypeCode":null,"districtId":"23008613","resblockOneId":null},{"id":"23008618","name":"海淀区","num":"109","avergPrice":null,"longitude":116.303677,"latitude":39.967775,"resblockId":null,"circleTypeCode":null,"districtId":"23008618","resblockOneId":null},{"id":"23008611","name":"昌平区","num":"59","avergPrice":null,"longitude":116.292466,"latitude":40.214726,"resblockId":null,"circleTypeCode":null,"districtId":"23008611","resblockOneId":null},{"id":"23008626","name":"西城区","num":"26","avergPrice":null,"longitude":116.369505,"latitude":39.922582,"resblockId":null,"circleTypeCode":null,"districtId":"23008626","resblockOneId":null},{"id":"23008625","name":"通州区","num":"18","avergPrice":null,"longitude":116.683265,"latitude":39.86296,"resblockId":null,"circleTypeCode":null,"districtId":"23008625","resblockOneId":null},{"id":"23008615","name":"大兴区","num":"7","avergPrice":null,"longitude":116.606729,"latitude":39.816034,"resblockId":null,"circleTypeCode":null,"districtId":"23008615","resblockOneId":null},{"id":"23008617","name":"丰台区","num":"7","avergPrice":null,"longitude":116.290382,"latitude":39.85936,"resblockId":null,"circleTypeCode":null,"districtId":"23008617","resblockOneId":null}]
     */
    public List<MapResult> result;

    @Override
    public String toString() {
        return "MapData{" +
                "result=" + result +
                '}';
    }
}