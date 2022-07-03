package com.juntai.project.sell.mall;


import com.juntai.disabled.basecomponent.bean.UploadFileBean;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.OpenLiveBean;
import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageListBean;
import com.juntai.disabled.basecomponent.bean.weather.CityBean;
import com.juntai.disabled.basecomponent.bean.weather.ResponseForcastWeather;
import com.juntai.disabled.basecomponent.bean.weather.ResponseRealTimeWeather;
import com.juntai.project.sell.mall.beans.AroundShopBean;
import com.juntai.project.sell.mall.beans.BillBaseInfoBean;
import com.juntai.project.sell.mall.beans.BillListBean;
import com.juntai.project.sell.mall.beans.CitysBean;
import com.juntai.project.sell.mall.beans.CommodityFormatDataBean;
import com.juntai.project.sell.mall.beans.CommodityFormatListBean;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.beans.MonthStatisticsBean;
import com.juntai.project.sell.mall.beans.NewsListBean;
import com.juntai.project.sell.mall.beans.PlayUrlBean;
import com.juntai.project.sell.mall.beans.ShopCommodityListBean;
import com.juntai.project.sell.mall.beans.ShopListDataBean;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.WithDrawListBean;
import com.juntai.project.sell.mall.beans.order.ConfirmOrderBean;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;
import com.juntai.project.sell.mall.beans.order.OrderDetailDataBean;
import com.juntai.project.sell.mall.beans.order.OrderListBean;
import com.juntai.project.sell.mall.beans.order.OrderPayWxBean;
import com.juntai.project.sell.mall.beans.order.OrderPayZfbBean;
import com.juntai.project.sell.mall.beans.order.OrderStatusAmountBean;
import com.juntai.project.sell.mall.beans.order.RefundReasonBean;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailDataBean;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityCategoryListBean;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityManagerListBean;
import com.juntai.project.sell.mall.beans.sell.ShopDetailBean;
import com.juntai.project.sell.mall.beans.sell.ShopHomeInfoBean;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeBean;
import com.juntai.project.sell.mall.beans.sell.SystemNoticeListBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * responseBody里的数据只能调用(取出)一次，第二次为空。可赋值给新的变量使用
 */
public interface AppServerMall {

    /**
     * 会话id   保活的接口
     *
     * @param sessionid
     * @return
     */
    @GET(AppHttpPathMall.BASE_CAMERA_URL + "/vss/video_keepalive/{sessionid}")
    Observable<OpenLiveBean> keepAlive(@Path("sessionid") String sessionid);

    /**
     * 打开视频流
     *
     * @return
     */
    @POST(AppHttpPathMall.STREAM_OPE_ADDR)
    Observable<PlayUrlBean> openStream(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.GET_SHOPES_AROUND)
    Observable<AroundShopBean> getAroundShopes(@Body RequestBody requestBody);


