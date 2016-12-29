package com.bwf.p_1land.requestclass.assetsclass;

import com.bwf.p_1land.framework.base.Basebean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class OnlineResultData extends Basebean {
    public List<OnlineParam> result;

    @Override
    public String toString() {
        return "OnlineResultData{" +
                "lista=" + result +
                '}';
    }
}


