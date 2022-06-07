package com.juntai.project.sell.mall.home.live;

import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.home.shop.ShopPresent;

/**
 * @aouther tobato
 * @description 描述  直播
 * @date 2022/6/7 16:32
 */
public class LivePrepareActivity extends BaseAppActivity<ShopPresent> implements HomePageContract.IHomePageView {

    @Override
    protected ShopPresent createPresenter() {
        return new ShopPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_live_prepare;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }
}
