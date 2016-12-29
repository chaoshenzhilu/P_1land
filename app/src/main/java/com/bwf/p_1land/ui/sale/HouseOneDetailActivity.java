package com.bwf.p_1land.ui.sale;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bwf.p_1land.R;
import com.bwf.p_1land.framework.base.BaseActivity;
import com.bwf.p_1land.framework.http.HttpRequesAsyncTask;
import com.bwf.p_1land.framework.http.Httphelper;
import com.bwf.p_1land.framework.image.ImageLoader;
import com.bwf.p_1land.framework.util.DisplayUtil;
import com.bwf.p_1land.framework.util.LogUtils;
import com.bwf.p_1land.framework.util.StringUtils;
import com.bwf.p_1land.framework.util.ToastUtil;
import com.bwf.p_1land.requestclass.guwenclass.guwen1.HouseOneGuwenData;
import com.bwf.p_1land.requestclass.guwenclass.guwen1.ShowArrBean;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneData;
import com.bwf.p_1land.requestclass.hosuedetailclass.houseonedetail.HouseOneResult;
import com.bwf.p_1land.ui.sale.adapter.ViewPagerAdapter;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne01;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne02;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne03;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne04;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne05;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne06;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne07;
import com.bwf.p_1land.ui.sale.fragment.FragmentHouseOne08;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bwf.p_1land.R.id.onehand_title_iv2;

/**
 * Created by Administrator on 2016/12/11.
 */

public class HouseOneDetailActivity extends BaseActivity {
    @Bind(R.id.onehand_title_tv1)
    TextView onehandTitleTv1;
    @Bind(R.id.onehand_title_tv2)
    TextView onehandTitleTv2;
    @Bind(R.id.onehand_title_iv1)
    ImageView onehandTitleIv1;
    @Bind(onehand_title_iv2)
    ImageView onehandTitleIv2;
    @Bind(R.id.onehand_floor_iv1)
    ImageView onehandFloorIv1;
    @Bind(R.id.onehand_floor_tv1)
    TextView onehandFloorTv1;
    @Bind(R.id.onehand_floor_tv2)
    TextView onehandFloorTv2;
    @Bind(R.id.onehand_floor_iv2)
    ImageView onehandFloorIv2;
    @Bind(R.id.onehand_floor_iv3)
    ImageView onehandFloorIv3;
    @Bind(R.id.onehand_title_rl)
    RelativeLayout onehandTitleRl;
    private String resblockOneId, houseOneId;
    private FragmentHouseOne01 fragmentHouseOne01;
    private FragmentHouseOne02 fragmentHouseOne02;
    private FragmentHouseOne03 fragmentHouseOne03;
    private FragmentHouseOne04 fragmentHouseOne04;
    private FragmentHouseOne05 fragmentHouseOne05;
    private FragmentHouseOne07 fragmentHouseOne07;
    private FragmentHouseOne06 fragmentHouseOne06;
    private FragmentHouseOne08 fragmentHouseOne08;
    private ImageLoader loader;
    private List<ShowArrBean> list;
    private HouseOneData houseOneData;
    private PopupWindow popupWindow;
    private ScrollView scrollView;
    private static final String APP_ID="wx93584718617ec36f";
    private IWXAPI iwxapi;

    @Override
    public int getContentViewId() {
        return R.layout.activity_onehand_detail;
    }

    @Override
    public void beforInitView() {
        resblockOneId = getIntent().getStringExtra("resblockOneId");
        houseOneId = getIntent().getStringExtra("houseOneId");
    }

