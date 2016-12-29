package com.bwf.p_1land.requestclass.searchclass;

/**
 * Created by Administrator on 2016/12/7.
 */

public class SearchChild {
    public String showImgPath;
    public String pkId;

    public String title;

    public String describe;

    public String wordPath;

    public String createDate;

    @Override
    public String toString() {
        return "SearchChild{" +
                "pkId='" + pkId + '\'' +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", wordPath='" + wordPath + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}

