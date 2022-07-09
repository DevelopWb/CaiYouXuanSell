package com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity;

import android.view.View;

import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailBean;
import com.juntai.project.sell.mall.beans.sell.CommodityDetailDataBean;
import com.juntai.project.sell.mall.home.shop.BaseShopActivity;

public class CommodityDetailActivity extends BaseShopActivity {

    @Override
    public void initData() {
        super.initData();
      int  commodityId = getIntent().getIntExtra(BASE_ID,0);
        // : 2022/6/15 获取商品详情
        mPresenter.getCommodityDetail(getBaseBuilder().add("commodityId",String.valueOf(commodityId)).build(), AppHttpPathMall.GET_COMMODITY_DETAIL);


    }
    @Override
    protected String getTitleName() {
        return "商品详情";
    }

    @Override
    protected boolean isDetail() {
        return true;
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
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.GET_COMMODITY_DETAIL:
                CommodityDetailDataBean detailDataBean = (CommodityDetailDataBean) o;
                if (detailDataBean != null) {
                    CommodityDetailBean commodityDetailBean = detailDataBean.getData();
                    baseQuickAdapter.setNewData(mPresenter.getCommodityBaseInfoData(commodityDetailBean, true,false));
                }
                break;
            default:
                break;
        }
    }
}
