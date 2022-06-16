package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.JsonObject;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityFormatListBean;

/**
 * @Author: tobato
 * @Description: 作用描述   生成的规格和属性
 * @UpdateUser: 更新者
 */
public class CommodityFormatPropertyAdapter extends BaseQuickAdapter<CommodityFormatListBean.DataBean, BaseViewHolder> {
    public CommodityFormatPropertyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityFormatListBean.DataBean item) {
        ImageLoadUtil.loadSquareImage(mContext,item.getImage(),helper.getView(R.id.commodity_cover_iv));
        JsonObject jsonObject = item.getDetail();
        helper.setText(R.id.commodity_property_tv,jsonObject.toString());
        helper.setText(R.id.commodity_price_tv,String.format("¥ %s",item.getPrice()));
        helper.setText(R.id.commodity_stock_tv,String.format("库存:%s",item.getStock()));
    }
}
