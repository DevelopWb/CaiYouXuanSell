package com.juntai.project.sell.mall.home.commodityfragment;

import android.os.Bundle;

/**
 * @Author: tobato
 * @Description: 作用描述  首页的商品列表
 * @CreateDate: 2022/5/20 15:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/20 15:59
 */
public class HomePageCommodityFragment extends BaseCommodityListFragment {

    public static HomePageCommodityFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("label", type);
        HomePageCommodityFragment fragment = new HomePageCommodityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getType() {
        return 0;
    }
}
