package com.juntai.project.sell.mall.search;

import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.project.sell.mall.base.search.BaseSearchHeadFragment;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/28 14:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/28 14:44
 */
public class HomeSearchHeadFragment extends BaseSearchHeadFragment {
    @Override
    protected String getHiskey() {
        return HawkProperty.getHomeSearchHisKey(UserInfoManagerMall.getUserId());
    }
}
