package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/13 9:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/13 9:58
 */
public class ShopCommodityFragment extends BaseRecyclerviewFragment<ShopPresent> implements HomePageContract.IHomePageView {

    private int status;

    /**
     * @param onStatus 上架状态
     * @return
     */
    public static ShopCommodityFragment getInstance(int onStatus) {
        Bundle bundle = new Bundle();
        bundle.putInt(BASE_ID, onStatus);
        ShopCommodityFragment shopCommodityFragment = new ShopCommodityFragment();
        shopCommodityFragment.setArguments(bundle);
        return shopCommodityFragment;
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
    protected void lazyLoad() {
        super.lazyLoad();
        status = getArguments().getInt(BASE_ID,0);
    }

    @Override
    protected void getRvAdapterData() {
        mPresenter.getAllCommodity(getBaseAppActivity().getBaseBuilder().add("putAwayStatus", String.valueOf(status)).build(), AppHttpPathMall.GET_ALL_COMMODITY);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected boolean enableLoadMore() {
        return true;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new ShopCommodityAdapter(R.layout.shop_commodity_item);
    }

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);

        switch (tag) {
            case AppHttpPathMall.GET_ALL_COMMODITY:
                ShopCommodityListBean shopCommodityListBean = (ShopCommodityListBean) o;
                if (shopCommodityListBean != null) {
                    ShopCommodityListBean.DataBean dataBean =  shopCommodityListBean.getData();
                    if (dataBean != null) {
                        List<ShopCommodityListBean.DataBean.ListBean> arrays = dataBean.getList();
                        setData(arrays,dataBean.getTotalCount());
                    }
                }
                break;
            default:
                break;
        }
    }
}
