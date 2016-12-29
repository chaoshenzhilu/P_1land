package com.bwf.p_1land.requestclass.researchclass;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/9.
 */

public class ResearchResult implements Serializable{
    public String id;
    public String name;
    public String type;

    @Override
    public String toString() {
        return "ResearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
