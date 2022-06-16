package com.juntai.project.sell.mall.home.commodityManager.allCommodity.commodityProperty;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;
import com.juntai.project.sell.mall.beans.CommodityFormatListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Map<String,String> map = item.getDetail();
        List<String> detail = new ArrayList<>();
         for (Map.Entry<String, String> entry : map.entrySet()) {
             detail.add(entry.getKey()+":"+entry.getValue());
        }
        helper.setText(R.id.commodity_property_tv, TextUtils.join("\n",detail));
        helper.setText(R.id.commodity_price_tv,String.format("¥ %s",item.getPrice()));
        helper.setText(R.id.commodity_stock_tv,String.format("库存:%s",item.getStock()));
    }
}
