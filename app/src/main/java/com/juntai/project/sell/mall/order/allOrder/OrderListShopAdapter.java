package com.juntai.project.sell.mall.order.allOrder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.eventbus.EventBusObject;
import com.juntai.disabled.basecomponent.utils.eventbus.EventManager;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.order.OrderDetailBean;
import com.juntai.project.sell.mall.home.HomePageContract;
import com.juntai.project.sell.mall.utils.UserInfoManagerMall;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/12 14:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/12 14:32
 */
public class OrderListShopAdapter extends BaseQuickAdapter<OrderDetailBean, BaseViewHolder> {
    public OrderListShopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailBean item) {

        helper.setText(R.id.order_shop_name_tv, UserInfoManagerMall.getShopName());
        helper.addOnClickListener(R.id.shop_bottom_cl);
        helper.setGone(R.id.order_paytype_tv, item.getPayType() == 4);
        helper.setGone(R.id.order_status_tv,true);
        helper.setText(R.id.order_status_tv, getOrderStatus(item.getState()));
        helper.setGone(R.id.final_payment_tv, item.getPayType() != 4);
        helper.setText(R.id.final_payment_tv, 0 == item.getState() ? String.format("金额:%s", item.getPayPrice()) : String.format("金额:%s", item.getPayPrice()));
        initBottomButton(helper,item);
        RecyclerView recyclerView = helper.getView(R.id.order_commodities_rv);
        OrderCommodityAdapter orderCommodityAdapter = new OrderCommodityAdapter(R.layout.comfirm_order_commodity_item);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(orderCommodityAdapter);
        recyclerView.setLayoutManager(manager);
        List<OrderDetailBean.CommodityListBean> arrays = item.getCommodityList();
        if (!arrays.isEmpty()) {
            for (OrderDetailBean.CommodityListBean array : arrays) {
                array.setOrderStatus(item.getState());
            }
        }
        orderCommodityAdapter.setNewData(arrays);
        orderCommodityAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
             helper.getView(R.id.shop_bottom_cl).performClick();
            }
        });
        orderCommodityAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDetailBean.CommodityListBean commodityListBean = (OrderDetailBean.CommodityListBean) adapter.getItem(position);
                // : 2022/5/16 立即评价
                EventManager.getEventBus().post(new EventBusObject(EventBusObject.EVALUATE, commodityListBean));
            }
        });
    }

    private void initBottomButton(BaseViewHolder helper, OrderDetailBean item) {
        helper.addOnClickListener(R.id.order_left_tv);
        helper.addOnClickListener(R.id.order_right_tv);
        helper.setGone(R.id.order_left_tv,false);
        helper.setGone(R.id.order_right_tv,false);
        helper.setBackgroundRes(R.id.order_right_tv,R.drawable.app_bt_bg_accent);
        switch (item.getState()) {
            case 0:
                //待付款
                helper.setGone(R.id.order_left_tv,false);
                helper.setGone(R.id.order_right_tv,true);
                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_CANCEL);
                break;
            case 1:
                helper.setGone(R.id.order_left_tv,false);
                helper.setGone(R.id.order_right_tv,true);
                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_SEND);
                break;
            case 2:
            case 3:
            case 7:
            case 8:
                helper.setGone(R.id.order_left_tv,false);
                helper.setGone(R.id.order_right_tv,false);
//                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_REFUND);
                break;
            case 4:
                // : 2022/6/21 退款待处理
                helper.setGone(R.id.order_left_tv,true);
                helper.setGone(R.id.order_right_tv,true);
                helper.setText(R.id.order_left_tv, HomePageContract.ORDER_REJECT);
                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_AGREE);
                break;
            case 6:
                helper.setGone(R.id.order_left_tv,false);
                helper.setGone(R.id.order_right_tv,false);
//                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_DELETE);
                break;
//            case 7:
//                // : 2022/6/21 退款完成
//                helper.setGone(R.id.order_left_tv,false);
//                helper.setGone(R.id.order_right_tv,false);
////                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_REFUND_AGREE);
//                break;
//            case 8:
//                helper.setGone(R.id.order_left_tv,false);
//                helper.setGone(R.id.order_right_tv,true);
//                helper.setText(R.id.order_right_tv, HomePageContract.ORDER_REFUND_UNAGREE);
//                helper.setBackgroundRes(R.id.order_right_tv,R.drawable.app_bt_bg_red);
//                break;
            default:
                break;
        }

    }

    /**
     * 订单状态(0：待付款）（1：待发货）（2：待收货）（3：待评价）（4：退款中）（5：完成）（6:订单取消）（7：退款完成）
     *
     * @param state
     * @return
     */
    private String getOrderStatus(int state) {
        String status = null;
        switch (state) {
            case 0:
                status = "等待买家付款";
                break;
            case 1:
                status = "待发货";
                break;
            case 2:
                status = "等待买家收货";
                break;
            case 3:
                status = "等待买家评价";
                break;
            case 4:
                status = "退款中";
                break;
            case 5:
                status = "已完成";
                break;
            case 6:
                status = "交易关闭";
                break;
            case 7:
                status = "退款完成";
                break;
            case 8:
                status = "退款申请已拒绝";
                break;
            default:
                break;
        }
        return status;
    }


}
