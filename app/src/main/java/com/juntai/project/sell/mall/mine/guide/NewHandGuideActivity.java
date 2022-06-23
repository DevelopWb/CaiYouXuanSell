package com.juntai.project.sell.mall.mine.guide;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.GuideMenuBean;
import com.juntai.project.sell.mall.home.HomePageContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 新手指导
 * @date 2022/6/22 14:50
 */
public class NewHandGuideActivity extends BaseRecyclerviewActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
        return new GridLayoutManager(mContext, 2);
    }

    @Override
    public void initData() {
        super.initData();
        setTitleName("新手教程");
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GuideMenuBean guideMenuBean = (GuideMenuBean) adapter.getItem(position);
                startActivity(new Intent(mContext, GuideDetailActivity.class).putExtra(BASE_STRING, guideMenuBean.getMenuName()));



            }
        });
    }

    @Override
    protected void getRvAdapterData() {
        mRecyclerview.setPadding(DisplayUtil.dp2px(mContext, 20), 0, DisplayUtil.dp2px(mContext, 20), 0);
        baseQuickAdapter.setNewData(getMenuList());
    }


    protected List<GuideMenuBean> getMenuList() {

        List<GuideMenuBean> arrays = new ArrayList<>();

        arrays.add(new GuideMenuBean(HomePageContract.GUIDE_MENU_ADD_COMMODITY, "TIANJIA",
                R.mipmap.guide_add_commodity, R.mipmap.guide_add_commodity_bg));
        arrays.add(new GuideMenuBean(HomePageContract.GUIDE_MENU_SHOP_FURNISH, "ZHUANGXIU",
                R.mipmap.guide_furnish, R.mipmap.guide_furnishbg));

        arrays.add(new GuideMenuBean(HomePageContract.GUIDE_MENU_FINANCE_MANAGER, "CAIWU", R.mipmap.guide_finance, R.mipmap.guide_finance_bg));

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
        return new GuideMenuAdapter(R.layout.guide_menu_item);
    }
}
