package com.juntai.project.sell.mall.order.refund;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewActivity;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/15 9:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/15 9:30
 */
public class RefundRequestActivity extends BaseRecyclerviewActivity<OrderPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private ConstraintLayout mRefundCl;
    private ConstraintLayout mRefundGoodsCl;
    private OrderDetailBean orderDetailBean;

    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_refund_request;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }



    @Override
    protected void getRvAdapterData() {
        if (orderDetailBean == null) {
            finish();
        }
        List<OrderDetailBean.CommodityListBean> commodityListBeans = orderDetailBean.getCommodityList();
        baseQuickAdapter.setNewData(commodityListBeans);

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
        return new RefundCommodityAdapter(R.layout.mall_refund_commodity_item);
    }


    public void initView() {
        super.initView();
        setTitleName("申请退款");
        mRefundCl = (ConstraintLayout) findViewById(R.id.refund_cl);
        mRefundCl.setOnClickListener(this);
        mRefundGoodsCl = (ConstraintLayout) findViewById(R.id.refund_goods_cl);
        mRefundGoodsCl.setOnClickListener(this);
        orderDetailBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
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
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.refund_cl:
                // : 2022/5/15 退款
                startToRefundActivity(orderDetailBean,1);
                break;
            case R.id.refund_goods_cl:
                // : 2022/5/15 退货退款
                startToRefundActivity(orderDetailBean,2);

                break;
        }
    }
}