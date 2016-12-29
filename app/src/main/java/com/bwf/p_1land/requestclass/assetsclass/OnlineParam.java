package com.bwf.p_1land.requestclass.assetsclass;

import java.io.Serializable;
import java.util.List;

public  class OnlineParam implements Serializable{
    public String paramType;
    public List<OnlineResult> paramList;

    @Override
    public String toString() {
        return "OnlineParam{" +
                "paramType='" + paramType + '\'' +
                ", resultList=" + paramList +
                '}';
    }
}
