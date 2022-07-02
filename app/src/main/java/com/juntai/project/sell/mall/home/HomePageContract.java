package com.juntai.project.sell.mall.home;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * @aouther tobato
 * @description 描述
 * @date 2020/3/12 16:00
 */
public interface HomePageContract {



    String  GUIDE_MENU_ADD_COMMODITY = "商品添加操作";
    String  GUIDE_MENU_SHOP_FURNISH = "店铺装修操作";
    String  GUIDE_MENU_FINANCE_MANAGER = "财务管理操作";

    String MENUE_MAP_TYPE = "地图";//地图类型
    String MENUE_WEATHER = "天气";//天气


    String SHARE_WEIXIN = "微信";
    String SHARE_WEIXIN_FRIENDS = "朋友圈";
    String SHARE_QQ = "QQ";
    String SHARE_QQ_SPACE = "空间";
    String SHARE_SAVE_PIC = "保存图片";
    String SHARE_COPY_LINK = "复制链接";


    String SHOP = "店铺";




    /*====================================================    订单   ==============================================================*/


    String ORDER_CANCEL = "取消订单";
    String ORDER_SEND = "立即发货";
    String ORDER_AGREE = "同意";
    String ORDER_REJECT = "拒绝";
    String ORDER_DELETE = "删除订单";


    String SHOP_FLOW_ORDER = "今日订单量";
    String SHOP_FLOW_BUSINESS = "今日营业额";
    String SHOP_FLOW_VISIT = "今日访问量";

    String SHOP_MANAGER_COMMODITY = "商品管理";
    String SHOP_MANAGER_ORDER = "订单管理";
    String SHOP_MANAGER_LIVE = "直播";
    String SHOP_MANAGER_ASSENT = "收入资产";
    String SHOP_MANAGER_FURNISH = "店铺装修";
    String SHOP_MANAGER_SHOP = "店铺管理";


    String COMMODITY_MANAGER_CATEGORY = "商品类目管理";
    String COMMODITY_MANAGER_TOTAL = "商品管理";


    /*====================================================    描述信息   ==============================================================*/
    String SHOP_PIC = "店铺头像";
    String SHOP_NAME = "店铺名称";
    String SHOP_INTRODUCTION = "店铺简介";
    String SHOP_ADDR = "店铺定位及地址";
    String SHOP_TEL = "店铺联系方式";
    String SHOP_CATEGORY = "店铺主营类目";
    String SHOP_LICENSE = "营业执照";
    String ID_CARD_FRONT = "法人身份证正面";
    String ID_CARD_BACK = "法人身份证反面";
    String SHOP_INNER_PICS = "店铺实景相片";


    String SEND_COMPANY = "快递公司";
    String SEND_NO = "快递单号";
    String SEND_LINK = "链接地址";



    String ASSETS_WITHDRAW_REAL_NAME = "真实姓名";
    String ASSETS_WITHDRAW_IDCARD = "身份证号";
    String ASSETS_WITHDRAW_PHONE = "手机号";
    String ASSETS_WITHDRAW_BANK_NAME = "开户行";
    String ASSETS_WITHDRAW_BANK = "开户行地址";
    String ASSETS_WITHDRAW_BANK_CARD = "银行卡号";




    /*====================================================    商品相关   ==============================================================*/

    String COMMODITY_CATEGORY_NAME = "类目名称";
    String COMMODITY_SORT = "商品分类";
    String COMMODITY_NAME = "商品名称";
    String COMMODITY_PRIMARY_PIC = "商品主图";
    String COMMODITY_BANNER_PICS = "商品轮播图";
    String COMMODITY_VIDEO = "视频";
    String COMMODITY_DETAIL_INFO = "商品详情";
    String COMMODITY_PRICE = "商品价格";
    String COMMODITY_STOCK = "商品库存";
    String COMMODITY_POSTAGE = "商品邮费";
    String COMMODITY_POST_FREE = "是否包邮";


    interface IHomePageView extends IView {

    }

    interface IHomePagePresent extends IPresenter<IHomePageView> {
    }

}
