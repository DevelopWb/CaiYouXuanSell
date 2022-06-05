package com.juntai.project.sell.mall.home.map;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.juntai.project.sell.mall.R;

/**
 * author:wong  地图底部弹窗的适配器
 * Date: 2019/4/19
 * Description:
 */
public class ClusterClickAdapter extends BaseQuickAdapter<MapClusterItem, BaseViewHolder> {
    public ClusterClickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MapClusterItem item) {
        ImageLoadUtil.loadSquareImageHasCorner(mContext, item.shop.getHeadPortrait(), helper.getView(R.id.commodity_cover_iv));
        helper.setText(R.id.linearlayout_commodity_des_tv, item.shop.getName());

    }
}
