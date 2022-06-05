package com.juntai.project.sell.mall.home.commodityfragment.commodity_detail.selectCommodityProperty;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityPropertyListBean;

/**
 * @Author: tobato
 * @Description: 作用描述  商品属性 具体内容
 * @CreateDate: 2022/5/3 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/3 16:30
 */
public class CommodityPropertyContentAdapter extends BaseQuickAdapter<CommodityPropertyListBean.PropertyContentBean, BaseViewHolder> {
    public CommodityPropertyContentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityPropertyListBean.PropertyContentBean item) {
        helper.setText(R.id.property_tv, item.getContent());
        helper.setBackgroundRes(R.id.property_tv,item.isSelected()?R.drawable.sp_filled_accent:R.drawable.sp_filled_gray_lighter);
        helper.setTextColor(R.id.property_tv,item.isSelected()? ContextCompat.getColor(mContext,R.color.white):ContextCompat.getColor(mContext,R.color.black));
    }
}