    @Override
    public void initView() {
        loader = new ImageLoader();
        fragmentHouseOne01 = (FragmentHouseOne01) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment1);
        fragmentHouseOne02 = (FragmentHouseOne02) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment2);
        fragmentHouseOne03 = (FragmentHouseOne03) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment3);
        fragmentHouseOne04 = (FragmentHouseOne04) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment4);
        fragmentHouseOne05 = (FragmentHouseOne05) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment5);
        fragmentHouseOne06 = (FragmentHouseOne06) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment6);
        fragmentHouseOne07 = (FragmentHouseOne07) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment7);
        fragmentHouseOne08 = (FragmentHouseOne08) getSupportFragmentManager().findFragmentById(R.id.houseone_fragment8);
        scrollView=findViewByIdNoCast(R.id.houseone_scrollview);
    }

    @Override
    public void initViewData() {
        ButterKnife.bind(this);
        iwxapi= WXAPIFactory.createWXAPI(this,APP_ID,true);
        iwxapi.registerApp(APP_ID);
    }

    @Override
    public void afterInitView() {
        showDialog();
        getHouseOneDetailData();
    }

    private void getHouseOneDetailData() {
        Httphelper.getHouseOneGuwenData(this, houseOneId, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                HouseOneGuwenData houseOneGuwenData = new Gson().fromJson(result, HouseOneGuwenData.class);
                if (houseOneGuwenData == null || houseOneGuwenData.result.showArr.size() <= 1) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.hide(fragmentHouseOne02);
                    transaction.commit();

                }
                list = houseOneGuwenData.result.showArr;
                loader.displayImg(list.get(0).photo, onehandFloorIv1);
                onehandFloorTv1.setText(list.get(0).createName);
                onehandFloorTv2.setText(list.get(0).phone);
                if (list.size() > 1) {
                    list.remove(0);
                    String count = houseOneGuwenData.result.totalAmount;
                    fragmentHouseOne02.setFragment02Data(count, list, loader);
                }
            }

            @Override
            public void onFail(String errMessage) {

            }
        });
        Httphelper.getHouseOneData(this, houseOneId, new HttpRequesAsyncTask.NetCallBack() {
            @Override
            public void onSuccess(String result) {
                dismissDialog();
                LogUtils.e(result);
                houseOneData = new Gson().fromJson(result, HouseOneData.class);
                setTitle(houseOneData);
                fragmentHouseOne01.setFragment01Data(houseOneData, loader);
                fragmentHouseOne03.setFragment03Data(houseOneData);
                fragmentHouseOne04.setFragment04Data(houseOneData.result.imgUrlArr, loader, true,true);
                fragmentHouseOne04.getAdapter().setManagerActivity(managerActivity);//getviewpager适配器接口赋值
                fragmentHouseOne06.setFragment06Data(houseOneData);
                fragmentHouseOne07.setFragment07Data(houseOneData, loader);
                fragmentHouseOne08.setFragment08Data();
                scrollView.smoothScrollTo(0,0);//scrollview滑到顶部
                fragmentHouseOne05.setFragment05Data(houseOneData, loader, callback);
            }

            @Override
            public void onFail(String errMessage) {

            }
        });

    }

    boolean isshoucang = true;

    @OnClick({R.id.onehand_title_iv1, R.id.onehand_title_iv2, R.id.onehand_floor_iv1, R.id.onehand_floor_iv2, R.id.onehand_floor_iv3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.onehand_title_iv1:
                final PopupWindow popupWindow=new PopupWindow(this);
                View view1=View.inflate(HouseOneDetailActivity.this,R.layout.sharepopupwindow,null);
                popupWindow.setContentView(view1);
                ImageView imageWeiXin= (ImageView) view1.findViewById(R.id.share_weixin);
                popupWindow.setWidth(DisplayUtil.getDensity_Width(this));
                popupWindow.setHeight(DisplayUtil.getDensity_Height(this)-findViewByIdNoCast(R.id.detail_title).getHeight());
                imageWeiXin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WXTextObject textObject=new WXTextObject();
                        textObject.text=setSmsContent(houseOneData.result);
                        WXMediaMessage mediaMessage=new WXMediaMessage();
                        mediaMessage.mediaObject=textObject;
                        mediaMessage.description=setSmsContent(houseOneData.result);
                        SendMessageToWX.Req req=new SendMessageToWX.Req();
                        req.transaction=String.valueOf(System.currentTimeMillis());
                        req.message=mediaMessage;
                        req.scene=SendMessageToWX.Req.WXSceneSession;
                        iwxapi.sendReq(req);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAsDropDown(findViewByIdNoCast(R.id.detail_title));
                break;
            case R.id.onehand_title_iv2:
                if (isshoucang) {
                    onehandTitleIv2.setImageResource(R.mipmap.shoucang_sel);
                    isshoucang = false;
                    ToastUtil.showToast("成功关注");
                } else {
                    onehandTitleIv2.setImageResource(R.mipmap.shoucang);
                    isshoucang = true;
                    ToastUtil.showToast("取消关注");
                }
                break;
            case R.id.onehand_floor_iv1:
                break;
            case R.id.onehand_floor_iv2:
                if ((onehandFloorTv2.getText() != null) && (!onehandFloorTv2.getText().equals(""))) {
                    Uri uri = Uri.parse("smsto:" + onehandFloorTv2.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    if (houseOneData != null) {
                        intent.putExtra("sms_body", setSmsContent(houseOneData.result));
                    } else {
                        intent.putExtra("sms_body", "请问需要咨询什么内容");
                    }
                    startActivity(intent);
                } else {
                    ToastUtil.showToast("没有获得联系人");
                }
                break;
            case R.id.onehand_floor_iv3:
                if ((onehandFloorTv2.getText() != null) && (!onehandFloorTv2.getText().equals(""))) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + onehandFloorTv2.getText().toString()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    ToastUtil.showToast("没有获得联系人");
                }
                break;
        }
    }

    private FragmentHouseOne05.FragmentCallback callback = new FragmentHouseOne05.FragmentCallback() {
        @Override
        public void onShow(String url) {
            popupWindow = new PopupWindow();
            View view = LayoutInflater.from(HouseOneDetailActivity.this).inflate(R.layout.fragmentpupopwindow, null);
            popupWindow.setContentView(view);
            popupWindow.setHeight(DisplayUtil.getDensity_Height(HouseOneDetailActivity.this) - onehandTitleRl.getHeight());
            popupWindow.setWidth(DisplayUtil.getDensity_Width(HouseOneDetailActivity.this));
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.PopupuWindowAnimation);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            ImageView imageView = (ImageView) view.findViewById(R.id.fragmen05_pop);
            loader.displayImg(url, imageView);
            popupWindow.showAsDropDown(onehandTitleRl);
            popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(popupWindow!=null&&popupWindow.isShowing()){
                        popupWindow.dismiss();
                        popupWindow=null;
                    }
                    return true;
                }
            });
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(popupWindow!=null&&popupWindow.isShowing()){
//                        popupWindow.dismiss();
//                        popupWindow=null;
//                    }
//                }
//            });
        }
    };

    private void setTitle(HouseOneData houseOneData) {
        onehandTitleTv1.setText(houseOneData.result.resblockOneName);
        StringBuffer sb = new StringBuffer();
        sb.append(StringUtils.getint(houseOneData.result.totalprBegin))
                .append("-").append(StringUtils.getint(houseOneData.result.totalprEnd))
                .append("万 ").append(StringUtils.getint(houseOneData.result.buildSize))
                .append("㎡ ").append(StringUtils.getint(houseOneData.result.bedroomAmount)).append("室")
                .append(StringUtils.getint(houseOneData.result.parlorAmount)).append("厅")
                .append(StringUtils.getint(houseOneData.result.toiletAmount)).append("卫");
        onehandTitleTv2.setText(sb.toString());
    }

    private String setSmsContent(HouseOneResult model) {
        StringBuffer smsContent = new StringBuffer();
        // 我想咨询房源：
        // 房源名称：
        // 售价：
        // 面积：
        // 房型：
        // 请尽快与我联系！【丽兹豪宅网】
        smsContent.append("我想咨询房源:");
        smsContent.append("\n房源名称:").append(model.resblockOneName);
        smsContent.append("\n售价:")
                .append(StringUtils.doubleFormat(model.totalprBegin)).append("-")
                .append(StringUtils.doubleFormat(model.totalprEnd)).append("万");
        smsContent.append("\n面积:").append(StringUtils.doubleFormat(model.buildSize))
                .append("㎡");
        smsContent.append("\n房型:").append(model.resblockType);
        smsContent.append("\n请尽快与我联系！【丽兹豪宅网】");
        return smsContent.toString();
    }
    private ViewPagerAdapter.ManagerActivity managerActivity=new ViewPagerAdapter.ManagerActivity() {
        @Override
        public void manager() {
            Intent intent=new Intent(HouseOneDetailActivity.this, ThumbnailActivity.class);
            intent.putExtra("HouseOneResult",houseOneData.result);
            startActivity(intent);
        }
    };
}
