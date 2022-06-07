package com.juntai.project.sell.mall.home.commodityManager;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.PicTextBean;
import com.juntai.project.sell.mall.home.HomePageContract;
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
        arrays.add(new PicTextBean(R.mipmap.ic_launcher,R.drawable.sp_filled_accent,HomePageContract.COMMODITY_MANAGER_CATEGORY,"abcd"));
        arrays.add(new PicTextBean(R.mipmap.ic_launcher,R.drawable.sp_filled_accent,HomePageContract.COMMODITY_MANAGER_TOTAL,"edfasdf"));
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
    public void initData() {
        super.initData();
        setTitleName("商品管理");
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PicTextBean picTextBean = (PicTextBean) adapter.getItem(position);
                switch (picTextBean.getTextName()) {
                    case HomePageContract.COMMODITY_MANAGER_CATEGORY:
                        // TODO: 2022/6/7 商品类目管理
                        break;
                    case HomePageContract.COMMODITY_MANAGER_TOTAL:
                        // TODO: 2022/6/7 商品管理



                        break;
                    default:
                        break;
                }
            }
        });
    }
}
