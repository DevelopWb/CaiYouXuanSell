package com.juntai.project.sell.mall.order.allOrder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.MainActivity;
import com.juntai.project.sell.mall.base.BaseTabViewPageActivity;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;

/**
 * @aouther tobato
 * @description 描述 所有订单
 * @date 2022/5/12 9:31
 */
public class AllOrderActivity extends BaseTabViewPageActivity<OrderPresent> implements HomePageContract.IHomePageView {
    //0代表支付成功之后  1代表个人中心进入
    private int enterType;
    private int tabPosition;



    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        enterType = intent.getIntExtra(BASE_ID, 0);
        EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, mSearchContentSv.getQuery().toString().trim()));

    }

    @Override
    public void initView() {
        enterType = getIntent().getIntExtra(BASE_ID, 0);
        tabPosition = getIntent().getIntExtra(BASE_ID2, 0);
        super.initView();
        if (5==tabPosition) {
            mTabTb.setVisibility(View.GONE);
        }else {
            mTabTb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        mViewpageVp.setCurrentItem(tabPosition);
    }

    @Override
    protected void onTabSelected(int i) {

    }

    @Override
    protected int getTabMode() {
        return 0;
    }

    @Override
    protected int getTabHeadLayout() {
        return 0;
    }

    @Override
    protected int getTabFootLayout() {
        return 0;
    }

    @Override
    protected void commitSearch(String s) {
        EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, mSearchContentSv.getQuery().toString().trim()));

    }

    @Override
    protected String getTitleName() {
        if (5== tabPosition) {
            return "退款/售后";
        }
        return "全部订单";
    }


    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> fragments = new SparseArray<>();
        if (5== tabPosition) {
            fragments.append(0, OrderListFragment.newInstance(4));
        }else {
            fragments.append(0, OrderListFragment.newInstance(-1));
            fragments.append(1, OrderListFragment.newInstance(0));
            fragments.append(2, OrderListFragment.newInstance(1));
            fragments.append(3, OrderListFragment.newInstance(2));
            fragments.append(4, OrderListFragment.newInstance(3));
        }

        return fragments;
    }

    @Override
    protected String[] getTabTitles() {
        if (5== tabPosition) {
            return new String[]{ORDER_TOTAL};
        }
        return new String[]{ORDER_TOTAL, ORDER_PAY, ORDER_SEND, ORDER_RECEIVE, ORDER_EVALUATE};
    }

    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onBackPressed() {
        if (0 == enterType) {
            // : 2022/5/12   跳到首页
            startActivity(new Intent(mContext, MainActivity.class));
        } else {
            super.onBackPressed();
        }
    }
}
