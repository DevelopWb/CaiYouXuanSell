package com.juntai.project.sell.mall.home.shop.shopCategory;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.IdNameBean;

/**
 * @Author: tobato
 * @Description: 作用描述  店铺经营类目
 * @CreateDate: 2022/6/10 14:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/10 14:53
 */
public class ShopCategoryAdapter extends BaseQuickAdapter<IdNameBean.DataBean, BaseViewHolder> {
    public ShopCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdNameBean.DataBean item) {
        if (item.isSelected()) {
            helper.setGone(R.id.category_selected_iv, true);
        } else {
            helper.setGone(R.id.category_selected_iv, false);
        }
        helper.setText(R.id.category_content_tv, item.getName());
    }
}
