package com.juntai.project.sell.mall.search;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.ShopListDataBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/10 10:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/10 10:33
 */
public class SearchShopAdapter extends BaseQuickAdapter<ShopListDataBean.DataBean.ListBean, BaseViewHolder> {
    public SearchShopAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListDataBean.DataBean.ListBean item) {
        ImageLoadUtil.loadHeadCirclePic(mContext,item.getHeadPortrait(), helper.getView(R.id.commodity_cover_iv));
        helper.setText(R.id.linearlayout_commodity_des_tv,item.getName());
    }
}
