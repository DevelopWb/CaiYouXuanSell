package com.juntai.project.sell.mall.home.label;


import com.juntai.disabled.basecomponent.mvp.IView;

import java.util.List;

/**
 * Describe:标签相关
 * Create by zhangzhenlong
 * 2021-2-5
 * email:954101549@qq.com
 */
public interface LabelContract {
    String GET_ALL_LABEL_LIST = "get_all_label_list";//获取所有标签
    String UPDATE_USER_LABEL = "update_user_label";//更新用户标签
    String ADD_USER_LABEL = "add_user_label";//设置用户偏好标签
    interface ILabelView extends IView {
    }

    interface ILabelPresenter {
        /**
         * 获取全部标签
         * @param tag
         */
       void getLabelList(String tag);

        /**
         * 更新用户标签
         * @param ids id集合
         * @param tag
         */
        void updateUserLabel(List<Integer> ids, String tag);

        /**
         * 添加用户标签
         * @param name
         * @param tag
         */
        void addUserLabel(int userId, String name, String tag);
    }
}
