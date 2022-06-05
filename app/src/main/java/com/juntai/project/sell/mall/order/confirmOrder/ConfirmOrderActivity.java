package com.juntai.project.sell.mall.order.confirmOrder;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.juntai.disabled.basecomponent.bean.address.AddressListBean;
import com.juntai.disabled.basecomponent.utils.GsonTools;
import com.juntai.disabled.basecomponent.utils.HawkProperty;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.beans.order.ConfirmOrderBean;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;
import com.orhanobut.hawk.Hawk;

/**
 * @aouther tobato
 * @description 描述 确认订单
 * @date 2022/5/10 17:30
 */
public class ConfirmOrderActivity extends BaseAppActivity<OrderPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    /**
     * 描述
     */
    private TextView mReceiverNameTv;
    /**
     * 价格
     */
    private TextView mAddrDesTv;
    /**
     * 销量
     */
    private TextView mReceiverPhoneTv;
    private ConstraintLayout mConfirmOrderAddrCl;
    private RecyclerView mOrderShopCommodityRv;
    /**
     * 共4件,小计:
     */
    private TextView mCommodityAmountTv;
    /**
     * Y666
     */
    private TextView mTotalPriceTv;
    /**
     * 提交订单
     */
    private TextView mCommitOrderTv;
    /**
     * 选择收货地址
     */
    private TextView mSelectAddrTv;
    private ConstraintLayout mDefaultAddrCl;
    private CreatOrderBean.DataBean dataBean;
    private ConfirmOrderShopAdapter orderShopAdapter;

    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initView() {
        setTitleName("确认订单");
        mReceiverNameTv = (TextView) findViewById(R.id.receiver_name_tv);
        mAddrDesTv = (TextView) findViewById(R.id.addr_des_tv);
        mReceiverPhoneTv = (TextView) findViewById(R.id.receiver_phone_tv);
        mConfirmOrderAddrCl = (ConstraintLayout) findViewById(R.id.confirm_order_addr_cl);
        mConfirmOrderAddrCl.setOnClickListener(this);
        mOrderShopCommodityRv = (RecyclerView) findViewById(R.id.order_shop_commodity_rv);
        mCommodityAmountTv = (TextView) findViewById(R.id.commodity_amount_tv);
        mTotalPriceTv = (TextView) findViewById(R.id.total_price_tv);
        mCommitOrderTv = (TextView) findViewById(R.id.commit_order_tv);
        mCommitOrderTv.setOnClickListener(this);
        mSelectAddrTv = (TextView) findViewById(R.id.select_addr_tv);
        mDefaultAddrCl = (ConstraintLayout) findViewById(R.id.default_addr_cl);
        if (Hawk.contains(HawkProperty.DEFAULT_ADDR)) {
            mDefaultAddrCl.setVisibility(View.VISIBLE);
            mSelectAddrTv.setVisibility(View.GONE);
            AddressListBean.DataBean dataBean = Hawk.get(HawkProperty.DEFAULT_ADDR);
            if (dataBean != null) {
                initDefaultAddr(dataBean);
            }
        } else {
            mDefaultAddrCl.setVisibility(View.GONE);
            mSelectAddrTv.setVisibility(View.VISIBLE);
        }
        orderShopAdapter = new ConfirmOrderShopAdapter(R.layout.mall_comfirm_order_shop_item);
        initRecyclerview(mOrderShopCommodityRv, orderShopAdapter, LinearLayoutManager.VERTICAL);

    }

    /**
     * 默认地址
     *
     * @param dataBean
     */
    private void initDefaultAddr(AddressListBean.DataBean dataBean) {
        mReceiverNameTv.setText(dataBean.getName());
        mReceiverPhoneTv.setText(dataBean.getPhone());
        mAddrDesTv.setText(dataBean.getDetailedAddress());

    }

    @Override
    public void initData() {
        dataBean = getIntent().getParcelableExtra(BASE_PARCELABLE);
        if (dataBean == null) {
            ToastUtils.toast(mContext, "订单生成异常");
            finish();
        }
        mCommodityAmountTv.setText(String.format("共%s件,小计:", dataBean.getTotalCommodityNum()));
        mTotalPriceTv.setText(String.valueOf(dataBean.getTotalPrice()));
        orderShopAdapter.setNewData(dataBean.getShopList());

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.COMMIT_ORDER:
                // : 2022/5/11 跳转到选择支付方式的界面
                ConfirmOrderBean orderListBean = (ConfirmOrderBean) o;
                if (orderListBean != null) {
                    orderListBean.setTotalPrice(dataBean.getTotalPrice());
                    orderListBean.setTotalCommodityNum(dataBean.getTotalCommodityNum());
                    startToOrderPayActivity(orderListBean, 1);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == BASE_RSULT) {
            if (data != null) {
                AddressListBean.DataBean dataBean = data.getParcelableExtra(BASE_PARCELABLE);
                mSelectAddrTv.setVisibility(View.GONE);
                mDefaultAddrCl.setVisibility(View.VISIBLE);
                initDefaultAddr(dataBean);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.confirm_order_addr_cl:
                // : 2022/5/11 选择收货地址
                startToAddressListActivity(1);
                break;
            case R.id.commit_order_tv:
                // : 2022/5/11 提交订单
                if (!Hawk.contains(HawkProperty.DEFAULT_ADDR)) {
                    ToastUtils.toast(mContext, "请选择收货地址");
                    return;
                }
                dataBean.setName(getTextViewValue(mReceiverNameTv));
                dataBean.setPhone(getTextViewValue(mReceiverPhoneTv));
                dataBean.setDetailedAddress(getTextViewValue(mAddrDesTv));

                mPresenter.commitOrder(getBaseBuilder()
                        .add("json", GsonTools.createGsonString(dataBean)).build(), AppHttpPathMall.COMMIT_ORDER
                );
                break;
        }
    }

}
