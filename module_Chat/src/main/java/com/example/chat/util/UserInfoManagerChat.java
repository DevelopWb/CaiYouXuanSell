package com.example.chat.util;

import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

/**
 * @Author: tobato
 * @Description: 作用描述  用户信息管理类
 * @CreateDate: 2020/12/19 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/19 14:04
 */
public class UserInfoManagerChat {
    public static String QQ_ID = null;//qqid
    public static String WECHAT_ID = null;//wechatid
    public static String OTHER_NICK_NAME = null;//第三方昵称


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
     * 获取用户的备注信息
     *
     * @return
     */
    public static String getContactRemarkName(MessageBodyBean messageBodyBean) {
        return "1231231";
    }



    public static String getHeadPic() {
        return getUser() != null && getUser() != null ? getUser().getHeadPortrait() : "";
    }
    /**
     * 获取usertoken
     *
     * @return
     */
    public static String getUserToken() {
        return Hawk.get(HawkProperty.SP_KEY_TOKEN);
    }
    /**
     * 获取getUserId
     *
     * @return
     */
    public static int getUserId() {
        return getUser().getUserId();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static String getAccount() {
        return getUser() != null && getUser() != null ? getUser().getAccount() : "";
    }





}
