package com.bwf.p_1land.framework.base;

/**
 * Created by Administrator on 2016/11/29.
 */

public class Basebean {
    public String resultStatus;
    public String resultMsg;
    @Override
    public String toString() {
        return "Basebean{" +
                "requescode='" + resultStatus + '\'' +
                ", result='" + resultMsg + '\'' +
                '}';
    }
}
