package com.juntai.disabled.basecomponent.utils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/4/15 10:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/15 10:08
 */
public class MultipleItem implements MultiItemEntity {

    public static final int ITEM_HEAD_PIC = 84;//类型3
    public static final int ITEM_TITILE_BIG = 85;//类型3
    public static final int ITEM_TITILE_SMALL = 86;//类型3
    public static final int ITEM_EDIT = 87;//类型3
    public static final int ITEM_SELECT = 88;//类型3
    public static final int ITEM_RADIO = 89;//类型3
    public static final int ITEM_PIC = 810;//类型3
    public static final int ITEM_NOTICE = 12;//提示
    public static final int ITEM_DATE = 13;//日期
    public static final int ITEM_NORMAL_RECYCLEVIEW = 814;//
    public static final int ITEM_EDIT2 = 815;//  key value 类型
    public static final int ITEM_LOCATION = 817;//  定位
    public static final int ITEM_TEXT = 821;//  文本展示
    public static final int ITEM_FRAGMENT = 818;//多选照片
    public static final int ITEM_FRAGMENT2 = 888;//多选照片
    public static final int ITEM_FRAGMENT_VIDEO = 120;//多选照片
    public static final int ITEM_RICH_TEXT = 121;//富文本展示



    public static final int ITEM_DIVIDER = 1;//  divider
    public static final int ITEM_MENUS = 31;//  菜单



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



        /*====================================================    直播   ==============================================================*/


    public static final int LIVE_MSG = 50;//  消息
    public static final int LIVE_LOG = 51;//  日志


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
