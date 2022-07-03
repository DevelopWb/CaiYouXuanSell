package com.juntai.project.sell.mall.search;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.MultipleItem;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityBean;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/20 11:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/20 11:58
 */
public class BaseSearchAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseSearchAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_COMMODITY, R.layout.mall_collect_commodity_item);
        addItemType(MultipleItem.ITEM_SHOP, R.layout.mall_collect_shop_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ITEM_COMMODITY:
                CommodityBean commodityBean = (CommodityBean) item.getObject();
                helper.setGone(R.id.linearlayout_commodity_sales_tv, false);
                ImageLoadUtil.loadSquareImageHasCorner(mContext, commodityBean.getCoverImg(), helper.getView(R.id.linearlayout_commodity_cover_iv));
                helper.setText(R.id.linearlayout_commodity_des_tv, commodityBean.getName());
                helper.setText(R.id.linearlayout_commodity_price_tv, String.format("￥%s",commodityBean.getPrice()));

                break;
            default:
                break;
        }
    }
}
