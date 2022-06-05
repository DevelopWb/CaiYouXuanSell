package com.juntai.project.sell.mall.mine;


import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * Describe: 个人信息接口类
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public interface MyCenterContract {
    String SET_UPDATE_TAG = "setUpdateTag";
    String ADDR_MANAGER_TAG = "addrmanager";
    String SET_CLEAR_TAG ="setClearTag";
    //设置相关
    String SET_UPDATE_PSD_TAG = "setUpdatePsdTag";
    String SET_UPDATE_TEL_TAG = "setUpdateTelTag";
    String SET_WEIXIN_TAG ="setWeiXinTag";
    String SET_QQ_TAG = "setQQTag";
    String SET_ABOUT_TAG ="setAboutTag";
    String CENTER_SETTING_TAG ="centerSettingTag";
    String TOP_MENU_COLLECT_COMMODITY ="商品收藏";
    String TOP_MENU_COLLECT_SHOP ="店铺收藏";
    String TOP_MENU_ADDR_MANAGER ="收货地址";
    String ORDER_TO_PAY ="待付款";
    String ORDER_TO_SEND ="待发货";
    String ORDER_TO_RECEIVE ="待收货";
    String ORDER_TO_EVALUATE ="待评价";
    String ORDER_TO_BACK ="退款/售后";

    interface ICenterView extends IView {
    }

    interface ICenterPresent {

    }
}
