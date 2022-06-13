package com.juntai.project.sell.mall.home.commodityManager.commodityCategory;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.sell.ShopCommodityCategoryListBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 14:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 14:17
 */
public class ShopCommodityCategoryAdapter extends BaseQuickAdapter<ShopCommodityCategoryListBean.DataBean, BaseViewHolder> {
    public ShopCommodityCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCommodityCategoryListBean.DataBean item) {
        helper.setText(R.id.commodity_category_name_tv, item.getShopClassifyName());

        helper.addOnClickListener(R.id.modify_tv);
        helper.addOnClickListener(R.id.delete_tv);

    }



}
