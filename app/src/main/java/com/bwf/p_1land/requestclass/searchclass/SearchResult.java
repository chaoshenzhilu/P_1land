package com.bwf.p_1land.requestclass.searchclass;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
    public String pkid;

    public String title;

    public String wordPath;

    public String showImgPath;

    public String describe;

    public List<SearchChild> reportList ;

    @Override
    public String toString() {
        return "SearchResult{" +
                "pkid='" + pkid + '\'' +
                ", title='" + title + '\'' +
                ", wordPath='" + wordPath + '\'' +
                ", showImgPath='" + showImgPath + '\'' +
                ", describe='" + describe + '\'' +
                ", reportList=" + reportList +
                '}';
    }
}
