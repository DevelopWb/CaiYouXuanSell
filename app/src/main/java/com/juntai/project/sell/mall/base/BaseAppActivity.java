package com.juntai.project.sell.mall.base;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chat.base.uploadFile.UploadUtil;
import com.example.chat.base.uploadFile.listener.OnUploadListener;
import com.example.live_moudle.bean.LiveListBean;
import com.example.live_moudle.util.UserInfoManagerLive;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.bean.UploadFileBean;
import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.MD5;
import com.juntai.disabled.basecomponent.utils.NotificationTool;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.disabled.bdmap.BaseRequestLocationActivity;
import com.juntai.disabled.bdmap.utils.NagivationUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.beans.sell.ShopDetailBean;
import com.juntai.project.sell.mall.entrance.LoginActivity;
import com.juntai.project.sell.mall.home.commodityManager.allCommodity.AllCommodityActivity;
import com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty.CommodityFormatPropertyActivity;
import com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity.CommodityDetailActivity;
import com.juntai.project.sell.mall.home.shop.ShopManagerActivity;
import com.juntai.project.sell.mall.mine.address.AddOrEditAddressActivity;
import com.juntai.project.sell.mall.mine.address.AddressListActivity;
import com.juntai.project.sell.mall.news.ChatActivity;
import com.juntai.project.sell.mall.order.allOrder.OrderManagerActivity;
import com.juntai.project.sell.mall.order.evaluate.EvaluateActivity;
import com.juntai.project.sell.mall.order.orderDetail.OrderDetailActivity;
import com.juntai.project.sell.mall.order.refund.RefundActivity;
import com.juntai.project.sell.mall.order.refund.RefundRequestActivity;
import com.juntai.project.sell.mall.order.send.SendActivity;
import com.juntai.project.sell.mall.share.ShareActivity;
import com.juntai.project.sell.mall.utils.ObjectBoxMallUtil;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @aouther tobato
 * @description ??????
 * @date 2020/4/27 8:48  app?????????
 */
public abstract class BaseAppActivity<P extends BasePresenter> extends BaseRequestLocationActivity<P> {
    public static String WX_APPID = "wx5fd6d26f7806a119";
    public UploadUtil mUploadUtil;

