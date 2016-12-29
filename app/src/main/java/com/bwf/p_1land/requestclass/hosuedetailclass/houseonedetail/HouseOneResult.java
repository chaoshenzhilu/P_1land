package com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */
public class HouseOneResult implements Serializable{
    public String houseOneId;

    public String roomCode;

    public String resblockOneId;

    public String resblockOneName;

    public String totalprBegin;

    public String totalprEnd;

    public String status;

    public String circleTypeCode;

    public String circleTypeName;

    public String unitprBegin;

    public String unitprEnd;

    public String bedroomAmount;

    public String parlorAmount;

    public String toiletAmount;

    public String titlepicImg;

    public String innenbereichSize;

    public String roomDescripe;

    public String buildSize;

    public String roomType;

    public String resblockType;

    public String districtName;

    public String districtId;

    public String shareURL;

    public List<ImgUrlArr> imgUrlArr ;

    public List<ApartmentImgVos> apartmentImgVos ;

    public String prodelId;

    public String coordinate;

    public String apartmentBegin;

    public String apartmentEnd;

    public String areaBegin;

    public String areaEnd;

    public String covers;

    public String gfa;

    public String resblockGrade;

    public String proId;

    public String imgUrl;

    public String resblockAddr;

    public String formType;

    public String decorationName;

    public String metersPrice;

    public String launchTime;

    public String volumeRate;

    public String greeningRate;

    public String storey;

    public String propertyCosts;

    public String heating;

    public String heating1;

    public String floorSpace;

    public String buildingType;

    public String parkingNum;

    public String facadeMaterial;

    public String developers;

    public String immobilien;

    public String lage;

    public String proTrack;

    public String isAttention;

    @Override
    public String toString() {
        return "HouseOneResult{" +
                "houseOneId='" + houseOneId + '\'' +
                ", roomCode='" + roomCode + '\'' +
                ", resblockOneId='" + resblockOneId + '\'' +
                ", resblockOneName='" + resblockOneName + '\'' +
                ", totalprBegin='" + totalprBegin + '\'' +
                ", totalprEnd='" + totalprEnd + '\'' +
                ", status='" + status + '\'' +
                ", circleTypeCode='" + circleTypeCode + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", unitprBegin='" + unitprBegin + '\'' +
                ", unitprEnd='" + unitprEnd + '\'' +
                ", bedroomAmount='" + bedroomAmount + '\'' +
                ", parlorAmount='" + parlorAmount + '\'' +
                ", toiletAmount='" + toiletAmount + '\'' +
                ", titlepicImg='" + titlepicImg + '\'' +
                ", innenbereichSize='" + innenbereichSize + '\'' +
                ", roomDescripe='" + roomDescripe + '\'' +
                ", buildSize='" + buildSize + '\'' +
                ", roomType='" + roomType + '\'' +
                ", resblockType='" + resblockType + '\'' +
                ", districtName='" + districtName + '\'' +
                ", districtId='" + districtId + '\'' +
                ", shareURL='" + shareURL + '\'' +
                ", imgUrlArr=" + imgUrlArr +
                ", apartmentImgVos=" + apartmentImgVos +
                ", prodelId='" + prodelId + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", apartmentBegin='" + apartmentBegin + '\'' +
                ", apartmentEnd='" + apartmentEnd + '\'' +
                ", areaBegin='" + areaBegin + '\'' +
                ", areaEnd='" + areaEnd + '\'' +
                ", covers='" + covers + '\'' +
                ", gfa='" + gfa + '\'' +
                ", resblockGrade='" + resblockGrade + '\'' +
                ", proId='" + proId + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", resblockAddr='" + resblockAddr + '\'' +
                ", formType='" + formType + '\'' +
                ", decorationName='" + decorationName + '\'' +
                ", metersPrice='" + metersPrice + '\'' +
                ", launchTime='" + launchTime + '\'' +
                ", volumeRate='" + volumeRate + '\'' +
                ", greeningRate='" + greeningRate + '\'' +
                ", storey='" + storey + '\'' +
                ", propertyCosts='" + propertyCosts + '\'' +
                ", heating='" + heating + '\'' +
                ", heating1='" + heating1 + '\'' +
                ", floorSpace='" + floorSpace + '\'' +
                ", buildingType='" + buildingType + '\'' +
                ", parkingNum='" + parkingNum + '\'' +
                ", facadeMaterial='" + facadeMaterial + '\'' +
                ", developers='" + developers + '\'' +
                ", immobilien='" + immobilien + '\'' +
                ", lage='" + lage + '\'' +
                ", proTrack='" + proTrack + '\'' +
                ", isAttention='" + isAttention + '\'' +
                '}';
    }
}
