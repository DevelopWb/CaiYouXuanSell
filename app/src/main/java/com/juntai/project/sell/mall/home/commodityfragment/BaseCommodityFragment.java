package com.juntai.project.sell.mall.home.commodityfragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppFragment;
import com.juntai.project.sell.mall.base.customview.CustomViewPager;
import com.juntai.project.sell.mall.beans.IdNameBean;
import com.juntai.project.sell.mall.beans.shop.ShopDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopActivity;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述  homepage
 * @date 2021/4/18 14:59
 */
public abstract class BaseCommodityFragment extends BaseAppFragment<CommodityPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private TabLayout mShopTablayout;
    private ImageView mMoreMenuBtn;
    private CustomViewPager mShopViewpager;
    private CommodityPagerAdapter adapter;

    private boolean commodityArrayTypeLinearlayout = false;


    @Override
    protected int getLayoutRes() {
        return R.layout.homepage_commidity_fg;
    }

    @Override
    protected void initView() {
        mShopTablayout = (TabLayout) getView(R.id.mall_tablayout);
        mMoreMenuBtn = (ImageView) getView(R.id.more_menu_btn);
        mMoreMenuBtn.setOnClickListener(this);
        mShopViewpager = (CustomViewPager) getView(R.id.shop_viewpager);
        initTab();
        initTabData();

        if (getType()==2) {
            mMoreMenuBtn.setVisibility(View.VISIBLE);
        }else {
            mMoreMenuBtn.setVisibility(View.GONE);
        }
    }

    private void initTab() {
        adapter = new CommodityPagerAdapter(getChildFragmentManager(), mContext,
                getLabels(), getType());
        mShopViewpager.setAdapter(adapter);
        /*viewpager切换监听，包含滑动点击两种*/
        mShopViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mShopTablayout.setupWithViewPager(mShopViewpager);

    }

    @Override
    protected void initData() {

    }

    protected abstract int getType();

    private void initTabData() {
        adapter.setTitles(getLabels());
        mShopViewpager.setOffscreenPageLimit(getLabels().size());
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mShopTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mShopTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mShopViewpager.setCurrentItem(0);
    }

    /**
     * 获取标签
     *
     * @return
     */
    private List<IdNameBean.DataBean> getLabels() {
        List<IdNameBean.DataBean> arrays = new ArrayList<>();
        if (2 != getType()) {
            arrays.add(0, new IdNameBean.DataBean(0, "推荐"));
            if (Hawk.contains(HawkProperty.LOCAL_LABEL)) {
                List<IdNameBean.DataBean> dataBeans = Hawk.get(HawkProperty.LOCAL_LABEL);
                arrays.addAll(dataBeans);
            }
        } else {
            //店铺
            ShopDetailBean.DataBean shopBean = Hawk.get(HawkProperty.getShopKey(((ShopActivity) getActivity()).shopId));
            if (shopBean != null) {
                List<ShopDetailBean.DataBean.ClassifyListBean> classifyListBeans = shopBean.getClassifyList();
                if (classifyListBeans != null && !classifyListBeans.isEmpty()) {
                    for (ShopDetailBean.DataBean.ClassifyListBean classifyListBean : classifyListBeans) {
                        arrays.add(new IdNameBean.DataBean(classifyListBean.getId(), classifyListBean.getShopClassifyName()));
                    }
                }
            }
        }

        return arrays;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void lazyLoad() {
        if (2 == getType()) {
            mPresenter.getShopDetail(getBaseAppActivity().getBaseBuilderWithoutParama().add("shopId", String.valueOf(((ShopActivity) getActivity()).shopId)
                    ).add("userId", String.valueOf(UserInfoManagerMall.getUserId())).build()
                    , AppHttpPathMall.SHOP_DETAIL);
        } else {
            mPresenter.getCommodityLaBels(AppHttpPathMall.COMMODIFY_LABELS);

        }
    }

    @Override
    protected void lazyloadGone() {

    }

    @Override
    protected CommodityPresent createPresenter() {
        return new CommodityPresent();
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.COMMODIFY_LABELS:
                List<IdNameBean.DataBean> dataBeans = (List<IdNameBean.DataBean>) o;
                if (!dataBeans.isEmpty()) {
                    Hawk.put(HawkProperty.LOCAL_LABEL, dataBeans);
                    initTabData();
                }
                break;
            case AppHttpPathMall.SHOP_DETAIL:
                ShopDetailBean shopDetailBean = (ShopDetailBean) o;
                if (shopDetailBean != null) {
                    ShopDetailBean.DataBean shopBean = shopDetailBean.getData();
                    if (shopBean != null) {
                        Hawk.put(HawkProperty.getShopKey(((ShopActivity) getActivity()).shopId), shopBean);
                        initTabData();
                        ((ShopActivity) getActivity()).initOwnerBaseInfo(shopBean);
                    }

                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.more_menu_btn:

                if (getType() != 2) {
                    // TODO: 2022/5/4 编辑标签
                } else {
                    // : 2022/5/4 更改商品排列方式
                    commodityArrayTypeLinearlayout = !commodityArrayTypeLinearlayout;
                    EventManager.getEventBus().post(new EventBusObject(EventBusObject.CHANGE_COMMODITY_ARRAY_TYPE, commodityArrayTypeLinearlayout));
                }

                break;
        }
    }
}
