package com.juntai.project.sell.mall.home.commodityManager.allCommodity;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.sell.EditShopCommodityBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 14:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 14:17
 */
public class ShopCommodityEditAdapter extends BaseQuickAdapter<EditShopCommodityBean, BaseViewHolder> {
    public ShopCommodityEditAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditShopCommodityBean item) {
        helper.setText(R.id.edit_commodity_tv, item.getTextContent());
        if (item.isSelect()) {
            helper.setTextColor(R.id.edit_commodity_tv, ContextCompat.getColor(mContext,R.color.white));
            helper.setBackgroundRes(R.id.edit_commodity_tv,R.drawable.sp_filled_accent_circle);
        }else {
            helper.setTextColor(R.id.edit_commodity_tv, ContextCompat.getColor(mContext,R.color.black));
            helper.setBackgroundRes(R.id.edit_commodity_tv,R.drawable.sp_filled_gray_circle);

        }
    }
}
