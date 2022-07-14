package com.juntai.project.sell.mall.order.orderDetail;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.juntai.project.sell.mall.base.OrderDetailItemBean;
import com.juntai.project.sell.mall.base.selectPics.SelectPhotosFragment;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.beans.order.OrderDetailDataBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;
import com.juntai.project.sell.mall.order.allOrder.OrderCommodityAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @aouther tobato
 * @description 描述 订单详情
 * @date 2022/5/13 14:06
 */
public class OrderDetailActivity extends BaseAppActivity<OrderPresent> implements HomePageContract.IHomePageView, View.OnClickListener, SelectPhotosFragment.OnPhotoItemClick {

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
    private RecyclerView mOrderInfoRv;
    private OrderDetailBean orderDetailBean;
    private OrderCommodityAdapter mOrderCommodityAdapter;
    private OrderBaseInfoAdapter mOrderInfoAdapter;
    private TextView mOrderStatusTv;
    private TextView mOrderPositiveTv, mOrderNegativeTv;

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

        mOrderInfoRv = (RecyclerView) findViewById(R.id.order_info_rv);


        mOrderDetailTopFl.setVisibility(View.GONE);
        mOrderCommodityAdapter = new OrderCommodityAdapter(R.layout.comfirm_order_commodity_item);
        initRecyclerviewNoScroll(mOrderDetailCommodityRv, mOrderCommodityAdapter, LinearLayoutManager.VERTICAL);
        mOrderInfoAdapter = new OrderBaseInfoAdapter(R.layout.mall_order_baseinfo_item);
        initRecyclerviewNoScroll(mOrderInfoRv, mOrderInfoAdapter, LinearLayoutManager.VERTICAL);
        mOrderDetailTopFl.setVisibility(View.VISIBLE);
        mOrderDetailTopFl.addView(getOrderStatusView());
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
     * 订单状态
     *
     * @return
     */
    private View getOrderStatusView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_status_layout, null);
        mOrderStatusTv = view.findViewById(R.id.order_status_tv);
        mOrderPositiveTv = view.findViewById(R.id.order_positive_tv);
        mOrderNegativeTv = view.findViewById(R.id.order_negative_tv);
        setViewVisibleOrGone(false, mOrderPositiveTv, mOrderNegativeTv);
        mOrderPositiveTv.setOnClickListener(this);
        mOrderNegativeTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        mPresenter.getOrderDetail(getBaseBuilder().add("orderId", String.valueOf(orderId)).build(), AppHttpPathMall.ORDER_DETAIL);

    }


    private void initAddrData() {
        mOrderStatusTv.setText(getOrderStatus());
    }

    /**
     * 订单状态(0：待付款）（1：待发货）（2：待收货）（3：待评价）（4：退款中）（5：完成）（6:订单取消）（7：退款完成） 8 拒绝退款申请
     *
     * @return
     */
    private String getOrderStatus() {
        switch (orderDetailBean.getState()) {
            case 0:
                return "商品已拍下，等待买家付款";
            case 1:
                return "买家已付款";
            case 2:
                return "待收货";
            case 3:
                return "已完成,等待买家评价";
            case 4:
                return "请处理退款申请";
            case 5:
                return "已完成";
            case 6:
                return "交易关闭";
            case 7:
                return "退款已完成";
            case 8:
                return "退款申请已拒绝";
            default:
                break;
        }
        return null;
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
            case R.id.order_negative_tv:
                switch (getTextViewValue(mOrderNegativeTv)) {
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

                    case HomePageContract.ORDER_REJECT:
                        // : 2022/6/21 不同意退货
                        showAlertDialog("确定不同意退货申请吗?", "确定", "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.handlerRefundRequest(getBaseBuilder()
                                        .add("type", "1")
                                        .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.REFUND_REQUEST);
                            }
                        });
                        break;
                    default:
                        break;
                }
                break;
            case R.id.order_positive_tv:
                switch (getTextViewValue(mOrderPositiveTv)) {

                    case HomePageContract.ORDER_SEND:
                        // : 立即发货
                        startSendActivity(orderId);
                        break;
                    case HomePageContract.ORDER_AGREE:
                        // :   同意退货
                        showAlertDialog("确定同意退货申请吗?", "确定", "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPresenter.handlerRefundRequest(getBaseBuilder()
                                        .add("type", "0")
                                        .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.REFUND_REQUEST);
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
                        mFinalPaymentTv.setText(0 == orderStatus ? String.format("总金额:%s", orderDetailBean.getPayPrice()) : String.format("总金额:%s", orderDetailBean.getPayPrice()));
                        List<OrderDetailItemBean> itemBeans = new ArrayList<>();
                        getBuyerInfo(itemBeans);
                        initAddrData();
                        List<TextKeyValueBean> arrays = new ArrayList<>();
                        List<TextKeyValueBean> logistics = new ArrayList<>();

                        switch (orderStatus) {
                            case 0:
                            case 6:
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                break;
                            case 1:
                                mOrderPositiveTv.setVisibility(View.VISIBLE);
                                mOrderPositiveTv.setText(HomePageContract.ORDER_SEND);
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                break;
                            case 2:
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                arrays.add(new TextKeyValueBean("发货时间:", orderDetailBean.getShipmentsTime()));
                                arrays.add(new TextKeyValueBean("物流公司:", orderDetailBean.getLogisticsName()));
                                logistics.add(new TextKeyValueBean("快递单号:", orderDetailBean.getLogisticsNumber()));
                                logistics.add(new TextKeyValueBean("快递链接:", orderDetailBean.getLogisticsLink()));
                                break;
                            case 3:
                            case 5:
                                arrays.add(new TextKeyValueBean("收货信息:", String.format("%s\u3000%s\u3000%s", orderDetailBean.getName(),
                                        orderDetailBean.getPhone(), orderDetailBean.getAddress())));
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                arrays.add(new TextKeyValueBean("发货时间:", orderDetailBean.getShipmentsTime()));
                                logistics.add(new TextKeyValueBean("快递公司:", orderDetailBean.getLogisticsName()));
                                logistics.add(new TextKeyValueBean("快递单号:", orderDetailBean.getLogisticsNumber()));
                                logistics.add(new TextKeyValueBean("物流信息:", orderDetailBean.getLogisticsLink()));
                                arrays.add(new TextKeyValueBean("成交时间:", orderDetailBean.getConfirmTime()));
                                break;
                            case 4:
                            case 7:
                            case 8:
                                if (4 == orderStatus) {
                                    setViewVisibleOrGone(true, mOrderPositiveTv, mOrderNegativeTv);
                                    mOrderPositiveTv.setText(HomePageContract.ORDER_AGREE);
                                    mOrderNegativeTv.setText(HomePageContract.ORDER_REJECT);

                                }
                                arrays.add(new TextKeyValueBean("订单编号:", orderDetailBean.getOrderFormNumber()));
                                arrays.add(new TextKeyValueBean("支付方式:", getPayTypeName(orderDetailBean.getPayType())));
                                arrays.add(new TextKeyValueBean("下单时间:", orderDetailBean.getEstablishTime()));
                                arrays.add(new TextKeyValueBean("付款时间:", orderDetailBean.getPaymentTime()));
                                if (!TextUtils.isEmpty(orderDetailBean.getShipmentsTime())) {
                                    arrays.add(new TextKeyValueBean("发货时间:", orderDetailBean.getShipmentsTime()));
                                }
                                OrderDetailBean.ReturnOrderFormInfoBean fefundBean = orderDetailBean.getReturnOrderFormInfo();
                                if (fefundBean != null) {
                                    List<TextKeyValueBean> refunds = new ArrayList<>();
                                    refunds.add(new TextKeyValueBean("退款原因:", fefundBean.getCauseName()));
                                    refunds.add(new TextKeyValueBean("退款金额:", String.valueOf(fefundBean.getReturnPrice())));
                                    refunds.add(new TextKeyValueBean("申请时间:", fefundBean.getRefundTime()));
                                    refunds.add(new TextKeyValueBean("退款编号:", fefundBean.getSalesFormNumber()));
                                    if (!TextUtils.isEmpty(fefundBean.getRemark())) {
                                        refunds.add(new TextKeyValueBean("退款说明:", fefundBean.getRemark()));
                                    }
                                    OrderDetailItemBean orderDetailItemBean = new OrderDetailItemBean("退款信息", refunds);
                                    List<String> refundPics = new ArrayList<>();
                                    if (!TextUtils.isEmpty(fefundBean.getPictureOne())) {
                                        refundPics.add(fefundBean.getPictureOne());
                                    }
                                    if (!TextUtils.isEmpty(fefundBean.getPictureTwo())) {
                                        refundPics.add(fefundBean.getPictureTwo());
                                    }
                                    if (!TextUtils.isEmpty(fefundBean.getPictureThree())) {
                                        refundPics.add(fefundBean.getPictureThree());
                                    }
                                    if (refundPics.size() > 0) {
                                        refunds.add(new TextKeyValueBean("上传凭证", ""));
                                        orderDetailItemBean.setImages(refundPics);
                                    }
                                    itemBeans.add(orderDetailItemBean);

                                }

                                break;
                            default:
                                break;
                        }

                        if (logistics.size() > 0) {
                            itemBeans.add(new OrderDetailItemBean("物流信息", logistics));
                        }
                        if (arrays.size() > 0) {
                            itemBeans.add(new OrderDetailItemBean("交易信息", arrays));
                        }
                        mOrderInfoAdapter.setNewData(itemBeans);
                    }

                }

                break;
            case AppHttpPathMall.NOTICE_SEND:
                ToastUtils.toast(mContext, "已通知卖家尽快发货");
                break;
            case AppHttpPathMall.REFUND_REQUEST:
            case AppHttpPathMall.CANCEL_ORDER:
            case AppHttpPathMall.CONFIRM_RECEIVED:
                ToastUtils.toast(mContext, "已处理");

                onBackPressed();
                break;
            default:
                break;
        }
    }

    private void getBuyerInfo(List<OrderDetailItemBean> itemBeans) {
        List<TextKeyValueBean> arraysBuyer = new ArrayList<>();
        arraysBuyer.add(new TextKeyValueBean("用户昵称", orderDetailBean.getNickname()));
        arraysBuyer.add(new TextKeyValueBean("收货人", orderDetailBean.getName()));
        arraysBuyer.add(new TextKeyValueBean("联系电话", orderDetailBean.getPhone()));
        arraysBuyer.add(new TextKeyValueBean("收货地址", orderDetailBean.getAddress()));

        OrderDetailBean.CommodityEvaluateBean commodityEvaluateBean = orderDetailBean.getCommodityEvaluateVo();
        if (commodityEvaluateBean != null) {
            List<TextKeyValueBean> buyerEvalute = new ArrayList<>();
            buyerEvalute.add(new TextKeyValueBean("评价内容", commodityEvaluateBean.getEvaluate()));
            buyerEvalute.add(new TextKeyValueBean("评价时间", commodityEvaluateBean.getCreateTime()));
            List<String> pics = new ArrayList<>();
            String imageUrl = commodityEvaluateBean.getImgUrl();
            if (!TextUtils.isEmpty(imageUrl)) {
                if (imageUrl.contains(",")) {
                    String[] arrays = imageUrl.split(",");
                    pics.addAll(Arrays.asList(arrays));
                } else {
                    pics.add(imageUrl);
                }
            }
            String video = commodityEvaluateBean.getVideoUrl();

            OrderDetailItemBean orderDetailItemBean = new OrderDetailItemBean("买家评价", buyerEvalute);
            if (!TextUtils.isEmpty(video)) {
                pics.add(video);
                orderDetailItemBean.setReFundVideoCover(commodityEvaluateBean.getVideoCover());
            }
            if (pics.size() > 0) {
                orderDetailItemBean.setImages(pics);
            }
            itemBeans.add(orderDetailItemBean);


        }

        itemBeans.add(new OrderDetailItemBean("买家信息", arraysBuyer));
    }

    @Override
    public void onVedioPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }
}