package com.bwf.p_1land.requestclass.searchclass;

import com.bwf.p_1land.framework.base.Basebean;

public class SearchData extends Basebean{
    public SearchResult result;
    @Override
    public String toString() {
        return "SearchData{" +
                "resultStatus='" + resultStatus + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
