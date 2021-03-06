package com.juntai.disabled.basecomponent.utils.eventbus;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/6 14:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/6 14:10
 */
public class EventBusObject {



    /**
     * 账户被顶之后
     */
    public final static String RE_LOAD = "reload";
    /**
     * 当获取到定位信息
     */
    public final static String ON_LOCATION_RECEIVED = "onLocationReceived";
    /**
     * 刷新订单列表
     */
    public final static String REFRESH_ORDER_LIST = "REFRESH_ORDER_LIST";

    public final static String REFRESH_SEARCH_SHOP_LIST = "REFRESH_SEARCH_SHOP_LIST";

    /**
     * 刷新消息列表
     */
    public final static String REFRESH_NEWS_LIST = "REFRESH_NEWS_LIST";
    /**
     * 待处理订单  待发货或者售后
     */
    public final static String TO_HANDLER_ORDER = "TO_HANDLER_ORDER";
    /**
     * 更新规格数据
     */
    public final static String REFRESH_COMMODITY_FORMAT_DATA = "REFRESH_COMMODITY_FORMAT_DATA";

    public final static String EVALUATE = "评价";
    public final static String WEIXIN_PAY_SUCCESS = "WEIXIN_PAY_SUCCESS";
    /**
     * 未读消息总数
     */
    public final static String UNREAD_MSG_AMOUNT = "UNREAD_MSG_AMOUNT";
    /**
     * 待处理订单
     *
     */
    public final static String UNHANDLER_ORDER_AMOUNT = "UNHANDLER_ORDER_AMOUNT";
    /**
     * 聊天内容
     */
    public final static String MESSAGE_BODY = "MESSAGE_BODY";
    /**
     * 处理接收到的消息
     */
    public final static String HANDLER_MESSAGE = "HANDLER_MESSAGE";
    /**
     * 刷新商品列表
     */
    public final static String REFRESH_COMMODITY_LIST = "REFRESH_COMMODITY_LIST";
    /**
     * 分享
     */
    public final static String LIVE_SHARE = "LIVE_SHARE";

    private String eventKey;

    private Object eventObj;

    public EventBusObject(String eventKey, Object eventObj) {
        this.eventKey = eventKey;
        this.eventObj = eventObj;
    }

    public String getEventKey() {
        return eventKey == null ? "" : eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey == null ? "" : eventKey;
    }

    public Object getEventObj() {
        return eventObj;
    }

    public void setEventObj(Object eventObj) {
        this.eventObj = eventObj;
    }
}
