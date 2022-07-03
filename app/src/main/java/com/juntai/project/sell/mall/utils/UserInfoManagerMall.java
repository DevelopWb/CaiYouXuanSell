package com.juntai.project.sell.mall.utils;

import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.project.sell.mall.webSocket.MyWsManager;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tobato
 * @Description: 作用描述  用户信息管理类
 * @CreateDate: 2020/12/19 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/19 14:04
 */
public class UserInfoManagerMall {
    public static String QQ_ID = null;//qqid
    /**
     * 设备登陆类型
     */
    public static String DEVICE_TYPE = "app_seller";
    public static String WECHAT_ID = null;//wechatid
    public static String OTHER_NICK_NAME = null;//第三方昵称

    /**
     * 获取账号的状态  0 代表游客登录 1代表手机号登录 2代表第三方登录（未绑定手机号）
     *
     * @return
     */
    public static int getAccountStatus() {
        int status = -1;
        if (isLogin()) {
            String phoneNum = getUser().getPhoneNumber();
            if ("未绑定".equals(phoneNum)) {
                status = 2;
            } else {
                status = 1;
            }
        } else {
            status = 0;
        }
        return status;
    }

    /**
     * 获取所有联系人信息
     *
     * @return
     */
    public static Map<Integer,ContactBean> getAllContacts() {
        Map<Integer,ContactBean> contactBeans = Hawk.get(HawkProperty.All_CONTACT);
        if (contactBeans == null) {
            return new HashMap<>();
        }
        return contactBeans;
    }

    /**
     * 添加联系人
     * @param contactBean
     */
    public static void initContacts(ContactBean contactBean) {
        Map<Integer,ContactBean> contactBeans = getAllContacts();

        if (contactBean != null) {
            if (!contactBeans.containsKey(contactBean.getUserId())) {
                contactBeans.put(contactBean.getUserId(),contactBean);
            }
        }
        Hawk.put(HawkProperty.All_CONTACT,contactBeans);

    }


    /**
     * 退出登录清理缓存配置
     */
    public static void clearUserData() {
        Hawk.delete(HawkProperty.SP_KEY_USER);
        Hawk.delete(HawkProperty.SP_KEY_TOKEN);
//        Hawk.delete(HawkPropertyChat.SP_KEY_UNREAD_COUNT);
        //ws退出连接
        MyWsManager.getInstance().disconnect();
//        AliPushManager.getInstance().unbindAccount(UserInfoManager.getUserUUID());

    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static ContactBean getUser() {
        ContactBean userBean = Hawk.get(HawkProperty.SP_KEY_USER);
        return userBean;
    }

    /**
     * 判定用户是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return Hawk.contains(HawkProperty.SP_KEY_USER) && Hawk.contains(HawkProperty.SP_KEY_TOKEN);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static String getPhoneNumber() {
        return getUser() != null && getUser() != null ? getUser().getPhoneNumber() : "";
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static String getAccount() {
        return getUser() != null && getUser() != null ? getUser().getAccount() : "";
    }
    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getRealNameStatus() {
        return getUser() != null? getUser().getRealNameStatus() : 0;
    }
    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getShopId() {
        return getUser() != null? getUser().getShopId() : 0;
    }
    /**
     * 获取getUserId
     *
     * @return
     */
    public static String getShopName() {
        if (Hawk.contains(HawkProperty.SHOP_NAME)) {
            return Hawk.get(HawkProperty.SHOP_NAME);
        }
        return "";
    }

    /**
     * 店铺状态
     *店铺申请状态（1审核中；2审核通过；3审核失败）
     * @return
     */
    public static int getShopStatus() {
        return getUser() != null? getUser().getShopState() : 0;
    }



    public static String getHeadPic() {
        return getUser() != null && getUser() != null ? getUser().getHeadPortrait() : "";
    }

    /**
     * 获取账户
     *
     * @return
     */
    public static String getUserNickName() {
        return getUser() != null && getUser() != null ? getUser().getNickname() : "";
    }


    /**
     * 获取usertoken
     *
     * @return
     */
    public static String getUserToken() {
        if (Hawk.contains(HawkProperty.SP_KEY_TOKEN)) {
            return Hawk.get(HawkProperty.SP_KEY_TOKEN);
        }
       return "";
    }

    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getUserId() {
        return getUser() != null && getUser() != null ? getUser().getUserId() : -1;
    }


}
