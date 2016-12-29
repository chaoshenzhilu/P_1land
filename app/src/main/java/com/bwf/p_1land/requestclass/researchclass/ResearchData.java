package com.bwf.p_1land.requestclass.researchclass;

import com.bwf.p_1land.framework.base.Basebean;

import java.util.List;

public class ResearchData extends Basebean {
    public List<ResearchResult> result ;
    @Override
    public String toString() {
        return "ResearchData{" +
                "result=" + result +
                '}';
    }
}
