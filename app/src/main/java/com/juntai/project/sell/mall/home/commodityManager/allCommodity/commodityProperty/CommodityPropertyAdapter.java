package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class CommodityPropertyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CommodityPropertyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.add_format_tv);
    }
}
