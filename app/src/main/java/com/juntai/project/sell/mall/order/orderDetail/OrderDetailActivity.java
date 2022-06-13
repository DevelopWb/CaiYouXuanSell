package com.juntai.project.sell.mall.order.orderDetail;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.bean.TextKeyValueBean;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseAppActivity;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.beans.order.OrderDetailDataBean;
import com.juntai.project.sell.mall.beans.order.OrderListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;
import com.juntai.project.sell.mall.order.allOrder.OrderCommodityAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 订单详情
 * @date 2022/5/13 14:06
 */
public class OrderDetailActivity extends BaseAppActivity<OrderPresent> implements HomePageContract.IHomePageView, View.OnClickListener {

    private int orderId;
    private int orderStatus;
    private FrameLayout mOrderDetailTopFl;
    /**
     * 商铺名称
     */
    private TextView mOrderShopNameTv;
    private RecyclerView mOrderDetailCommodityRv;
    /**
     * 实付款:
     */
    private TextView mFinalPaymentTv;
    private RecyclerView mOrderDetailInfoRv;
    /**
     * 配送服务
     */
    private TextView mOrderLeftTv;
    /**
     * 快递包邮
     */
    private TextView mOrderRightTv;
    private OrderDetailBean orderDetailBean;
    private OrderCommodityAdapter mOrderCommodityAdapter;
    private OrderBaseInfoAdapter mOrderBaseInfoAdapter;
    private TextView mReceiverNameTv;
    private TextView mReceiverAddrTv;
    private TextView mReceiverPhoneTv;
    private TextView mCustomTv;

    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }


    @Override
    public int getLayoutView() {
        return R.layout.order_detail_activity;
    }

    @Override
    public void initView() {
        setTitleName("订单详情");
        orderId = getIntent().getIntExtra(BASE_ID, 0);
        orderStatus = getIntent().getIntExtra(BASE_ID2, 0);
        mOrderDetailTopFl = (FrameLayout) findViewById(R.id.order_detail_top_fl);
        mOrderShopNameTv = (TextView) findViewById(R.id.order_shop_name_tv);
        mOrderShopNameTv.setOnClickListener(this);
        mOrderDetailCommodityRv = (RecyclerView) findViewById(R.id.order_detail_commodity_rv);
        mFinalPaymentTv = (TextView) findViewById(R.id.final_payment_tv);
        mOrderDetailInfoRv = (RecyclerView) findViewById(R.id.order_detail_info_rv);
        mOrderLeftTv = (TextView) findViewById(R.id.order_left_tv);
        mCustomTv = (TextView) findViewById(R.id.custom_tv);
        mOrderLeftTv.setOnClickListener(this);
        mOrderRightTv = (TextView) findViewById(R.id.order_right_tv);
        mOrderRightTv.setOnClickListener(this);
        mOrderDetailTopFl.setVisibility(View.GONE);
        mOrderCommodityAdapter = new OrderCommodityAdapter(R.layout.comfirm_order_commodity_item);
        initRecyclerview(mOrderDetailCommodityRv, mOrderCommodityAdapter, LinearLayoutManager.VERTICAL);
        mOrderBaseInfoAdapter = new OrderBaseInfoAdapter(R.layout.mall_order_baseinfo_item);
        initRecyclerview(mOrderDetailInfoRv, mOrderBaseInfoAdapter, LinearLayoutManager.VERTICAL);
        if (0 == orderStatus || 1 == orderStatus) {
            mOrderDetailTopFl.setVisibility(View.VISIBLE);
            //显示收货地址
            mOrderDetailTopFl.addView(getAddrView());
        } else if (4 == orderStatus || 7 == orderStatus || 8 == orderStatus) {
            mOrderDetailTopFl.setVisibility(View.VISIBLE);
            //显示处理情况
            mOrderDetailTopFl.addView(getProgressView());

        }
        mOrderCommodityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailBean.CommodityListBean commodityListBean = (OrderDetailBean.CommodityListBean) adapter.getItem(position);
            }
        });
        mOrderCommodityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailBean.CommodityListBean commodityListBean = (OrderDetailBean.CommodityListBean) adapter.getItem(position);
                // : 2022/5/16 立即评价
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.EVALUATE, commodityListBean));
            }
        });

    }

    /**
     * 退款进度
     *            //4是等待商家处理  7是商家同意退款 8是商家不同意退款
     * @return
     */
    private View getProgressView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.refund_progress_layout, null);
        TextView contentTv =view.findViewById(R.id.progress_content_tv);

        if (orderStatus == 8) {
            view.findViewById(R.id.recommit_ll).setVisibility(View.VISIBLE);
            setTextViewDrawable(contentTv,true,R.mipmap.mall_unagree_icon);
            contentTv.setText("商家不同意");
        } else {
            view.findViewById(R.id.recommit_ll).setVisibility(View.GONE);
            setTextViewDrawable(contentTv,true,R.mipmap.mall_agree_icon);

            if (4==orderStatus) {
                contentTv.setText("等待商家处理");
            }else {
                contentTv.setText("商家已同意");
            }
        }
        view.findViewById(R.id.recommit_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022/5/15 重新提交退货申请
                startToOrderRefundRequestActivity(orderDetailBean);
            }
        });
        return view;
    }

    /**
     * 获取收货地址
     *
     * @return
     */
    private View getAddrView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.default_addr_layout, null);
        mReceiverNameTv = view.findViewById(R.id.receiver_name_tv);
        mReceiverPhoneTv = view.findViewById(R.id.receiver_phone_tv);
        mReceiverAddrTv = view.findViewById(R.id.addr_des_tv);
        view.findViewById(R.id.arrow_right_iv).setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {
        mPresenter.getOrderDetail(getBaseBuilder().add("orderId", String.valueOf(orderId)).build(), AppHttpPathMall.ORDER_DETAIL);

    }


    private void initAddrData() {

        mReceiverNameTv.setText(orderDetailBean.getName());
        mReceiverPhoneTv.setText(orderDetailBean.getPhone());
        mReceiverAddrTv.setText(orderDetailBean.getAddress());
    }

    /**
     * 获取支付方式
     *
     * @param payType
     * @return /**
     * * 支付方式（0：未支付）（1：支付宝）（2：微信）（3：银行卡）（4：对公账户）
     */
    private String getPayTypeName(int payType) {
        if (payType == 0) {
            return "未支付";
        } else if (payType == 1) {
            return "支付宝";
        } else if (payType == 2) {
            return "微信";
        } else if (payType == 3) {
            return "银行卡";
        } else if (payType == 4) {
            return "对公账户";
        }
        return "";
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.order_shop_name_tv:
                startToShop(orderDetailBean.getShopId());
                break;
            case R.id.order_left_tv:
                switch (getTextViewValue(mOrderLeftTv)) {
                    case HomePageContract.ORDER_CANCEL:
                        // : 2022/5/14 取消订单
                        showAlertDialog("是否取消当前订单?", "确定", "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.cancelOrder(getBaseBuilder()
                                        .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.CANCEL_ORDER);
                            }
                        });

                        break;

                    case HomePageContract.ORDER_REFUND:
                        // : 2022/5/14 申请退款
                        startToOrderRefundRequestActivity(orderDetailBean);

                        break;
                    case HomePageContract.ORDER_REBUY:
                        // TODO: 2022/5/14 再来一单
                        ToastUtils.toast(mContext, "再来一单");
                        break;
                    default:
                        break;
                }
                break;
            case R.id.order_right_tv:
                switch (getTextViewValue(mOrderRightTv)) {
                    case HomePageContract.ORDER_PAY:
                        // : 2022/5/14 立即付款
                        OrderListBean orderListBean = new OrderListBean();
                        OrderListBean.DataBean dataBean = new OrderListBean.DataBean();
                        List<OrderDetailBean> orderDetailBeans = new ArrayList<>();
                        orderDetailBeans.add(orderDetailBean);
                        dataBean.setList(orderDetailBeans);
                        orderListBean.setTotalPrice(orderDetailBean.getPayPrice());
                        orderListBean.setData(dataBean);
                        startToOrderPayActivity(orderListBean, 2);
                        break;

                    case HomePageContract.ORDER_SEND:
                        // : 2022/5/14 提醒发货
                        mPresenter.noticeSend(getBaseBuilder().add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.NOTICE_SEND);
                        break;
                    case HomePageContract.ORDER_RECEIVE:
                        // : 2022/5/12 确认收货
                        showAlertDialog("确定收到货物了吗?", "确定", "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.confirmReceived(getBaseBuilder()
                                        .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.CONFIRM_RECEIVED);
                            }
                        });
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startToAllOrderActivity(1, 0);
    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case AppHttpPathMall.ORDER_DETAIL:
                OrderDetailDataBean orderDetailDataBean = (OrderDetailDataBean) o;
                if (orderDetailDataBean != null) {
                    orderDetailBean = orderDetailDataBean.getData();
                    if (orderDetailBean != null) {
                        List<OrderDetailBean.CommodityListBean> list = orderDetailBean.getCommodityList();
                        if (list != null) {
                            for (OrderDetailBean.CommodityListBean commodityListBean : list) {
                                commodityListBean.setOrderStatus(orderDetailBean.getState());
                            }
                        }
                        mOrderCommodityAdapter.setNewData(list);
                        mOrderShopNameTv.setText(orderDetailBean.getShopName());
                        mFinalPaymentTv.setText(0 == orderStatus ? String.format("需付款:%s", orderDetailBean.getPayPrice()) : String.format("实付款:%s", orderDetailBean.getPayPrice()));
                        List<TextKeyValueBean> arrays = new ArrayList<>();
                        mCustomTv.setVisibility(View.GONE);
                        switch (orderStatus) {
                            case 0:
                                initAddrData();
                                mOrderLeftTv.setText(HomePageContract.ORDER_CANCEL);
                                mOrderRightTv.setText(HomePageContract.ORDER_PAY);
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                break;
                            case 1:
                                initAddrData();
                                mOrderLeftTv.setText(HomePageContract.ORDER_REFUND);
                                mOrderRightTv.setText(HomePageContract.ORDER_SEND);
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                break;
                            case 2:
                                mOrderLeftTv.setText(HomePageContract.ORDER_REFUND);
                                mOrderRightTv.setText(HomePageContract.ORDER_RECEIVE);
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                arrays.add(new TextKeyValueBean("发货时间:", orderDetailBean.getShipmentsTime()));
                                arrays.add(new TextKeyValueBean("物流公司:", orderDetailBean.getLogisticsName()));
                                arrays.add(new TextKeyValueBean("快递单号:", orderDetailBean.getLogisticsNumber()));
                                break;
                            case 3:
//                                mOrderLeftTv.setText(HomePageContract.ORDER_REBUY);
                                mOrderLeftTv.setVisibility(View.GONE);
                                mOrderRightTv.setVisibility(View.GONE);
                                arrays.add(new TextKeyValueBean("收货信息:", String.format("%s\u3000%s\u3000%s", orderDetailBean.getName(),
                                        orderDetailBean.getPhone(), orderDetailBean.getAddress())));
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                arrays.add(new TextKeyValueBean("发货时间:", orderDetailBean.getShipmentsTime()));
                                arrays.add(new TextKeyValueBean("物流公司:", orderDetailBean.getLogisticsName()));
                                arrays.add(new TextKeyValueBean("快递单号:", orderDetailBean.getLogisticsNumber()));
                                arrays.add(new TextKeyValueBean("成交时间:", orderDetailBean.getConfirmTime()));
                                break;
                            case 4:
                            case 7:
                            case 8:
                                mOrderLeftTv.setVisibility(View.GONE);
                                mOrderRightTv.setVisibility(View.GONE);
                                mCustomTv.setVisibility(View.VISIBLE);
                                // : 2022/5/15 这些字段现在没有
                                OrderDetailBean.ReturnOrderFormInfoBean returnOrderFormInfoBean = orderDetailBean.getReturnOrderFormInfo();
                                if (returnOrderFormInfoBean != null) {
                                    arrays.add(new TextKeyValueBean("退款原因:", returnOrderFormInfoBean.getCauseName()));
                                    arrays.add(new TextKeyValueBean("退款金额:", String.valueOf(returnOrderFormInfoBean.getReturnPrice())));
                                    arrays.add(new TextKeyValueBean("申请时间:", returnOrderFormInfoBean.getRefundTime()));
                                    arrays.add(new TextKeyValueBean("退款编号:", returnOrderFormInfoBean.getSalesFormNumber()));
                                }

                                break;
                            default:
                                break;
                        }
                        mOrderBaseInfoAdapter.setNewData(arrays);

                    }

                }

                break;
            case AppHttpPathMall.NOTICE_SEND:
                ToastUtils.toast(mContext, "已通知卖家尽快发货");
                break;
            case AppHttpPathMall.CANCEL_ORDER:
            case AppHttpPathMall.CONFIRM_RECEIVED:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}