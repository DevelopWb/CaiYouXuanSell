package com.example.chat.util;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/4/15 10:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/15 10:08
 */
public class MultipleItem implements MultiItemEntity {





    public static final int ITEM_DIVIDER = 1;//  divider
    public static final int ITEM_MENUS = 31;//  菜单

    public static final int ITEM_TITLE = 1001;//  divider
    public static final int ITEM_MYCENTER_MENUS = 2;//  菜单
    public static final int ITEM_PIC_TEXT_MENUS = 3;//
    public static final int ITEM_CONTACT = 4;//
    public static final int ITEM_GROUP = 40;//
    public static final int ITEM_SELECT_GROUP = 401;//
    public static final int ITEM_SELECT_CONTACT = 41;//
    public static final int ITEM_TEXT_VALUE = 5;//
    public static final int ITEM_RECYCLE = 6;//
    public static final int ITEM_KEY_VALUE = 7;//
    //带有switch的控件
    public static final int ITEM_KEY_VALUE_WITH_SWITCH = 8;


    /*====================================================    聊天   ==============================================================*/
    public static final int ITEM_CHAT_TEXT_MSG = 10;//文本聊天
    public static final int ITEM_CHAT_DATE_MSG = 11;//日期
    public static final int ITEM_CHAT_LIST_CONTACT = 12;//
    public static final int ITEM_CHAT_LIST_GROUP = 1201;//
    public static final int ITEM_SEND_AUDIO = 13;//
    public static final int ITEM_RECEIVE_AUDIO = 14;//
    public static final int ITEM_CHAT_PIC_VIDEO = 15;//
    //视频通话记录
    public static final int ITEM_CHAT_VIDEO_CALL = 16;//
    //分享位置信息
    public static final int ITEM_CHAT_LOCATE = 17;//
    //发送名片
    public static final int ITEM_CHAT_CARD = 18;//
    public static final int ITEM_CHAT_FILE = 19;//
    public static final int ITEM_CHAT_RECORD = 20;//
    //提醒类消息  群创建成功 等
    public static final int ITEM_CHAT_NOTICE = 21;//
    /**
     * 外部分享
     */
    public static final int ITEM_CHAT_OUTSIDE_SHARE = 22;//



    public static final int ITEM_CHAT_FILE_DIR = 100;//
    public static final int ITEM_CHAT_FILE_TEXT = 101;//




    public static final int ITEM_COMMODITY_BASE_INFO = 100;//  商品基本信息
    public static final int ITEM_COMMODITY_EVALUTA = 101;//  商品评价
    public static final int ITEM_COMMODITY_DETAIL = 102;//  商品详情


    public static final int ITEM_COMMODITY = 103;//  商品
    public static final int ITEM_SHOP = 104;//  店铺


    private int itemType;
    private Object object;

    public MultipleItem(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
