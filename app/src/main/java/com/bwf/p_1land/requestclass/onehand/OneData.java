package com.bwf.p_1land.requestclass.onehand;

import com.bwf.p_1land.framework.base.Basebean;

import java.util.List;

public class OneData extends Basebean{
    public List<OneResult> result ;

    @Override
    public String toString() {
        return "OneData{" +
                "result=" + result +
                '}';
    }
}
