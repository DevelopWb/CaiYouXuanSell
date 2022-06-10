package com.juntai.project.sell.mall.home;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/3/12 16:00
 */
public interface HomePageContract {

    String GET_WEATHER_REAL_TIME = "get_real_time";//实时数据
    String GET_FORCAST_WEATHER = "get_forcast";//预报

    String GET_PRIVINCE = "get_privince";//省份
    String GET_CITY = "get_city";//市
    String GET_TOWN = "get_town";//县
    String GET_STREET = "get_street";//街道
    


    String MENUE_MAP_TYPE = "地图";//地图类型
    String MENUE_WEATHER = "天气";//天气


    String  SHARE_WEIXIN = "微信";
    String  SHARE_WEIXIN_FRIENDS = "朋友圈";
    String  SHARE_QQ = "QQ";
    String  SHARE_QQ_SPACE = "空间";
    String  SHARE_SAVE_PIC = "保存图片";
    String  SHARE_COPY_LINK = "复制链接";




    String  SHOP = "店铺";
    String  CUSTOMER = "客服";
    String  COLLECT = "收藏";
    String COLLECT_COMMODITY_SHOP = "COLLECT_COMMODITY";
    String UN_COLLECT_COMMODITY_SHOP = "UN_COLLECT_COMMODITY";




        /*====================================================    订单   ==============================================================*/


    String  ORDER_CANCEL = "取消订单";
    String  ORDER_PAY = "立即付款";
    String  ORDER_REFUND = "申请退款";
    String  ORDER_SEND = "提醒发货";
    String  ORDER_RECEIVE = "确认收货";
    String  ORDER_PROGRESS = "查看进度";
    String  ORDER_DELETE = "删除订单";
    String  ORDER_REBUY = "再来一单";
    String  ORDER_REFUND_AGREE = "商家已同意";
    String  ORDER_REFUND_UNAGREE = "商家不同意";




    String  SHOP_FLOW_ORDER  = "今日订单量";
    String  SHOP_FLOW_BUSINESS  = "今日营业额";
    String  SHOP_FLOW_VISIT  = "今日访问量";

    String  SHOP_MANAGER_COMMODITY  = "商品管理";
    String  SHOP_MANAGER_ORDER  = "订单管理";
    String  SHOP_MANAGER_LIVE  = "直播";
    String  SHOP_MANAGER_ASSENT  = "收入资产";
    String  SHOP_MANAGER_FURNISH  = "店铺装修";
    String  SHOP_MANAGER_SHOP  = "店铺管理";
    String  SHOP_MANAGER_GUIDE  = "新手教程";



    String  COMMODITY_MANAGER_CATEGORY  = "商品类目管理";
    String  COMMODITY_MANAGER_TOTAL  = "商品管理";




        /*====================================================    描述信息   ==============================================================*/
        String  SHOP_PIC  = "店铺头像";
        String  SHOP_NAME  = "店铺名称";
        String  SHOP_INTRODUCTION  = "店铺简介";
        String  SHOP_ADDR  = "店铺定位及地址";
        String  SHOP_TEL  = "店铺联系方式";
        String  SHOP_CATEGORY  = "店铺主营类目";
        String  SHOP_LICENSE  = "营业执照";
        String  ID_CARD_FRONT  = "法人身份证正面";
        String  ID_CARD_BACK  = "法人身份证反面";
        String  SHOP_BANNER_PICS = "店铺实景相片";




    interface IHomePageView extends IView {

    }

    interface IHomePagePresent extends IPresenter<IHomePageView> {
    }

}
