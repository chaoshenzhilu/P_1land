package com.bwf.p_1land.requestclass.saleclass;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by cao_ye on 2016/7/19 0019.
 * Description:
 */
public class OnLineHouseResultBean implements Parcelable {

    public List<HouseArrBean> houseArr;
    public List<HouseOneArrBean> houseOneArr;

    @Override
    public String toString() {
        return "OnLineHouseResultBean{" +
                "houseArr=" + houseArr +
                ", houseOneArr=" + houseOneArr +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.houseArr);
        dest.writeTypedList(this.houseOneArr);
    }

    public OnLineHouseResultBean() {
    }

    protected OnLineHouseResultBean(Parcel in) {
        this.houseArr = in.createTypedArrayList(HouseArrBean.CREATOR);
        this.houseOneArr = in.createTypedArrayList(HouseOneArrBean.CREATOR);
    }

    public static final Parcelable.Creator<OnLineHouseResultBean> CREATOR = new Parcelable.Creator<OnLineHouseResultBean>() {
        @Override
        public OnLineHouseResultBean createFromParcel(Parcel source) {
            return new OnLineHouseResultBean(source);
        }

        @Override
        public OnLineHouseResultBean[] newArray(int size) {
            return new OnLineHouseResultBean[size];
        }
    };
}