    /*====================================================    地址管理   ==============================================================*/
    @POST(AppHttpPathMall.ADDR_LIST)
    Observable<AddressListBean> getAddrList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.SET_DEFAULT_ADDR)
    Observable<BaseResult> setDefaultAdddr(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ADD_OR_EDIT_ADDR)
    Observable<BaseResult> addOrEditAddrList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.DELETE_ADDR)
    Observable<BaseResult> deleteAddr(@Query("account") String account, @Query("token") String token, @Query("typeEnd") String typeEnd, @Query("id") List<Integer> ids);




    /*====================================================    天气   ==============================================================*/


    //实时天气
    @POST(AppHttpPathMall.REALTIME_WEATHER)
    Observable<ResponseRealTimeWeather> getWeatherRealtime(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //天气预报
    @POST(AppHttpPathMall.FORCAST_WEATHER)
    Observable<ResponseForcastWeather> getForcast(@Query("longitude") String longitude, @Query("latitude") String latitude);

    @POST(AppHttpPathMall.PROVINCE)
    Observable<CityBean> getProvince();

    @POST(AppHttpPathMall.CITY)
    Observable<CityBean> getCity(@Query("cityNum") int cityNum);

    @POST(AppHttpPathMall.AREA)
    Observable<CityBean> getArea(@Query("cityNum") int cityNum);

    @POST(AppHttpPathMall.STREET)
    Observable<CityBean> getStreet(@Query("cityName") int townNum);








    /*====================================================    店铺详情   ==============================================================*/


    @POST(AppHttpPathMall.SHOP_DETAIL)
    Observable<ShopDetailBean> getShopDetail(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.SHOP_APPLY)
    Observable<BaseResult> shopApply(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.EDIT_SHOP_APPLY)
    Observable<BaseResult> eidtShopApply(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.SHOP_COMMODITY_LIST)
    Observable<ShopCommodityListBean> getShopCommodityList(@Body RequestBody requestBody);

    /**
     * 获取所有的类目
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPathMall.ALL_SHOP_CATEGORY)
    Observable<IdNameBean> getAllCategory(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.SHOP_COLLECT)
    Observable<BaseResult> collectShop(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.COMMODITY_COLLECT)
    Observable<BaseResult> collectCommodity(@Body RequestBody requestBody);


    @GET(AppHttpPathMall.ALL_CITYS)
    Observable<CitysBean> getAllCitys(@Query("keywords") String keywords, @Query("subdistrict") String subdistrict, @Query("key") String key);


    /**
     * 商城登录
     *
     * @return
     */
    @POST(AppHttpPathMall.LOGIN)
    Observable<UserBeanMall> login(@Body RequestBody requestBody);


    /**
     * 登录
     *
     * @return
     */
    @POST(AppHttpPathMall.REGIST)
    Observable<UserBeanMall> regist(@Body RequestBody requestBody);

    /**
     * 上传文件
     *
     * @return
     */
    @POST(AppHttpPathMall.UPLOAD_FILES)
    Observable<UploadFileBean> uploadFiles(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.MODIFY_PWD)
    Observable<BaseResult> modifyPwd(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.MODIFY_PHONE)
    Observable<BaseResult> modifyPhone(@Body RequestBody requestBody);

    /**
     * account  手机号
     *
     * @return
     */
    @GET(AppHttpPathMall.GET_SMS_CODE + "/{phoneNumber}")
    Observable<BaseResult> getSMSCode(@Path("phoneNumber") String path);






    /*====================================================    个人中心   ==============================================================*/


    /**
     * account  手机号
     *
     * @return
     */
    @POST(AppHttpPathMall.GET_USER_INFO)
    Observable<UserBeanMall> getUserInfo(@Body RequestBody requestBody);

    /**
     * 修改账户
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPathMall.MODIFY_USER_ACCOUNT)
    Observable<BaseResult> modifyAccount(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.COMMIT_SUGGESTION)
    Observable<BaseResult> commitSuggestion(@Body RequestBody requestBody);

    /**
     * 用户认证
     */
    @POST(AppHttpPathMall.USER_AUTH)
    Observable<BaseResult> userAuth(@Body RequestBody jsonBody);

    /**
     * 修改用户信息
     *
     * @param requestBody
     * @return
     */
    @POST(AppHttpPathMall.MODIFY_USER_INFO)
    Observable<BaseResult> modifyUserInfo(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.LOGOUT)
    Observable<BaseResult> logout(@Body RequestBody requestBody);



    /*====================================================    订单部分   ==============================================================*/

    @POST(AppHttpPathMall.CREAT_ORDER_CART)
    Observable<CreatOrderBean> creatOrderCart(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.CREAT_ORDER_BUY)
    Observable<CreatOrderBean> creatOrderBuy(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.COMMIT_ORDER)
    Observable<ConfirmOrderBean> commitOrder(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.CANCEL_ORDER)
    Observable<BaseResult> cancelOrder(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.NOTICE_SEND)
    Observable<BaseResult> noticeSend(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.DELETE_CANCEL_ORDER)
    Observable<BaseResult> deleteCancelOrder(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.REQUEST_REFUND)
    Observable<BaseResult> requestRefund(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.START_EVALUATE)
    Observable<BaseResult> startEvaluate(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.CONFIRM_RECEIVED)
    Observable<BaseResult> confirmReceived(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ORDER_STATUS)
    Observable<BaseResult> getOrderStatus(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.ORDER_LIST)
    Observable<OrderListBean> getOrderList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ORDER_DETAIL)
    Observable<OrderDetailDataBean> getOrderDetail(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ORDER_STATUS_AMOUNT)
    Observable<OrderStatusAmountBean> getOrderStatusAmount(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.GET_REFUND_REASON)
    Observable<RefundReasonBean> getRefundReasons(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.ORDER_PAY_PUB_WEIXIN)
    Observable<OrderPayWxBean> payByWeixin(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ORDER_PAY_ZHIFUBAO)
    Observable<OrderPayZfbBean> payByZhifubao(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ORDER_PAY_PUB_ACCOUNT)
    Observable<BaseResult> payByPubAccount(@Body RequestBody requestBody);






    /*====================================================    消息   ==============================================================*/

    @POST(AppHttpPathMall.SEND_MSG)
    Observable<BaseResult> sendMessage(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.MESSAGE_READ)
    Observable<BaseResult> messageRead(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.UNREAD_CONTACT_MSG)
    Observable<MessageListBean> getContactUnreadMsg(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.NEWS_LIST)
    Observable<NewsListBean> getNewsList(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.SEARCH_SHOP)
    Observable<ShopListDataBean> startSearchShop(@Body RequestBody requestBody);



    /*====================================================    卖家端   ==============================================================*/


    @POST(AppHttpPathMall.SHOP_HOME_INFO)
    Observable<ShopHomeInfoBean> getShopHomeInfo(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.GET_SYSTEM_NOTICE)
    Observable<SystemNoticeListBean> getSystemNotice(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.GET_SYSTEM_NOTICE_DETAIL)
    Observable<SystemNoticeBean> getSystemNoticeDetail(@Body RequestBody requestBody);





    /*====================================================    商品类目   ==============================================================*/

    @POST(AppHttpPathMall.ALL_COMMODITY_CATEGORY)
    Observable<ShopCommodityCategoryListBean> getCommodityCategorys(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ADD_COMMODITY_CATEGORY)
    Observable<BaseResult> addCommodityCategorys(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.MODIFY_COMMODITY_CATEGORY)
    Observable<BaseResult> modifyCommodityCategorys(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.DELETE_COMMODITY_CATEGORY)
    Observable<BaseResult> deleteCommodityCategorys(@Body RequestBody requestBody);



    /*====================================================    商品管理   ==============================================================*/

    @POST(AppHttpPathMall.GET_ALL_COMMODITY)
    Observable<ShopCommodityManagerListBean> getAllCommodity(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.GET_COMMODITY_DETAIL)
    Observable<CommodityDetailDataBean> getCommodityDetail(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ADD_COMMODITY_BASE_INFO)
    Observable<BaseResult> addCommodityBaseInfo(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.UPDATE_COMMODITY_BASE_INFO)
    Observable<BaseResult> updateCommodityBaseInfo(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.DELETE_COMMODITY)
    Observable<BaseResult> deleteCommodity(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.EDIT_COMMODITY_FORMAT)
    Observable<BaseResult> editCommodityProperty(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.COMMODITY_ON_SALE)
    Observable<BaseResult> onSaleCommodity(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.SEND_GOODS)
    Observable<BaseResult> sendGoods(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.REFUND_REQUEST)
    Observable<BaseResult> handlerRefundRequest(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.CREATE_COMMODITY_FORMAT)
    Observable<CommodityFormatListBean> createCommodityFormatList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.GET_COMMODITY_FORMAT)
    Observable<CommodityFormatDataBean> getCommodityFormat(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.ADD_SHOP_BANNERS)
    Observable<CommodityFormatDataBean> addShopBannerPics(@Body RequestBody requestBody, @Query("bannerPhoto") List<String> pics);

    @POST(AppHttpPathMall.BILL_LIST)
    Observable<BillListBean> getBillList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.BILL_BASE_INFO)
    Observable<BillBaseInfoBean> getBillBaseInfo(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.BILL_WITHDRAW)
    Observable<WithDrawListBean> getBillWithDrawList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.MONTH_STATISTICS)
    Observable<MonthStatisticsBean> getMonthStatistics(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.WITHDRAW)
    Observable<BaseResult> withDraw(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.BIND_BANK_CARD)
    Observable<BaseResult> bindBankCard(@Body RequestBody requestBody);





}