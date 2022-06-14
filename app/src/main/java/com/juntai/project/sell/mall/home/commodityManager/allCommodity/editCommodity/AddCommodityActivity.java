package com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity;

import android.view.View;

import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;

/**
 * @aouther tobato
 * @description 描述 添加商品
 * @date 2022/6/13 16:20
 */
public class AddCommodityActivity extends BaseShopActivity {

    @Override
    public void initData() {
        super.initData();
        CommodityDetailBean detailBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(detailBean,false));


    }

    @Override
    protected View getFootView() {
        return null;
    }

    @Override
    protected String getTitleName() {
        return "添加商品";
    }

    @Override
    protected boolean isDetail() {
        return false;
    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }



}
