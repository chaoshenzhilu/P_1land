package com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/11.
 */

public class ImgUrlArr implements Serializable{
    public String resourceId;
    public String picName;
    public String picType;

    @Override
    public String toString() {
        return "ImgUrlArr{" +
                "resourceId='" + resourceId + '\'' +
                ", picName='" + picName + '\'' +
                ", picType='" + picType + '\'' +
                '}';
    }
}

