package com.juntai.project.sell.mall.home;


import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.base.BaseMvpFragment;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.project.sell.mall.MainActivity;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.home.map.MyMapFragment;

/**
 * @aouther tobato
 * @description 描述  home
 * @date 2021/4/18 14:59
 */
public class HomeFragment extends BaseMvpFragment<HomePagePresent> implements HomePageContract.IHomePageView,
        View.OnClickListener {

    private HomeCommodityFragment homeCommodityFragment;
    private MyMapFragment mapFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.home_fg;
    }

    @Override
    protected void initView() {
        initFragmentSelected(0);
        ((MainActivity)getBaseActivity()).mImmersionBar.reset().statusBarColor(R.color.transparent)
                .statusBarDarkFont(true)
                .init();
    }

    /**
     * 获取各个fragment对象
     */
    private void initFragments() {
        if (homeCommodityFragment == null) {
            homeCommodityFragment = new HomeCommodityFragment();
        }
        if (mapFragment == null) {
            mapFragment = new MyMapFragment();
        }
    }

    /**
     * 隐藏所有的fragment
     *
     * @param fragmentTransaction
     */
    private void hindFragments(FragmentTransaction fragmentTransaction) {
        if (homeCommodityFragment != null) {
            fragmentTransaction.hide(homeCommodityFragment);
        }
        if (mapFragment != null) {
            fragmentTransaction.hide(mapFragment);
        }
    }

    @Override
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);
        switch (eventBusObject.getEventKey()) {
            case EventBusObject.HOME_PAGE_DISPLAY_MODE:
                int  type = (int) eventBusObject.getEventObj();
                initFragmentSelected(type);
                break;
            case EventBusObject.ON_LOCATION_RECEIVED:
                BDLocation bdLocation = (BDLocation) eventBusObject.getEventObj();
                mapFragment.setBdLocation(bdLocation);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化fragment
     *
     * @param i
     */
    private void initFragmentSelected(int i) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        hindFragments(fragmentTransaction);
        initFragments();
        switch (i) {
            case 0:
                if (!homeCommodityFragment.isAdded()) {
                    fragmentTransaction.add(R.id.home_fl, homeCommodityFragment, "homepage");
                }
                fragmentTransaction.show(homeCommodityFragment);

                break;
            case 1:
                if (!mapFragment.isAdded()) {
                    fragmentTransaction.add(R.id.home_fl, mapFragment, "mapfragment");
                }
                fragmentTransaction.show(mapFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
    @Override
    protected void initData() {
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
    }

    @Override
    protected void lazyloadGone() {

    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }


    @Override
    public void onSuccess(String tag, Object o) {
    }

    @Override
    public void onError(String tag, Object o) {
        ToastUtils.toast(mContext, String.valueOf(o));
    }


    @Override
    public void onClick(View v) {

    }

}
