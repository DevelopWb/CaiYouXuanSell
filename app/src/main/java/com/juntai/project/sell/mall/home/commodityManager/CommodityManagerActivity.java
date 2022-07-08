package com.juntai.project.sell.mall.home.commodityManager;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.commodityManager.commodityCategory.CommodityCategoryActivity;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  商品管理
 * @date 2022/6/7 15:32
 */
public class CommodityManagerActivity extends BaseRecyclerviewActivity<ShopPresent> implements HomePageContract.IHomePageView {

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        baseQuickAdapter.setNewData(getAdapterDate());
    }

    private List<PicTextBean> getAdapterDate() {
        List<PicTextBean>  arrays = new ArrayList<>();
        arrays.add(new PicTextBean(R.mipmap.commodity_category,R.mipmap.commodity_category_bg,HomePageContract.COMMODITY_MANAGER_CATEGORY,"Category management"));
        arrays.add(new PicTextBean(R.mipmap.commodity_managere_icon,R.mipmap.commodity_manager_bg,HomePageContract.COMMODITY_MANAGER_TOTAL,"Commodity management"));
        return arrays;
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
        return new CommodityManagerAdapter(R.layout.commodity_manager_item);
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
        setTitleName("商品管理");
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case HomePageContract.COMMODITY_MANAGER_CATEGORY:
                        // : 2022/6/7 商品类目管理
                        startActivity(new Intent(mContext, CommodityCategoryActivity.class));

                        break;
                    case HomePageContract.COMMODITY_MANAGER_TOTAL:
                        // : 2022/6/7 商品管理
                        startAllCommodityActivity();



                        break;
                    default:
                        break;
                }
            }
        });
    }
}
