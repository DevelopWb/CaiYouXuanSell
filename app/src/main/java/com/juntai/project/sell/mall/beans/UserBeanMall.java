package com.juntai.project.sell.mall.beans;


import com.juntai.disabled.basecomponent.bean.ContactBean;
import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * 个人信息
 * Created by Ma
 * on 2019/4/27
 */
public class UserBeanMall extends BaseResult {

    /**
     * data : {"userId":97,"account":"15735046365","phoneNumber":"15735046365","nickname":"铁人王进喜","headPortrait":"https://www.juntaikeji.com:17002/head_img/b66f7c92700d41fc83b23a07d8b37537.jpeg","schoolName":"临沂一中","paymentType":0,"token":"4J5WRF5T2-K7A0PY9UEY82297LS9ZX2-03SB0X2L-0"}
     */

    private ContactBean data;

    public ContactBean getData() {
        return data;
    }

    public void setData(ContactBean data) {
        this.data = data;
    }

}
