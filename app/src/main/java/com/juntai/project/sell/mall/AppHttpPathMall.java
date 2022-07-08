package com.juntai.project.sell.mall;

public class AppHttpPathMall {


//    public static final String BASE = "http://192.168.124.148:8080/jt-mall";
        public static final String BASE = "http://www.juntaikeji.com:21970/jt-mall";
//        public static final String BASE_SOCKET = "ws://192.168.124.148:8080/jt-mall/sellerSocket/";
    public static final String BASE_SOCKET = "ws://www.juntaikeji.com:21970/jt-mall/sellerSocket/";
    //    public static final String BASE_IMAGE = "http://192.168.124.148:9598";
    public static final String BASE_IMAGE = "http://www.juntaikeji.com:19170";

    public static final String ALL_CITYS = "https://restapi.amap.com/v3/config/district?";
    /*==============================================  流媒体相关  =============================================*/


    //摄像头拉流地址
    public static final String BASE_CAMERA_URL = "http://www.juntaikeji.net:8060";
    /**
     * 获取视频播放地址
     */
    public static final String STREAM_OPE_ADDR = "http://61.156.157.132:35080/streamingMedia/u/app/getVideoOpenStream.shtml";

    /**
     * 上传图片或视频
     */
    public static final String UPLOAD_FILES = BASE + "/uploadFile/upload";
    public static final String UPLOAD_ONE_PIC = "/uploadFile/uploadonepic";
    public static final String UPLOAD_MORE_PIC = "/uploadFile/uploadmorepic";
    /**
     * 登录
     */
    public static final String LOGIN = BASE + "/member/sellerLogin";
    /**
     * 登录
     */
    public static final String REGIST = BASE + "/member/register";


    /**
     * /**
     * 检查更新
     */
    public static final String APP_UPDATE = BASE + "/member/detectionAppVersions/2";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = BASE + "/member/getSMSCode";


    /**
     * 查找周边商铺
     */
    public static final String GET_SHOPES_AROUND = BASE + "/seller/selectPeripheryShopList";







    /*====================================================    个人中心   ==============================================================*/


    /**
     * 个人详情
     */
    public static final String GET_USER_INFO = BASE + "/member/getUserInfo";
    /**
     * 提交意见反馈
     */
    public static final String COMMIT_SUGGESTION = BASE + "/opinion/save";
    /**
     * 退出登录
     */
    public static final String LOGOUT = BASE + "/member/logout";

    /**
     * 修改密码
     */
    public static final String MODIFY_PWD = BASE + "/member/updateMemberPwd";
    /**
     * 修改手机号
     */
    public static final String MODIFY_PHONE = BASE + "/member/updateMemberPhone";
    /**
     * 修改个人信息
     */
    public static final String MODIFY_USER_INFO = BASE + "/member/updateMember";

    /**
     * 修改账户
     */
    public static final String MODIFY_USER_ACCOUNT = BASE + "/member/updateUserAccountNumber";


    /**
     * 用户实名认证
     */
    public static final String USER_AUTH = "https://www.dgjpcs.cn/server/dongGuanPoliceStation/u/webConnector/realNameAuthentication.shtml";


    /*====================================================    天气   ==============================================================*/

    //实时天气
    public static final String REALTIME_WEATHER = BASE + "/u/appConnector/getRealTimeWeather.shtml";
    //天气预报
    public static final String FORCAST_WEATHER = BASE + "/u/appConnector/weatherForecast.shtml";
    //获取省份
    public static final String PROVINCE = BASE + "/u/appConnector/getProvince.shtml";
    //获取城市 u/apiAppAlarm/getProvince.shtml
    public static final String CITY = BASE + "/u/appConnector/getCity.shtml";
    //获取地区 u/apiAppAlarm/getProvince.shtml
    public static final String AREA = BASE + "/u/appConnector/getArea.shtml";
    //获取街道
    public static final String STREET = BASE + "/u/appConnector/getStreet.shtml";






    /*====================================================    商品   ==============================================================*/


    //商品类目
    public static final String COMMODIFY_LABELS = BASE + "/seller/selectShopCategoryList";
    /**
     * 商品推荐
     */
    public static final String COMMODIFY_RECOMMEND = BASE + "/seller/selectRecommendList";
    /**
     * 商品详情
     */
    public static final String COMMODIFY_DETAIL = BASE + "/seller/selectCommodityInfo";
    /**
     * 商品评价
     */
    public static final String COMMODIFY_EVALUATION = BASE + "/seller/selectCommodityEvaluateList";





    /*====================================================    店铺   ==============================================================*/