    private OnFileUploadStatus onFileUploadStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationTool.SHOW_NOTIFICATION = true;
        initUploadUtil();
    }

    public void setOnFileUploadStatus(OnFileUploadStatus onFileUploadStatus) {
        this.onFileUploadStatus = onFileUploadStatus;
    }
    /**
     * ????????????????????????
     *
     * @param content
     */
    public void copy(String content) {
// ????????????????????????
        ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        ToastUtils.toast(mContext,"?????????");
    }
    protected void initUploadUtil() {
        //?????????????????????
        mUploadUtil = new UploadUtil();
        mUploadUtil.setOnUploadListener(new OnUploadListener() {
            @Override
            public void onAllSuccess() {
                ToastUtils.toast(mContext, "onAllSuccess");
            }

            @Override
            public void onAllFailed() {

            }

            @Override
            public void onThreadProgressChange(UploadFileBean uploadFileBean, int position, int percent) {
                Log.d("onThreadProgressChange", "onThreadProgressChange" + uploadFileBean.getFilePath() + "---------" + percent);

                if (onFileUploadStatus != null) {
                    onFileUploadStatus.onUploadProgressChange(uploadFileBean, percent);
                }
            }

            @Override
            public void onThreadFinish(UploadFileBean uploadFileBean, int position) {
                if (onFileUploadStatus != null) {
                    onFileUploadStatus.onUploadFinish(uploadFileBean);
                }
            }

            @Override
            public void onThreadInterrupted(int position) {

            }
        });
    }


    public interface OnFileUploadStatus {
        void onUploadProgressChange(UploadFileBean uploadFileBean, int percent);

        void onUploadFinish(UploadFileBean uploadFileBean);

    }

    /**
     * ????????????
     */
    public void reLogin(String regPhone) {
        UserInfoManagerMall.clearUserData();//????????????
        HawkProperty.clearRedPoint(mContext.getApplicationContext());
        ActivityManagerTool.getInstance().finishApp();
        startActivity(new Intent(this, LoginActivity.class).putExtra(BASE_STRING, regPhone
        ));
    }

    /**
     * ???????????????
     *
     * @return
     */
    public boolean initThirdShareLogic(Intent intent, Context context, Class cls) {
        if (intent != null) {
            String shareTitle = intent.getStringExtra("title");
            String shareUrl = intent.getStringExtra("shareUrl");
            String sharePic = intent.getStringExtra("picPath");
            String shareContent = intent.getStringExtra("content");
            String shareFromApp = intent.getStringExtra("shareFromApp");
            if (!TextUtils.isEmpty(shareUrl) && !TextUtils.isEmpty(shareTitle)) {
                Intent toIntent = new Intent();
                toIntent.putExtra("title", shareTitle);
                toIntent.putExtra("shareUrl", shareUrl);
                toIntent.putExtra("picPath", sharePic);
                toIntent.putExtra("content", shareContent);
                toIntent.putExtra("shareFromApp", shareFromApp);
                toIntent.setClass(context, cls);
                startActivity(toIntent);
                return true;
            }
        }
        return false;
    }


    /**
     * ??????????????????
     *
     * @param messageBodyBean
     * @return
     */
    public String getSavedFileName(MessageBodyBean messageBodyBean) {
        String content = messageBodyBean.getContent();
        return getSavedFileName(content);
    }

    /**
     * ??????????????????
     *
     * @return
     */
    public String getSavedFileName(String content) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        if (content.contains("/")) {
            content = content.substring(content.lastIndexOf("/") + 1, content.length());
        }
        return content;
    }

    /**
     * ??????????????????  ??????
     *
     * @param messageBodyBean
     * @return
     */
    public String getSavedFileNameWithoutSuffix(MessageBodyBean messageBodyBean) {
        String content = messageBodyBean.getContent();
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        if (content.contains("/")) {
            content = content.substring(content.lastIndexOf("/") + 1, content.lastIndexOf("."));
        }
        return content;
    }
    public RequestBody getJsonRequestBody(String jsonStr){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(JSON,jsonStr);
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return true;
    }

    @Override
    protected String getUpdateHttpUrl() {
        return AppHttpPathMall.APP_UPDATE;
    }

    /**
     * ??????
     *
     * @param endLatlng ?????????
     * @param endName   ???????????????
     */
    public void navigationLogic(LatLng endLatlng, String endName) {
        AlertDialog.Builder build = new AlertDialog.Builder(mContext);
        final String item_list[] = {"????????????", "????????????", "????????????"};
        build.setItems(item_list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (item_list[which]) {
                    case "????????????":
                        NagivationUtils.getInstant().openTencent(mContext, endLatlng.latitude, endLatlng.longitude,
                                endName);
                        break;
                    case "????????????":
                        NagivationUtils.getInstant().openGaoDeMap(mContext, endLatlng.latitude, endLatlng.longitude,
                                endName);
                        break;
                    case "????????????":
                        NagivationUtils.getInstant().openBaiduMap(mContext, endLatlng.latitude, endLatlng.longitude,
                                endName);
                        break;
                    default:
                        break;
                }
            }
        });
        build.setTitle("?????????????????????");
        AlertDialog alertDialog = build.create();
        alertDialog.show();
    }


    /**
     * ???????????????????????????
     */
    public void broadcasetRefreshHead() {
        Intent intent = new Intent();
        intent.setAction("action.refreshHead");
        sendBroadcast(intent);
    }

    /**
     * ??????builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilder() {
        FormBody.Builder builder = new FormBody.Builder()
                .add("account", UserInfoManagerMall.getAccount())
                .add("token", UserInfoManagerMall.getUserToken())
                .add("typeEnd", UserInfoManagerMall.DEVICE_TYPE)
                .add("userId", String.valueOf(UserInfoManagerMall.getUserId()));
        if (UserInfoManagerMall.getShopId()>0) {
            builder.add("shopId", String.valueOf(UserInfoManagerMall.getShopId()));
        }
        return builder;
    }

    /**
     * ??????builder
     *
     * @return
     */
    public FormBody.Builder getBaseBuilderWithoutParama() {
        FormBody.Builder builder = new FormBody.Builder();
        return builder;
    }
