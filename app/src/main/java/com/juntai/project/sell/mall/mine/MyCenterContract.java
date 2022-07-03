package com.juntai.project.sell.mall.mine;


import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.mvp.IView;

import java.util.List;

/**
 * Describe: 个人信息接口类
 * 2020/3/7
 * email:954101549@qq.com
 */
public interface MyCenterContract {
    //我的消息
    String MENU_NEWS ="centerSettingTag";
    String MENU_MODIFY_PHONE ="修改手机号";
    String MENU_MODIFY_PWD ="修改密码";
    String MENU_MODIFY_AUTH ="实名认证";
    String MENU_MODIFY_SUGGESTION ="投诉建议";
    String MENU_MODIFY_BIND ="微信qq绑定";
    String MENU_MODIFY_GUIDE ="新手教程";

    interface ICenterView extends IView {
    }

    interface ICenterPresent {

        List<MultipleItem> getMenuBeans();
    }
}
