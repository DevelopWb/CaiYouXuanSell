package com.juntai.project.sell.mall.order.allOrder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.DisplayUtil;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.MainActivity;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseTabViewPageActivity;
import com.juntai.project.sell.mall.base.SingleTextAdapter;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 所有订单
 * @date 2022/5/12 9:31
 */
public class OrderManagerActivity extends BaseTabViewPageActivity<OrderPresent> implements HomePageContract.IHomePageView {
    private int enterType;
    private int tabPosition;
    protected TextView orderTypeTv;
    private PopupWindow popupWindow;


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
        mTabTb.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        super.initData();
        orderTypeTv = findViewById(R.id.order_type_tv);
        orderTypeTv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                List<String> arrays = new ArrayList<>();
                arrays.add("全部订单");
                arrays.add("商城订单");
                arrays.add("公户订单");
                View popView = LayoutInflater.from(mContext).inflate(R.layout.pop_recycler, null);
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(popView, DisplayUtil.dp2px(mContext, 80), WindowManager.LayoutParams.WRAP_CONTENT,
                            false);
                    popupWindow.setOutsideTouchable(true);
                    SingleTextAdapter singleTextAdapter = new SingleTextAdapter(R.layout.pop_text_item);
                    RecyclerView mRecyclerview = (RecyclerView) popView.findViewById(R.id.pop_rv);
                    mRecyclerview.setAdapter(singleTextAdapter);
                    LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    mRecyclerview.setLayoutManager(manager);
                    singleTextAdapter.setNewData(arrays);
                    singleTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            orderTypeTv.setText((String)adapter.getItem(position));
                            EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, mSearchContentSv.getQuery().toString().trim()));
                            popupWindow.dismiss();
                        }
                    });
                }


                popupWindow.showAsDropDown(v,DisplayUtil.dp2px(mContext,15),0);
            }
        });
        mViewpageVp.setCurrentItem(tabPosition);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow=null;
        }
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
        return R.layout.order_manager_head_layout;
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
        return "订单管理";
    }


    @Override
    protected SparseArray<Fragment> getFragments() {
        SparseArray<Fragment> fragments = new SparseArray<>();
        fragments.append(0, OrderListFragment.newInstance(-1));
        fragments.append(1, OrderListFragment.newInstance(0));
        fragments.append(2, OrderListFragment.newInstance(1));
        fragments.append(3, OrderListFragment.newInstance(9));
        fragments.append(4, OrderListFragment.newInstance(2));
        fragments.append(5, OrderListFragment.newInstance(3));
        fragments.append(6, OrderListFragment.newInstance(5));

        return fragments;
    }

    @Override
    protected String[] getTabTitles() {
        return new String[]{ORDER_ALL,ORDER_PAY, ORDER_SEND, ORDER_REFUND, ORDER_IS_SEND, ORDER_EVALUATE,ORDER_FINISHED};
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
