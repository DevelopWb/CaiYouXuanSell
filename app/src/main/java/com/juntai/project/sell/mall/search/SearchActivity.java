package com.juntai.project.sell.mall.search;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseTabViewPageActivity;
import com.juntai.project.sell.mall.base.search.BaseSearchHeadFragment;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.HomePagePresent;

/**
 * @aouther tobato
 * @description 描述  首页搜索
 * @date 2022/5/20 14:27
 */
public class SearchActivity extends BaseTabViewPageActivity<HomePagePresent> implements HomePageContract.IHomePageView, BaseSearchHeadFragment.OnSearchCallBack {

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }


    @Override
    public void initData() {
        super.initData();
        mTabTb.setVisibility(View.GONE);
        mViewpageVp.setVisibility(View.GONE);
        mSearchLl.setVisibility(View.GONE);

    }

    @Override
    protected void onTabSelected(int i) {
        commitSearch(mSearchContentSv.getQuery().toString().trim());
    }

    @Override
    protected int getTabMode() {
        return TabLayout.MODE_FIXED;
    }

    @Override
    protected int getTabHeadLayout() {
        return R.layout.fragment_search_head;
    }

    @Override
    protected int getTabFootLayout() {
        return 0;
    }

    @Override
    protected void commitSearch(String s) {
    }

    @Override
    protected String getTitleName() {
        return null;
    }

    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> fragments = new SparseArray<>();
        fragments.append(0, SearchCommodityFragment.newInstance(0));
        fragments.append(1, SearchShopListFragment.newInstance(1));
        return fragments;
    }

    @Override
    protected String[] getTabTitles() {
        return new String[]{COMMODITY, SHOP};

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onSearch(String s) {
        if (!TextUtils.isEmpty(s)) {
            mTabTb.setVisibility(View.VISIBLE);
            mViewpageVp.setVisibility(View.VISIBLE);
        } else {
            mTabTb.setVisibility(View.GONE);
            mViewpageVp.setVisibility(View.GONE);

        }
        EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_SEARCH_COMMODITY_LIST, s));
        EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_SEARCH_SHOP_LIST, s));
    }
}
