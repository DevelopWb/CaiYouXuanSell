package com.juntai.project.sell.mall.home.shop;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.home.HomePageContract;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/9 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/9 11:08
 */
public abstract class BaseShopActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView, SelectPhotosFragment.OnPhotoItemClick, View.OnClickListener {
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
        return new BaseShopAdapter(null, isDetail(), getSupportFragmentManager());
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName(getTitleName());
        if (getFootView() != null) {
            baseQuickAdapter.setFooterView(getFootView());
        }
    }

    protected abstract View getFootView();

    protected abstract String getTitleName();

    @Override
    public void onClick(View v) {

    }

    /**
     * 是否是详情
     *
     * @return
     */
    protected abstract boolean isDetail();

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }
}
