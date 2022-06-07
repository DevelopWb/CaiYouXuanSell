package com.juntai.project.sell.mall.mine;


import com.example.chat.util.MultipleItem;
import com.juntai.disabled.basecomponent.mvp.IView;

import java.util.List;

/**
 * Describe: 个人信息接口类
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
 */
public interface MyCenterContract {
    String SET_UPDATE_TAG = "setUpdateTag";
    String SET_CLEAR_TAG ="setClearTag";
    //设置相关
    String MODIFY_PWD = "setUpdatePsdTag";
    String VERIFIED = "实名认证";
    String SET_UPDATE_TEL_TAG = "setUpdateTelTag";
    String SET_WEIXIN_TAG ="setWeiXinTag";
    String SET_QQ_TAG = "setQQTag";
    String SET_ABOUT_TAG ="setAboutTag";
    //我的消息
    String MENU_NEWS ="centerSettingTag";

    interface ICenterView extends IView {
    }

    interface ICenterPresent {

        List<MultipleItem> getMenuBeans();
    }
}