    /**
     * 店铺商品列表
     */
    public static final String SHOP_COMMODITY_LIST = BASE + "/buyers/selectShopCommodityList";
    /**
     * 店铺收藏或取消
     */
    public static final String SHOP_COLLECT = BASE + "/seller/addCollectShop";
    /**
     * 店铺收藏列表
     */
    public static final String SHOP_COLLECT_LIST = BASE + "/seller/getShopCollectList";
    /**
     * 商品收藏或取消
     */
    public static final String COMMODITY_COLLECT = BASE + "/seller/addCollectCommodity";
    /**
     * 商品收藏列表
     */
    public static final String COMMODITY_COLLECT_LIST = BASE + "/seller/getCommodityCollectList";







    /*====================================================    购物车   ==============================================================*/

    /**
     * 加入修改购物车
     */
    public static final String EDIT_CART = BASE + "/seller/addShoppingTrolley";
    /**
     * 购物车列表
     */
    public static final String CART_LIST = BASE + "/seller/selectShoppingTrolleyList";
    /**
     * 删除购物车商品
     */
    public static final String DELETE_CART_COMMODITY = BASE + "/seller/deleteShoppingTrolley";





    /*====================================================    地址管理   ==============================================================*/


    /**
     * 地址列表
     */
    public static final String ADDR_LIST = BASE + "/seller/selectShippingAddressList";
    /**
     * 添加（修改）用户收货地址
     */
    public static final String ADD_OR_EDIT_ADDR = BASE + "/seller/addShippingAddress";
    /**
     * 删除用户收货地址
     */
    public static final String DELETE_ADDR = BASE + "/seller/deleteShippingAddress";
    /**
     * 设置默认地址
     */
    public static final String SET_DEFAULT_ADDR = BASE + "/seller/defaultShippingAddress";







    /*====================================================    订单相关   ==============================================================*/


    /**
     * 购物车生成订单
     */
    public static final String CREAT_ORDER_CART = BASE + "/seller/createTrolleyOrderForm";

    /**
     * 购物车生成订单
     */
    public static final String CREAT_ORDER_BUY = BASE + "/seller/createOrderForm";
    /**
     * 提交订单
     */
    public static final String COMMIT_ORDER = BASE + "/seller/submitOrderForm";
    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER = BASE + "/buyers/cancelOrderForm";
    /**
     * 提醒发货
     */
    public static final String NOTICE_SEND = BASE + "/seller/remindDelivery";
    /**
     * 删除已取消订单
     */
    public static final String DELETE_CANCEL_ORDER = BASE + "/seller/deleteOrder";
    /**
     * 确认收货
     */
    public static final String CONFIRM_RECEIVED = BASE + "/seller/confirmOrderForm";
    /**
     * 订单状态
     */
    public static final String ORDER_STATUS = BASE + "/seller/getOrderFormStateNum";
    /**
     * 订单列表
     */
    public static final String ORDER_LIST = BASE + "/seller/selectOrderFormList";
    /**
     * 订单详情
     */
    public static final String ORDER_DETAIL = BASE + "/seller/selectOrderFormInfo";

    /**
     * 对公付款
     */
    public static final String ORDER_PAY_PUB_ACCOUNT = BASE + "/seller/publicPay/publicPayTradeAppPayRequest";
    /**
     * 微信支付
     */
    public static final String ORDER_PAY_PUB_WEIXIN = BASE + "/seller/weChatPay/weChatPayTradeAppPayRequest";
    /**
     * 支付宝支付
     */
    public static final String ORDER_PAY_ZHIFUBAO = BASE + "/seller/aliPay/aliPayTradeAppPayRequest";
    /**
     * 订单状态数量
     */
    public static final String ORDER_STATUS_AMOUNT = BASE + "/seller/getOrderFormStateNum";

    /**
     * 退货原因
     */
    public static final String GET_REFUND_REASON = BASE + "/seller/getSalesReturnCause";

    /**
     * 申请退款
     */
    public static final String REQUEST_REFUND = BASE + "/seller/addSalesReturn";

    /**
     * 发布评价
     */
    public static final String START_EVALUATE = BASE + "/seller/addCommodityEvaluate";





    /*====================================================    消息   ==============================================================*/


    /**
     * 发送消息
     */
    public static final String SEND_MSG = BASE + "/msg/sendMsg";
    /**
     * 消息已读
     */
    public static final String MESSAGE_READ = BASE + "/msg/updateMsgRead";
    /**
     * 未读消息详情
     */
    public static final String UNREAD_CONTACT_MSG = BASE + "/msg/selectMsgInfo";
    /**
     * 消息列表
     */
    public static final String NEWS_LIST = BASE + "/msg/selectMsgList";


    /**
     * 搜索
     */
    public static final String SEARCH_COMMODITY = BASE + "/member/search";

