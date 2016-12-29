package com.bwf.p_1land.requestclass.guwenclass.guwen2;

import com.bwf.p_1land.requestclass.guwenclass.guwen1.ShowArrBean;

import java.util.List;

public  class HouseTwoGuWenData {
    /**
     * totalAmount : 13
     * showArr : [{"resourceId":"s216185387","showStartTime":1479021300000,"createdTime":1479021300000,"createdBy":"126012","createName":"王强","phone":"18612135865","photo":"http://119.254.70.187/group1/M00/07/0A/rBAOKFcMyS-ECphuAAAAAH4h6MU308.jpg","inductionDate":"2年","totalShowing":2},{"resourceId":"s216185387","showStartTime":1479031200000,"createdTime":1479031200000,"createdBy":"124254","createName":"曲旖旎","phone":"18900128430","photo":"http://119.254.70.187/group1/M00/07/0C/rBAOKFcMyXuECQ8iAAAAAOCjREg495.jpg","inductionDate":"3年6个月","totalShowing":1},{"resourceId":"s216185387","showStartTime":1479013500000,"createdTime":1479013500000,"createdBy":"129172","createName":"冯诚诚","phone":"17701273827","photo":"http://119.254.70.187/group1/M00/07/0F/rBAOKFcMycmEIsfnAAAAAOypUBs663.jpg","inductionDate":"1年3个月","totalShowing":1},{"resourceId":"s216185387","showStartTime":1479013200000,"createdTime":1479013200000,"createdBy":"125953","createName":"刘晓伟","phone":"13511055843","photo":"http://119.254.70.187/group1/M00/07/0C/rBAOKFcMyWyECyqJAAAAAGs71mE695.jpg","inductionDate":"2年1个月","totalShowing":1}]
     */

    public String totalAmount;
    public List<ShowArrBean> showArr;

    @Override
    public String toString() {
        return "HouseTwoGuWenData{" +
                "totalAmount='" + totalAmount + '\'' +
                ", showArr=" + showArr +
                '}';
    }
}