//    /**
//     * ??????builder
//     *
//     * @return
//     */
//    public FormBody.Builder getBaseBuilderWithoutUserId() {
//        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("account", UserInfoManagerChat.getUserAccount());
//        builder.add("token", UserInfoManagerChat.getUserToken());
//        return builder;
//    }


    //    /**
    //     * ?????????????????????
    //     *
    //     * @return
    //     */
    //    public boolean isInnerAccount() {
    //        return UserInfoManagerChat.isTest();

    //    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }


    /**
     * ??????????????????
     */
    public void copyTelephoneNum(String text) {
        //???????????????
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        //??????ClipData??????
        //???????????????????????????????????????????????????
        //????????????????????????????????????????????????
        ClipData clip = ClipData.newPlainText("simple text", text);
        //??????clipdata??????.
        clipboard.setPrimaryClip(clip);
    }

    @Override
    public boolean requestLocation() {
        return false;
    }


    /**
     * ????????????
     *
     * @param pwd
     * @return
     */
    protected String encryptPwd(String account, String pwd) {
        return MD5.md5(String.format("%s#%s", account, pwd));
    }

    @Override
    protected void onPicsAndEmpressed(List<String> icons) {

    }


    @Override
    protected String getDownloadTitleRightName() {
        return null;
    }

    @Override
    protected String getDownLoadPath() {
        return null;
    }


    public String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void onBackPressed() {
        hideKeyboard(mBaseRootCol);
        super.onBackPressed();
    }



    /**
     * ?????????????????????
     *
     * @param shopId
     */
    public void startToShop(int shopId) {
//        startActivityForResult(new Intent(mContext, ShopFurnishActivity.class).putExtra(BASE_ID, shopId), BASE_REQUEST_RESULT);

    }

    /**
     * ?????????????????????
     * enterType 0 ???????????????????????????
     * 1 ??????????????????????????????
     * 2. ????????????????????????
     */
    public void startToOrderPayActivity(BaseResult orderListBean, int enterType) {
//        startActivity(new Intent(mContext, OrderPayActivity.class)
//                .putExtra(BASE_STRING, enterType)
//                .putExtra(BASE_PARCELABLE, orderListBean));

    }

    /**
     * ?????????????????????
     */
    public void startToConfirmOrder(CreatOrderBean.DataBean dataBean) {
//        startActivity(new Intent(mContext, ConfirmOrderActivity.class).putExtra(BASE_PARCELABLE, dataBean));

    }

    /**
     * ?????????????????????
     * type  1???????????????  0???????????????
     */
    public void startToAddressListActivity(int type) {
        startActivityForResult(new Intent(mContext, AddressListActivity.class).putExtra(BASE_ID, type), BASE_REQUEST_RESULT);


    }

    /**
     * ??????(????????????)
     */
    public void startToAddAddress(AddressListBean.DataBean dataBean) {
        startActivityForResult(new Intent(mContext, AddOrEditAddressActivity.class).putExtra(BASE_PARCELABLE, dataBean), BASE_REQUEST_RESULT);
    }

    /**
     * ????????????
     * enterType  0????????????????????????  1????????????????????????
     */
    public void startToAllOrderActivity(int enterType, int tabPosition) {
        startActivity(new Intent(mContext, OrderManagerActivity.class)
                .putExtra(BASE_ID2, tabPosition)
                .putExtra(BASE_ID, enterType));
    }

    /**
     * @param orderId
     * @param orderStatus
     */
    public void startToOrderDetailActivity(int orderId, int orderStatus) {
        startActivity(new Intent(mContext, OrderDetailActivity.class)
                .putExtra(BASE_ID, orderId)
                .putExtra(BASE_ID2, orderStatus));
    }

    /**
     * ?????? ??????????????????
     */
    public void startToOrderRefundRequestActivity(OrderDetailBean orderDetailBean) {
        startActivity(new Intent(mContext, RefundRequestActivity.class)
                .putExtra(BASE_PARCELABLE, orderDetailBean)
        );

    }

    @Override
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.EVALUATE:
                if (this instanceof OrderManagerActivity) {
                    OrderDetailBean.CommodityListBean commodityBean = (OrderDetailBean.CommodityListBean) eventBusObject.getEventObj();
                    startToEvaluateActivity(commodityBean);
                }
                break;
            case EventBusObject.HANDLER_MESSAGE:
                MessageBodyBean messageBody = (MessageBodyBean) eventBusObject.getEventObj();
                if (mContext instanceof ChatActivity) {
                    EventManager.getEventBus().post(new EventBusObject(EventBusObject.MESSAGE_BODY, messageBody));
                } else {
                    switch (messageBody.getMsgType()) {
                        case 15:
                            EventManager.getEventBus().post(new EventBusObject(EventBusObject.TO_HANDLER_ORDER, messageBody));

                            break;
                        default:
                            ObjectBoxMallUtil.addMessage(messageBody);
                            EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_NEWS_LIST, messageBody));
                            break;
                    }


                }
                break;
            case EventBusObject.LIVE_SHARE:
                LiveListBean.DataBean.ListBean bean = (LiveListBean.DataBean.ListBean) eventBusObject.getEventObj();
                bean.setHeadPortrait(UserInfoManagerLive.getHeadPic());
                bean.setShopName(UserInfoManagerLive.getShopName());
                ShareActivity.startShareActivity(mContext, 2,bean);

                break;
            default:
                break;
        }
    }
    /**
     * ?????? ??????
     */
    public void startToEvaluateActivity(OrderDetailBean.CommodityListBean commodityBean) {
        OrderDetailBean orderDetailBean = new OrderDetailBean();
        List<OrderDetailBean.CommodityListBean> listBeans = new ArrayList<>();
        listBeans.add(commodityBean);
        orderDetailBean.setShopName(commodityBean.getShopName());
        orderDetailBean.setCommodityList(listBeans);
        startActivity(new Intent(mContext, EvaluateActivity.class)
                .putExtra(BASE_PARCELABLE, orderDetailBean)
        );

    }

    /**
     * ?????? ??????
     * receivedStatus ??????????????????  1????????? 2 ??????
     */
    public void startToRefundActivity(OrderDetailBean orderDetailBean, int receivedStatus) {
        startActivity(new Intent(mContext, RefundActivity.class)
                .putExtra(BASE_ID, receivedStatus)
                .putExtra(BASE_PARCELABLE, orderDetailBean));
    }

    /**
     * ??????????????????
     */
    public void startToChatActivity(ContactBean contactBean) {
        startActivity(new Intent(mContext, ChatActivity.class)
                .putExtra(BASE_PARCELABLE, contactBean));
    }
    /**
     * ????????????????????????
     */
    public void startAllCommodityActivity() {
        startActivity(new Intent(mContext, AllCommodityActivity.class));
    }
    /**
     * ????????????????????????
     */
    public void startSendActivity(int orderId) {
        startActivity(new Intent(mContext, SendActivity.class).putExtra(BASE_ID,orderId));
    }
    /**
     * ??????????????????
     */
    public void startCommodityPropertyActivity(int commodityId) {
        startActivity(new Intent(mContext, CommodityFormatPropertyActivity.class).putExtra(BASE_ID,commodityId));
    }
    /**
     * ?????????????????????
     *
     * @param commodityId
     */
    public void startToCommodityDetail(int commodityId) {
        startActivityForResult(new Intent(mContext, CommodityDetailActivity.class)
                .putExtra(BASE_ID, commodityId), BASE_REQUEST_RESULT);
    }
    /**
     * ??????????????????
     */
    public void startToShopAuthActivity(ShopDetailBean.DataBean dataBean) {
        // : 2022/6/8 ???????????????????????????
        startActivity(new Intent(mContext, ShopManagerActivity.class)
        .putExtra(BASE_PARCELABLE,dataBean));
    }
    private void showListPopwindow(View v) {
        List<String> arrays = new ArrayList<>();
        arrays.add("????????????");
        arrays.add("????????????");
        arrays.add("????????????");
        View popView = LayoutInflater.from(mContext).inflate(R.layout.pop_recycler, null);
        PopupWindow popupWindow = new PopupWindow(popView, DisplayUtil.dp2px(mContext, 70), DisplayUtil.dp2px(mContext, 90),
                false);
        popupWindow.setOutsideTouchable(true);
        SingleTextAdapter singleTextAdapter = new SingleTextAdapter(R.layout.single_text_layout);

        RecyclerView mRecyclerview = (RecyclerView) popView.findViewById(R.id.pop_rv);
        mRecyclerview.setAdapter(singleTextAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(manager);
        singleTextAdapter.setNewData(arrays);
        singleTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        popupWindow.showAsDropDown(v);
    }

}
