package com.juntai.project.sell.mall;


import com.example.chat.bean.UploadFileBean;
import com.juntai.disabled.basecomponent.base.BaseResult;
import com.juntai.disabled.basecomponent.bean.OpenLiveBean;
import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageListBean;
import com.juntai.disabled.basecomponent.bean.weather.CityBean;
import com.juntai.disabled.basecomponent.bean.weather.ResponseForcastWeather;
import com.juntai.disabled.basecomponent.bean.weather.ResponseRealTimeWeather;
import com.juntai.project.sell.mall.beans.AroundShopBean;
import com.juntai.project.sell.mall.beans.CartListBean;
import com.juntai.project.sell.mall.beans.CitysBean;
import com.juntai.project.sell.mall.beans.CollectDataBean;
import com.juntai.project.sell.mall.beans.CommodityDesListBean;
import com.juntai.project.sell.mall.beans.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.CommodityEvaluationBean;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.beans.NewsListBean;
import com.juntai.project.sell.mall.beans.PlayUrlBean;
import com.juntai.project.sell.mall.beans.ShopListDataBean;
import com.juntai.project.sell.mall.beans.UserBeanMall;
import com.juntai.project.sell.mall.beans.order.ConfirmOrderBean;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;
import com.juntai.project.sell.mall.beans.order.OrderDetailDataBean;
import com.juntai.project.sell.mall.beans.order.OrderListBean;
import com.juntai.project.sell.mall.beans.order.OrderPayWxBean;
import com.juntai.project.sell.mall.beans.order.OrderPayZfbBean;
import com.juntai.project.sell.mall.beans.order.OrderStatusAmountBean;
import com.juntai.project.sell.mall.beans.order.RefundReasonBean;
import com.juntai.project.sell.mall.beans.shop.ShopCommodityListBean;
import com.juntai.project.sell.mall.beans.shop.ShopDetailBean;

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




    /*====================================================    商品信息   ==============================================================*/


    @POST(AppHttpPathMall.COMMODIFY_LABELS)
    Observable<IdNameBean> getCommodityLaBels();


    @POST(AppHttpPathMall.COMMODIFY_RECOMMEND)
    Observable<CommodityDesListBean> getCommodityRecommendList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.COMMODIFY_DETAIL)
    Observable<CommodityDetailBean> getCommodityDetail(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.COMMODIFY_EVALUATION)
    Observable<CommodityEvaluationBean> getCommodityEvaluation(@Body RequestBody requestBody);



    /*====================================================    店铺详情   ==============================================================*/


    @POST(AppHttpPathMall.SHOP_DETAIL)
    Observable<ShopDetailBean> getShopDetail(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.SHOP_COMMODITY_LIST)
    Observable<ShopCommodityListBean> getShopCommodityList(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.SHOP_COLLECT)
    Observable<BaseResult> collectShop(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.COMMODITY_COLLECT)
    Observable<BaseResult> collectCommodity(@Body RequestBody requestBody);


    @POST(AppHttpPathMall.SHOP_COLLECT_LIST)
    Observable<CollectDataBean> getShopCollectList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.COMMODITY_COLLECT_LIST)
    Observable<CollectDataBean> getCommodityCollectList(@Body RequestBody requestBody);



    /*====================================================    购物车   ==============================================================*/


    @POST(AppHttpPathMall.EDIT_CART)
    Observable<BaseResult> editCart(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.CART_LIST)
    Observable<CartListBean> getCartList(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.DELETE_CART_COMMODITY)
    Observable<BaseResult> deleteCartCommodity(@Query("account") String account, @Query("token") String token, @Query("typeEnd") String typeEnd, @Query("id") List<Integer> ids);


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

    @POST(AppHttpPathMall.SEARCH_COMMODITY)
    Observable<CommodityDesListBean> startSearchCommodity(@Body RequestBody requestBody);

    @POST(AppHttpPathMall.SEARCH_SHOP)
    Observable<ShopListDataBean> startSearchShop(@Body RequestBody requestBody);

}