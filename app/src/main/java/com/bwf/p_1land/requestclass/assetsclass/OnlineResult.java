package com.bwf.p_1land.requestclass.assetsclass;

import java.io.Serializable;
import java.util.List;

public class OnlineResult implements Serializable {
    public String key;
    public String name;
    public String value;
    public String minValue;
    public String maxValue;
    public List<OnlineResult> childList;
    public boolean isSelect;
    public OnlineResult(){

    }
    public OnlineResult(String name,String value) {
        this.name=name;
        this.value = value;
    }
    public OnlineResult(String name,String value,boolean isSelect) {
        this.name=name;
        this.value = value;
        this.isSelect=isSelect;
    }

    public OnlineResult(String name, boolean isSelect,List<OnlineResult> resultList) {
        this.name = name;
        this.childList = resultList;
        this.isSelect = isSelect;
    }

    @Override
    public String toString() {
        return "OnlineResult{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", childList=" + childList +
                ", isSelect=" + isSelect +
                '}';
    }
}
