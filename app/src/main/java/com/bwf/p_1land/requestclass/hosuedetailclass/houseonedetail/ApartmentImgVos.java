package com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail;

import java.io.Serializable;

public class ApartmentImgVos implements Serializable{
    public String totalprBegin;

    public String totalprEnd;

    public String innenbereichSize;

    public String bedroomAmount;

    public String parlorAmount;

    public String toiletAmount;

    public String imgUrl;

    @Override
    public String toString() {
        return "ApartmentImgVos{" +
                "totalprBegin='" + totalprBegin + '\'' +
                ", totalprEnd='" + totalprEnd + '\'' +
                ", innenbereichSize='" + innenbereichSize + '\'' +
                ", bedroomAmount='" + bedroomAmount + '\'' +
                ", parlorAmount='" + parlorAmount + '\'' +
                ", toiletAmount='" + toiletAmount + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
