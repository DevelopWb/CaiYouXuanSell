package com.juntai.project.sell.mall.order.allOrder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.AppHttpPathMall;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.base.BaseRecyclerviewFragment;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.beans.order.OrderListBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.order.OrderPresent;

import java.util.List;

import okhttp3.FormBody;

/**
 * @Author: tobato
 * @Description: 作用描述  订单列表
 * @CreateDate: 2022/4/29 17:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/4/29 17:37
 */
public class OrderListFragment extends BaseRecyclerviewFragment<OrderPresent> implements HomePageContract.IHomePageView {

    private int labelId;

    public static OrderListFragment newInstance(int labelId) {
        Bundle args = new Bundle();
        args.putInt("label", labelId);
        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void lazyLoad() {
        labelId = getArguments().getInt("label");
        super.lazyLoad();
    }


    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return new OrderListShopAdapter(R.layout.mall_order_list_item);
    }

    @Override

    protected boolean enableLoadMore() {
        return true;
    }

    @Override
    protected OrderPresent createPresenter() {
        return new OrderPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailBean orderDetailBean = (OrderDetailBean) adapter.getItem(position);
                getBaseAppActivity().startToOrderDetailActivity(orderDetailBean.getId(), orderDetailBean.getState());

            }
        });
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailBean orderDetailBean = (OrderDetailBean) adapter.getItem(position);
                TextView orderLeftTv = (TextView) adapter.getViewByPosition(mRecyclerview, position, R.id.order_left_tv);
                TextView orderRightTv = (TextView) adapter.getViewByPosition(mRecyclerview, position, R.id.order_right_tv);

                switch (view.getId()) {

                    case R.id.shop_bottom_cl:
                        // : 2022/5/12 跳转到订单详情
                        getBaseAppActivity().startToOrderDetailActivity(orderDetailBean.getId(), orderDetailBean.getState());
                        break;
                    case R.id.order_shop_name_tv:
                        getBaseAppActivity().startToShop(orderDetailBean.getShopId());
                        break;

                    case R.id.order_left_tv:
                        switch (orderLeftTv.getText().toString().trim()) {
                            case HomePageContract.ORDER_REJECT:
                                // : 2022/6/21 不同意退货
                                getBaseAppActivity(). showAlertDialog("确定不同意退货申请吗?", "确定", "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mPresenter.handlerRefundRequest(getBaseAppActivity().getBaseBuilder()
                                                .add("type", "1")
                                                .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.REFUND_REQUEST);
                                    }
                                });
                                break;
                            default:
                                break;
                        }

                        break;
                    case R.id.order_right_tv:
                        switch (orderRightTv.getText().toString().trim()) {
                            case HomePageContract.ORDER_CANCEL:
                                // : 2022/5/12 取消订单
                                getBaseAppActivity().showAlertDialog("是否取消当前订单?", "确定", "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mPresenter.cancelOrder(getBaseAppActivity().getBaseBuilder()
                                                .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.CANCEL_ORDER);
                                    }
                                });

                                break;
                            case HomePageContract.ORDER_SEND:
                                // :  立即发货
                                getBaseAppActivity().startSendActivity(orderDetailBean.getId());
                                break;
                            case HomePageContract.ORDER_AGREE:
                                // :   同意退货
                                getBaseAppActivity().showAlertDialog("确定同意退货申请吗?", "确定", "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mPresenter.handlerRefundRequest(getBaseAppActivity().getBaseBuilder()
                                                .add("type", "0")
                                                .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.REFUND_REQUEST);
                                    }
                                });
                                break;
                            case HomePageContract.ORDER_DELETE:
                                // : 2022/5/12 删除订单
                                getBaseActivity().showAlertDialog("确定删除该订单?", "确定", "取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mPresenter.deleteCancelOrder(getBaseAppActivity().getBaseBuilder()
                                                .add("orderId", String.valueOf(orderDetailBean.getId())).build(), AppHttpPathMall.DELETE_CANCEL_ORDER);
                                    }
                                });

                                break;
                            default:
                                break;
                        }

                        break;
                    default:
                        break;
                }

            }
        });
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
    public void onEvent(EventBusObject eventBusObject) {
        super.onEvent(eventBusObject);

        switch (eventBusObject.getEventKey()) {
            case EventBusObject.REFRESH_ORDER_LIST:
                String key = (String) eventBusObject.getEventObj();
                page = 1;
                getList(key);
                break;
            case EventBusObject.TO_HANDLER_ORDER:
                page = 1;
                getRvAdapterData();
                break;
            default:
                break;
        }
    }
    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        // : 2022/5/12 获取订单列表
        getList(((OrderManagerActivity) getActivity()).mSearchContentSv.getQuery().toString().trim());

    }

    private void getList(String key) {
        FormBody.Builder builder = getBaseAppActivity().getBaseBuilder()
                .add("page", String.valueOf(page))
                .add("keyword", key)
                .add("payType", getPayType())
                .add("limit", String.valueOf(limit));
        if (-1 != labelId) {
            builder.add("state", String.valueOf(labelId));
        }
        mPresenter.getOrderList(builder
                .build(), AppHttpPathMall.ORDER_LIST
        );
    }

    private String getPayType() {
        String type = ((OrderManagerActivity) getActivity()).orderTypeTv.getText().toString();
        if ("全部订单".equals(type)) {
            return "0";
        } else if ("商城订单".equals(type)) {
            return "1";
        }
        return "4";
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


    @Override
    public void onSuccess(String tag, Object o) {
        super.onSuccess(tag, o);
        switch (tag) {
            case AppHttpPathMall.ORDER_LIST:
                OrderListBean orderListBean = (OrderListBean) o;
                if (orderListBean != null) {
                    OrderListBean.DataBean dataBean = orderListBean.getData();
                    if (dataBean != null) {
                        List<OrderDetailBean> arrays = dataBean.getList();
                        setData(arrays, dataBean.getTotalCount());
                    }
                }


                break;
            case AppHttpPathMall.CANCEL_ORDER:
                ToastUtils.toast(mContext, "已取消订单");
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, ""));
                break;
            case AppHttpPathMall.REFUND_REQUEST:
                ToastUtils.toast(mContext, "已处理");
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, ""));
                break;
            case AppHttpPathMall.NOTICE_SEND:
                ToastUtils.toast(mContext, "已通知卖家尽快发货");
                break;
            case AppHttpPathMall.CONFIRM_RECEIVED:
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, ""));

                break;
            case AppHttpPathMall.DELETE_CANCEL_ORDER:
                ToastUtils.toast(mContext, "已删除订单");
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.REFRESH_ORDER_LIST, ""));

                break;
            default:
                break;
        }
    }
}
