package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

/**
 * @aouther tobato
 * @description 描述 店铺内所有商品 管理
 * @date 2022/6/12 14:44
 */
public class AllCommodityActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView {

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName("商品管理");
        getTitleRightTv().setText("添加商品");
        baseQuickAdapter.setNewData(getTestData());
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2022/6/12 添加商品

            }
        });
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
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
        return true;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new ShopCommodityAdapter(R.layout.shop_commodity_item);
    }
}
