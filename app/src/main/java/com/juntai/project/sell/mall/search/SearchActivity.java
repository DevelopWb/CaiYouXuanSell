package com.juntai.project.sell.mall.search;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

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

    private SearchedShopManagerCommodityFragment searchCommodityFragment;
    private HomeSearchHeadFragment searchHeadFragment;
    private SearchShopListFragment searchShopListFragment;
    private int type;

    /**
     * @param mContext
     * @param type     0是首页  1是直播
     */
    public static void startSearchActivity(Context mContext, int type) {
        Intent intent = new Intent(mContext, SearchActivity.class);
        intent.putExtra(BASE_ID, type);
        mContext.startActivity(intent);
    }


    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra(BASE_ID, 0);
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        mTabTb.setVisibility(View.GONE);
        mViewpageVp.setVisibility(View.GONE);
        mSearchLl.setVisibility(View.GONE);
        searchHeadFragment = (HomeSearchHeadFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_serach_top);

    }

    @Override
    protected void onTabSelected(int i) {

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
        searchCommodityFragment = SearchedShopManagerCommodityFragment.getInstance(0);
        fragments.append(0, searchCommodityFragment);
        return fragments;
    }

    @Override
    protected String[] getTabTitles() {
        return new String[]{""};
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onSearch(String s) {
        if (!TextUtils.isEmpty(s)) {
            mViewpageVp.setVisibility(View.VISIBLE);
            searchCommodityFragment.startSearch(s);

        } else {
            mViewpageVp.setVisibility(View.GONE);

        }

    }
}
