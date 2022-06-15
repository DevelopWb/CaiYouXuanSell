package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity.CommodityDetailActivity;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/13 9:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/13 9:58
 */
public class ShopCommodityFragment extends BaseRecyclerviewFragment<ShopPresent> implements HomePageContract.IHomePageView,ShopCommodityAdapter.OnChildClickCallBack {

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
        status = getArguments().getInt(BASE_ID,0);
        super.lazyLoad();
    }

    @Override
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.REFRESH_COMMODITY_LIST:
                lazyLoad();
                break;
            default:
                break;
        }
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
        return new ShopCommodityAdapter(R.layout.shop_commodity_item,this);
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

    @Override
    protected void initData() {
        super.initData();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopCommodityListBean.DataBean.ListBean listBean = (ShopCommodityListBean.DataBean.ListBean) adapter.getItem(position);
                startActivity(new Intent(mContext, CommodityDetailActivity.class).putExtra(BASE_ID,listBean.getId()));
            }
        });
    }

    /**
     *
     * @param editType  0 修改 1 删除 2规格 3上架 4下架
     * @param item
     */
    @Override
    public void onChildClick(int editType, ShopCommodityListBean.DataBean.ListBean item) {
        switch (editType) {
            case 0:
                // TODO: 2022/6/13 修改商品
                
                break;
            default:
                break;
        }
    }
}
