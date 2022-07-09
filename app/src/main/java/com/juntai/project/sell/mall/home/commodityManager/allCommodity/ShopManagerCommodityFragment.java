package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityManagerListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity.CommodityDetailActivity;
import com.juntai.project.sell.mall.home.commodityManager.allCommodity.editCommodity.EditCommodityActivity;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/13 9:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/13 9:58
 */
public class ShopManagerCommodityFragment extends BaseRecyclerviewFragment<ShopPresent> implements HomePageContract.IHomePageView, ShopCommodityAdapter.OnChildClickCallBack {

    private int status;

    /**
     * @param onStatus 上架状态
     * @return
     */
    public static ShopManagerCommodityFragment getInstance(int onStatus) {
        Bundle bundle = new Bundle();
        bundle.putInt(BASE_ID, onStatus);
        ShopManagerCommodityFragment shopCommodityFragment = new ShopManagerCommodityFragment();
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
        status = getArguments().getInt(BASE_ID, 0);
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
        mPresenter.getAllCommodity(getBaseAppActivity().getBaseBuilder()
                .add("page",String.valueOf(page))
                .add("limit",String.valueOf(limit))
                .add("putAwayStatus", String.valueOf(status)).build(), AppHttpPathMall.GET_ALL_COMMODITY);
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
        return new ShopCommodityAdapter(R.layout.shop_commodity_item, this);
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
                ShopCommodityManagerListBean shopCommodityListBean = (ShopCommodityManagerListBean) o;
                if (shopCommodityListBean != null) {
                    ShopCommodityManagerListBean.DataBean dataBean = shopCommodityListBean.getData();
                    if (dataBean != null) {
                        List<ShopCommodityManagerListBean.DataBean.ListBean> arrays = dataBean.getList();
                        setData(arrays, dataBean.getTotalCount());
                    }
                }
                break;

            case AppHttpPathMall.COMMODITY_ON_SALE:
                ToastUtils.toast(mContext, "上架成功");
                getRvAdapterData();
                break;
            case AppHttpPathMall.DELETE_COMMODITY:
                ToastUtils.toast(mContext, "删除成功");
                getRvAdapterData();
                break;
            case AppHttpPathMall.COMMODITY_ON_SALE_:
                ToastUtils.toast(mContext, "已下架");
                getRvAdapterData();
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
                ShopCommodityManagerListBean.DataBean.ListBean listBean = (ShopCommodityManagerListBean.DataBean.ListBean) adapter.getItem(position);
                startActivity(new Intent(mContext, CommodityDetailActivity.class).putExtra(BASE_ID, listBean.getId()));
            }
        });
    }

    /**
     * @param editType 0 修改 1 删除 2规格 3上架 4下架
     * @param item
     */
    @Override
    public void onChildClick(int editType, ShopCommodityManagerListBean.DataBean.ListBean item) {
        switch (editType) {
            case 0:
                // : 2022/6/13 修改商品
                getBaseAppActivity().showAlertDialog("是否修改当前商品?", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(mContext, EditCommodityActivity.class).putExtra(BASE_ID, item.getId()));


                    }
                });
                break;
            case 1:
                // : 2022/6/13 删除商品
                getBaseAppActivity().showAlertDialog("是否删除当前商品?", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteCommodity(getBaseAppActivity().getBaseBuilder().add("id", String.valueOf(item.getId())).build(), AppHttpPathMall.DELETE_COMMODITY
                        );
                    }
                });
                break;
            case 2:
                // : 2022/6/15 规格
                getBaseAppActivity().startCommodityPropertyActivity(item.getId());
                break;
            case 3:
                // : 2022/6/15 上架
                getBaseAppActivity().showAlertDialog("是否上架当前商品?", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.onSaleCommodity(getBaseAppActivity().getBaseBuilder().add("id", String.valueOf(item.getId()))
                                .add("status", "0").build(), AppHttpPathMall.COMMODITY_ON_SALE
                        );
                    }
                });
                break;
            case 4:
                // : 2022/6/15 下架
                getBaseAppActivity().showAlertDialog("是否下架当前商品?", "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.onSaleCommodity(getBaseAppActivity().getBaseBuilder().add("id", String.valueOf(item.getId()))
                                .add("status", "1").build(), AppHttpPathMall.COMMODITY_ON_SALE_
                        );
                    }
                });
                break;
            default:
                break;
        }
    }
}
