package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

/**
 * @aouther tobato
 * @description 描述 商品属性
 * @date 2022/6/15 11:11
 */
public class CommodityPropertyActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView,View.OnClickListener {

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.commodity_property_activity;
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
    public void initData() {
        super.initData();
        baseQuickAdapter.setHeaderFooterEmpty(true,false);
        setTitleName("商品规格属性");
        getTitleRightTv().setText("生成");
        getTitleRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2022/6/15 生成规格属性

            }
        });
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
        return new CommodityPropertyAdapter(R.layout.commodity_property_item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_format_tv:
                // TODO: 2022/6/15 添加规格
                break;
            case R.id.commit_tv:
                // TODO: 2022/6/15 提交更改
                break;
            default:
                break;
        }
    }
}
