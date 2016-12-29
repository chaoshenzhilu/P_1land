package com.bwf.p_1land.requestclass.guwenclass.guwen1;

import com.bwf.p_1land.framework.base.Basebean;

import java.util.List;

public class HouseOneGuWenResult extends Basebean{
    /**
     * totalAmount : 168
     * showArr : [{"resourceId":"f1121","showStartTime":1452407700000,"createdTime":1452407700000,"createdBy":"125286","createName":"王楠","phone":"15600579422","photo":"http://119.254.70.187/SalesMgr-Web/pics/empImages/3000/30004275/3000427520150825.jpg","inductionDate":"2年11个月","totalShowing":10},{"resourceId":"f1121","showStartTime":1450609200000,"createdTime":1450609200000,"createdBy":"124801","createName":"关庆羽","phone":"13811181499","photo":"http://119.254.70.187/SalesMgr-Web/pics/empImages/3000/30003417/3000341720150825.jpg","inductionDate":"2年6个月","totalShowing":10},{"resourceId":"f1121","showStartTime":1452752700000,"createdTime":1452752700000,"createdBy":"128708","createName":"赵洋","phone":"13810088684","photo":"http://119.254.70.187/SalesMgr-Web/pics/empImages/3000/30005314/3000531420151027.jpg","inductionDate":"1年7个月","totalShowing":9},{"resourceId":"f1121","showStartTime":1451543400000,"createdTime":1451543400000,"createdBy":"124590","createName":"武智利","phone":"13811465523","photo":"http://119.254.70.187/SalesMgr-Web/pics/empImages/3000/30002626/3000262620150825.jpg","inductionDate":"1年11个月","totalShowing":9}]
     */
    public String totalAmount;
    public List<ShowArrBean> showArr;

    @Override
    public String toString() {
        return "HouseOneGuWenResult{" +
                "totalAmount=" + totalAmount +
                ", showArr=" + showArr +
                '}';
    }
}