    /**
     * 搜索
     */
    public static final String SEARCH_SHOP = BASE + "/member/search";







    /*====================================================    卖家端   ==============================================================*/


    /**
     * 首页
     */
    public static final String SHOP_HOME_INFO = BASE + "/seller/selectShopHome";
    /**
     * 系统公告和通知
     */
    public static final String GET_SYSTEM_NOTICE = BASE + "/seller/getSysNoticeList";

    /**
     * 系统公告和通知
     */
    public static final String GET_SYSTEM_NOTICE_DETAIL = BASE + "/seller/getSysNoticeInfo";




    /*====================================================    卖家店铺   ==============================================================*/


    /**
     * 店铺详情
     */
    public static final String SHOP_DETAIL = BASE + "/seller/selectShopInfo";
    /**
     * 店铺申请
     */
    public static final String SHOP_APPLY = BASE + "/seller/applyShop";
    /**
     * 编辑店铺申请
     */
    public static final String EDIT_SHOP_APPLY = BASE + "/seller/updateApplyShop";

    /**
     * 经营类目
     */
    public static final String ALL_SHOP_CATEGORY = BASE + "/seller/selectShopCategoryList";




    /*====================================================    商品类目   ==============================================================*/
    /**
     * 商品类目
     */
    public static final String ALL_COMMODITY_CATEGORY = BASE + "/seller/selectShopClassifyList";
    /**
     * 添加商品类目
     */
    public static final String ADD_COMMODITY_CATEGORY = BASE + "/seller/addShopClassify";
    /**
     * 修改商品类目
     */
    public static final String MODIFY_COMMODITY_CATEGORY = BASE + "/seller/updateShopClassify";
    /**
     * 删除商品类目
     */
    public static final String DELETE_COMMODITY_CATEGORY = BASE + "/seller/deleteShopClassify";




    /*====================================================    商品管理   ==============================================================*/

    /**
     * 商品列表
     */
    public static final String GET_ALL_COMMODITY = BASE + "/seller/selectCommodityList";
    /**
     * 商品详情
     */
    public static final String GET_COMMODITY_DETAIL = BASE + "/seller/selectCommodityInfo";
    /**
     * 添加商品基础信息
     */
    public static final String ADD_COMMODITY_BASE_INFO = BASE + "/seller/addCommodity";
    /**
     * 更新商品基础信息
     */
    public static final String UPDATE_COMMODITY_BASE_INFO = BASE + "/seller/updateCommodity";
    /**
     * 删除商品基础信息
     */
    public static final String DELETE_COMMODITY = BASE + "/seller/deleteCommodity";
    /**
     * 生成商品属性
     */
    public static final String CREATE_COMMODITY_FORMAT = BASE + "/seller/isFormatAttr";
    /**
     * 获取商品属性
     */
    public static final String GET_COMMODITY_FORMAT = BASE + "/seller/getCommodityAttr";
    /**
     * 添加修改 商品属性
     */
    public static final String EDIT_COMMODITY_FORMAT = BASE + "/seller/addCommodityAttr";
    /**
     * 添加修改 商品属性
     */
    public static final String MODIFY_COMMODITY_PRICE_STOCK= BASE + "/seller/updateCommodityAttrStockPrice";
    /**
     * 上架
     */
    public static final String COMMODITY_ON_SALE = BASE + "/seller/onSale";
    public static final String COMMODITY_ON_SALE_ = BASE + "/seller/onSale_";
    /**
     * 发货
     */
    public static final String SEND_GOODS = BASE + "/seller/addPhysicalDistribution";
    /**
     * 是否同意退货
     */
    public static final String REFUND_REQUEST = BASE + "/seller/orderRefund";




        /*====================================================    店铺装修   ==============================================================*/

    /**
     * 上传店铺背景图及形象图片
     */
    public static final String ADD_SHOP_BANNERS = BASE + "/seller/addShopPhoto";





        /*====================================================    财务管理   ==============================================================*/


    /**
     * 账单明细列表
     */
    public static final String BILL_LIST = BASE + "/seller/getBillList";

    /**
     * bill基础信息
     */
    public static final String BILL_BASE_INFO = BASE + "/seller/getFinanceInfo";
    /**
     * 提现列表
     */
    public static final String BILL_WITHDRAW = BASE + "/seller/getWithdrawList";


    /**
     * 月收入统计
     */
    public static final String MONTH_STATISTICS = BASE + "/seller/getMonthIncome";
    /**
     * 提现
     */
    public static final String WITHDRAW = BASE + "/seller/toWithdrawFunds";
    /**
     * 绑定银行卡
     */
    public static final String BIND_BANK_CARD = BASE + "/seller/bindingBankCard";





        /*====================================================    live   ==============================================================*/







}