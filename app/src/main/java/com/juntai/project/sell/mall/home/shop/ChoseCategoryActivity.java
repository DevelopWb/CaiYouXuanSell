package com.juntai.project.sell.mall.home.shop;

import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.home.HomePageContract;

/**
 * @aouther tobato
 * @description 描述 选择主营类目
 * @date 2022/6/10 14:35
 */
public class ChoseCategoryActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView {

    @Override
    protected ShopPresent createPresenter() {
        return null;
    }


    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {

    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return null;
    }
}
