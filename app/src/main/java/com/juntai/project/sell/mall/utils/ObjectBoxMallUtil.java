package com.juntai.project.sell.mall.utils;

import com.juntai.disabled.basecomponent.bean.objectboxbean.MessageBodyBean;
import com.juntai.disabled.basecomponent.utils.ObjectBox;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/7 9:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/7 9:26
 */
public class ObjectBoxMallUtil {


    /*====================================================    mall   ==============================================================*/


    /**
     * 添加消息
     * @param messageBodyBean
     */
    public static void addMessage(MessageBodyBean...  messageBodyBean) {
        if (messageBodyBean.length>0) {
            for (MessageBodyBean bodyBean : messageBodyBean) {
                bodyBean.setOwner(UserInfoManagerMall.getUserId());
                ObjectBox.get().boxFor(MessageBodyBean.class).put(bodyBean);
            }
        }

    }
    /**
     * 删除消息
     * @param messageBodyBean
     */
    public static void deleteMessage(MessageBodyBean...  messageBodyBean) {
        ObjectBox.get().boxFor(MessageBodyBean.class).remove(messageBodyBean);
    }
}
