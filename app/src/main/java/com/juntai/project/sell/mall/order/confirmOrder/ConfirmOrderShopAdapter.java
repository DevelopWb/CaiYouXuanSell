package com.juntai.project.sell.mall.order.confirmOrder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.order.CreatOrderBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/11 10:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/11 10:38
 */
public class ConfirmOrderShopAdapter extends BaseQuickAdapter<CreatOrderBean.DataBean.ShopListBean, BaseViewHolder> {
    public ConfirmOrderShopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreatOrderBean.DataBean.ShopListBean item) {
        helper.setText(R.id.order_shop_name_tv,item.getShopName());
        helper.setText(R.id.transport_price_tv,item.getTransportCharges()>0?String.format("快递￥%s",item.getTransportCharges()):"快递(包邮)");
        RecyclerView recyclerView = helper.getView(R.id.order_shop_commodities_rv);
        ConfirmOrderCommodityAdapter orderCommodityAdapter = new ConfirmOrderCommodityAdapter(R.layout.comfirm_order_commodity_item);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(orderCommodityAdapter);
        recyclerView.setLayoutManager(manager);
        orderCommodityAdapter.setNewData(item.getCommodities());
    }
}
