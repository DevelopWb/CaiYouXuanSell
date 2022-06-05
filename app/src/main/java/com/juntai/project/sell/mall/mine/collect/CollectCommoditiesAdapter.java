package com.juntai.project.sell.mall.mine.collect;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CollectDataBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/10 10:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/10 10:33
 */
public class CollectCommoditiesAdapter extends BaseQuickAdapter<CollectDataBean.DataDTO, BaseViewHolder> {
    public CollectCommoditiesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectDataBean.DataDTO item) {
        helper.setGone(R.id.linearlayout_commodity_sales_tv, false);
        ImageLoadUtil.loadSquareImageHasCorner(mContext, item.getPhoto(), helper.getView(R.id.linearlayout_commodity_cover_iv));
        helper.setText(R.id.linearlayout_commodity_des_tv, item.getName());
        helper.setText(R.id.linearlayout_commodity_price_tv, String.format("￥%s",item.getPrice()));
    }
}
